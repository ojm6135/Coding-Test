import java.io.*;
import java.util.*;

public class Main {
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] dist = new int[N + 1];
        PriorityQueue<Edge> toPartyQ = new PriorityQueue<>();
        PriorityQueue<Edge> toHomeQ = new PriorityQueue<>();
        List<Edge>[] graphToParty = new ArrayList[N + 1];
        List<Edge>[] graphToHome = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];
        int cnt;
        int u, v, w;
        Edge edge;

        for (int i = 1; i <= N; i++) {
            graphToParty[i] = new ArrayList<>();
            graphToHome[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graphToParty[v].add(new Edge(u, w));
            graphToHome[u].add(new Edge(v, w));
        }

        // 파티 가는 길
        toPartyQ.addAll(graphToParty[X]);
        visited[X] = true;
        cnt = 1;

        while (cnt < N) {
            edge = toPartyQ.poll();

            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            dist[edge.to] = edge.weight;
            cnt++;

            for (Edge n : graphToParty[edge.to]) {
                if (!visited[n.to]) {
                    toPartyQ.add(new Edge(n.to, n.weight + edge.weight));
                }
            }
        }

        Arrays.fill(visited, false);
        visited[X] = true;
        cnt = 1;

        // 집 가는 길
        toHomeQ.addAll(graphToHome[X]);

        while (cnt < N) {
            edge = toHomeQ.poll();

            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            dist[edge.to] += edge.weight;
            cnt++;

            for (Edge n : graphToHome[edge.to]) {
                if (!visited[n.to]) {
                    toHomeQ.add(new Edge(n.to, n.weight + edge.weight));
                }
            }
        }

        int max = -1;

        for (int dst : dist) {
            max = Math.max(max, dst);
        }

        System.out.println(max);

        br.close();
    }
}
