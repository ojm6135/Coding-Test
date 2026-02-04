import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int u, v, w;
        List<Node>[] tree = new ArrayList[N + 1];
        int[] total = new int[N + 1];
        int res = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            tree[u].add(new Node(v, w));
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        Stack<Integer> history = new Stack<>();

        while (!stack.isEmpty()) {
            u = stack.pop();
            history.push(u);

            for (Node child : tree[u]) {
                stack.push(child.to);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // leaf node부터 거슬러 올라가며 계산
        while (!history.isEmpty()) {
            u = history.pop();

            for (Node child : tree[u]) {
                pq.add(total[child.to] + child.weight);
            }

            // 자식 중 최댓값
            if (!pq.isEmpty()) {
                total[u] = pq.poll();
            }

            // 자식 중 두 번째 최댓값
            if (!pq.isEmpty()) {
                res =  Math.max(res, total[u] + pq.poll());
            }

            res =  Math.max(res, total[u]);

            pq.clear();
        }

        System.out.println(res);

        br.close();
    }
}
