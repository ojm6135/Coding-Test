import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];
        int u, v;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], N);
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }

        int sum;
        int minSum = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            sum = 0;
            
            for (int j = 1; j <= N; j++) {
                sum += graph[i][j];
            }

            if (sum < minSum) {
                minSum = sum;
                min = i;
            }
        }

        System.out.println(min);

        br.close();
    }
}
