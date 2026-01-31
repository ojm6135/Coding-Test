//import java.io.*;
//import java.util.*;
//
//public class Main {
//        static List<Integer>[] graph;
//        static int[] parent;
//        static boolean[] finished;
//        static List<List<Integer>> scc;
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        // 순서대로 dfs
//        // dfs 중, 최초 부모 자기 자신, 아직 탐색 중인 노드를 만난다? 그 노드가 부모
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int V = Integer.parseInt(st.nextToken());
//        int E = Integer.parseInt(st.nextToken());
//        graph = new ArrayList[V + 1];
//        parent = new int[V + 1];
//        finished = new boolean[V + 1];
//        int u, v;
//        scc = new ArrayList<>();
//
//        for (int i = 1; i <= V; i++) {
//            graph[i] = new ArrayList<>();
//        }
//
//        for (int i = 0; i < E; i++) {
//            st = new StringTokenizer(br.readLine());
//            u = Integer.parseInt(st.nextToken());
//            v = Integer.parseInt(st.nextToken());
//            graph[u].add(v);
//        }
//
//
//
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//
//        Stack<Integer> history = new Stack<>();
//        history.push(1);
//
//        List<Integer> t;
//
//        while (!stack.empty()) {
//            u = stack.pop();
//
//            if (parent[u] != 0) {
//                continue;
//            }
//
//            parent[u] = u;
//
//            for (int next : graph[u]) {
//                if (parent[next] == 0) {  // 최초 탐색 시 자기 자신을 부모로 지정
//                    stack.push(next);
//                    history.push(next);
//                    continue;
//                }
//
//                // 탐색 중에 같은 노드를 다시 만남 -> 사이클 발견
//                t = new ArrayList<>();
//                // SCC 추가
//                // parent 갱신
//                while (!history.isEmpty()) {
//                    v = history.pop();
//                    if (finished[v]) {
//                        continue;
//                    }
//
//                    finished[v] = true;
//                    parent[v] = next;
//                    t.add(v);
//
//                    if (v == next) {
//                        t.sort(Comparator.naturalOrder());
//                        scc.add(t);
//                        break;
//                    }
//                }
//
//                // 자식 노드들의 탐색이 모두 종료되었지만 부모가 남아있을 때
//                // 즉, SCC의 요소가 자기 자신 뿐
//                while (stack.size() < history.size()) {
//                    v = history.pop();
//                    if (finished[v]) {
//                        continue;
//                    }
//
//                    finished[v] = true;
//                    t = new ArrayList<>();
//                    t.add(v);
//                    scc.add(t);
//                }
//            }
//        }
//
//        while (!history.isEmpty()) {
//            v = history.pop();
//            if (finished[v]) {
//                continue;
//            }
//
//            finished[v] = true;
//            t = new ArrayList<>();
//            t.add(v);
//            scc.add(t);
//        }
//
//        scc.sort(Comparator.comparingInt(o -> o.get(0)));
//
//        StringBuilder sb = new StringBuilder();
//
//        for (List<Integer> l : scc) {
//            for (int vtx : l) {
//                sb.append(vtx).append(" ");
//            }
//
//            sb.append("-1\n");
//        }
//
//        bw.write(scc.size() + "\n");
//        bw.write(sb.toString());
//
//        br.close();
//        bw.close();
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] sushi = new int[N];
        int[] count = new int[D + 1];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine().trim());
        }

        int res = 0;

        for (int i = 0; i < K; i++) {
            if (count[sushi[i]] == 0) {
                res++;
            }

            count[sushi[i]]++;
        }

        if (count[C] == 0) {
            res++;
        }

        int curr = res;
        int head, tail;

        // window를 한 칸씩 이동하며 최댓값 갱신
        for (int i = K; i < N + K - 1; i++) {
            // tail
            tail = (i + N - K) % N;

            if (sushi[tail] == C) {
                if (count[C] == 1) {
                    curr--;
                }
            } else {
                if (count[sushi[tail]] == 1) {
                    curr--;
                }

                if (count[C] == 0) {
                    curr--;
                }
            }

            count[sushi[tail]]--;

            // head: 새로 추가할 접시
            head = i % N;

            if (sushi[head] == C) {
                if (count[C] == 0) {
                    curr++;
                }
            } else {
                if (count[sushi[head]] == 0) {
                    curr++;
                }

                if (count[C] == 0) {
                    curr++;
                }
            }

            count[sushi[head]]++;
            res = Math.max(res, curr);
        }

        System.out.println(res);
    }
}
