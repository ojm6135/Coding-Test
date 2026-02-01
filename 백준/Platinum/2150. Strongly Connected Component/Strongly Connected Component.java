import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Integer>[]graph = new ArrayList[V + 1];
        int[] disc = new int[V + 1];  // 노드가 최초 탐색될 때 부여되는 ID (낮을수록 먼저 탐색)
        int[] low = new int[V + 1];   // 한 노드가 back edge를 타고 올라갈 수 있는 부모 ID의 최솟값
        boolean[] finished = new boolean[V + 1];  // 하나의 SCC에 포함되면 true
        int u, v;
        List<List<Integer>> sccList = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }
        
        // 타잔 알고리즘
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> history = new Stack<>();
        List<Integer> scc;
        int id = 0;

        for (int start = 1; start <= V; start++) {
            if (finished[start]) {
                continue;
            }

            stack.push(start);

            // pop이 아닌 peek 이유:
            // 첫 사이클은 최초 탐색, 두 번째 사이클은 자식으로부터 부모의 low 갱신
            while (!stack.isEmpty()) {
                u = stack.peek();

                if (finished[u]) {
                    stack.pop();
                    continue;
                }

                if (disc[u] == 0) {
                    disc[u] = ++id;
                    low[u] = disc[u];
                    history.push(u);
                }

                for (int w : graph[u]) {
                    if (finished[w]) {
                        continue;
                    }

                    // 자식을 탐색했다면, 현재 노드의 low를 자식의 low로
                    if (disc[w] != 0) {
                        low[u] = Math.min(low[u], low[w]);
                        continue;
                    }

                    stack.push(w);
                }

                if (stack.peek() == u) {  // stack의 top == 더 이상 탐색할(갱신에 이용할) 자식이 없음
                    if (low[u] == disc[u]) {  // scc의 부모
                        scc = new ArrayList<>();

                        while (!history.isEmpty() && disc[history.peek()] >= disc[u]) {
                            v = history.pop();
                            scc.add(v);
                            finished[v] = true;
                        }

                        scc.sort(Comparator.naturalOrder());
                        sccList.add(scc);
                    }

                    stack.pop();
                }
            }
        }

        sccList.sort(Comparator.comparingInt(list -> list.get(0)));

        StringBuilder sb = new StringBuilder(sccList.size() + "\n");

        for (List<Integer> SCC : sccList) {
            for (Integer e : SCC) {
                sb.append(e).append(" ");
            }

            sb.append(-1).append("\n");
        }

        bw.write(sb.toString());
        
        br.close();
        bw.close();
    }
}