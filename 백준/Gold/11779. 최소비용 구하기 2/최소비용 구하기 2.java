import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
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

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new ArrayList[N + 1];
        StringTokenizer st;
        int u, v, w;
        int src, dest;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Edge edge;
        int totalCost = 0;
        int[] prev = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(u, v, w));
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
        prev[src] = -1;
        visited[src] = true;
        pq.addAll(graph[src]);

        while (!pq.isEmpty()) {
            edge = pq.poll();

            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            prev[edge.to] = edge.from;

            if (edge.to == dest) {
                totalCost = edge.weight;
                break;
            }

            for (Edge e : graph[edge.to]) {
                if (!visited[e.to]) {
                    pq.add(new Edge(e.from, e.to, e.weight + edge.weight));
                }
            }
        }

        StringBuilder path = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int cityCnt = 0;

        for (int i = dest; i != -1; i = prev[i]) {
            stack.push(i);
            cityCnt++;
        }

        path.append(stack.pop());

        while (!stack.isEmpty()) {
            path.append(" ").append(stack.pop());
        }

        System.out.println(totalCost);
        System.out.println(cityCnt);
        System.out.println(path);

        br.close();
    }
}
