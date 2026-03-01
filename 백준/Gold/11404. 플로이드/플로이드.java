import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] graph = new int[N + 1][N + 1];
        int u, v, w;
        StringTokenizer st;
        final int INF = 100_000_000;

        for (int[] g : graph) {
            Arrays.fill(g, INF);
        }

        for (int i = 1; i <= N; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v], w);
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (graph[i][j] == INF) {
                    sb.append(0);
                } else {
                    sb.append(graph[i][j]);
                }

                sb.append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}
