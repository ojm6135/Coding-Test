import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Edge>[] edges;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

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

        final int INF = Integer.MAX_VALUE / 2;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];
        edges = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        int u, v, w;
        int must1, must2;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                graph[i][j] = INF;
            }

            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v], w);
            graph[v][u] = Math.min(graph[u][v], w);
        }

        st = new StringTokenizer(br.readLine());
        must1 = Integer.parseInt(st.nextToken());
        must2 = Integer.parseInt(st.nextToken());
        br.close();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != INF) {
                    edges[i].add(new Edge(j, graph[i][j]));
                }
            }
        }

        int totalCost = INF;

        // 1 -> must1 -> must2 -> N
        int srcToMust1 = dijkstra(1, must1);
        
        if (srcToMust1 != -1) {
            int must1ToMust2 = dijkstra(must1, must2);

            if (must1ToMust2 != -1) {
                int must2ToDest = dijkstra(must2, N);

                if (must2ToDest != -1) {
                   totalCost = srcToMust1 + must1ToMust2 + must2ToDest;
                }
            }
        }

        // 1 -> must2 -> must1 -> N
        int srcToMust2 = dijkstra(1, must2);
        
        if (srcToMust2 != -1) {
            int must2ToMust1 = dijkstra(must2, must1);

            if (must2ToMust1 != -1) {
                int must1ToDest = dijkstra(must1, N);

                if (must1ToDest != -1) {
                    totalCost = Math.min(totalCost, srcToMust2 + must2ToMust1 + must1ToDest);
                }
            }
        }
        
        System.out.println(totalCost != INF ? totalCost : -1);
    }

    static int dijkstra(int src, int dest) {
        if (src == dest) {
            return 0;
        }

        pq.addAll(edges[src]);
        visited[src] = true;

        Edge edge;
        int cnt = 0;
        int cost = -1;

        while (!pq.isEmpty() && cnt < N) {
            edge = pq.poll();

            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            cnt++;

            if (edge.to == dest) {
                cost = edge.weight;
                break;
            }

            for (Edge e : edges[edge.to]) {
                if (!visited[e.to]) {
                    pq.add(new Edge(e.to, e.weight + edge.weight));
                }
            }
        }

        pq.clear();
        Arrays.fill(visited, false);

        return cost;
    }
}
