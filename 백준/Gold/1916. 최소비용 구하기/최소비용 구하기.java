import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Node>[] nodes = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];
        int u, v, w;
        StringTokenizer st;
        int src, dest;

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            nodes[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        if (src == dest) {
            System.out.println(0);
            br.close();
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.addAll(nodes[src]);
        visited[src] = true;

        Node curr;

        while (!pq.isEmpty()) {
            curr = pq.poll();

            if (curr.to == dest) {
                System.out.println(curr.weight);
                break;
            }

            if (visited[curr.to]) {
                continue;
            }

            visited[curr.to] = true;

            for (Node next : nodes[curr.to]) {
                if (!visited[next.to]) {
                    pq.add(new Node(next.to, curr.weight + next.weight));
                }
            }
        }

        br.close();
    }
}
