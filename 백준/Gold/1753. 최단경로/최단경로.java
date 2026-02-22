import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine().trim());
        List<Edge>[] graph = new ArrayList[V + 1];
        int u, v, w;

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        boolean[] visited = new boolean[V + 1];
        visited[K] = true;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(graph[K]);

        int[] cost = new int[V + 1];

        Edge next;
        while (!pq.isEmpty()) {
            next = pq.poll();

            if (visited[next.to]) {
                continue;
            }

            visited[next.to] = true;
            cost[next.to] = next.weight;

            // 갱신
            for (Edge e : graph[next.to]) {
                if (!visited[e.to]) {
                    pq.add(new Edge(e.to, e.weight + next.weight));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (v = 1; v < cost.length; v++) {
            if (v == K) {
                sb.append(0).append("\n");
                continue;
            }

            if (cost[v] == 0) {
                sb.append("INF").append("\n");
                continue;
            }

            sb.append(cost[v]).append("\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
