import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[] items = new int[N + 1];
		int[][] graph = new int[N + 1][N + 1];
		final int INF = Integer.MAX_VALUE / 2;
		int u;
		int v;
		int w;
		int max = -1;
		int sum;

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = INF;
			}

			graph[i][i] = 0;
		}

		while (R-- > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			graph[u][v] = w;
			graph[v][u] = w;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			sum = 0;

			for (int j = 1; j <= N; j++) {
				if (graph[i][j] <= M) {
					sum += items[j];
				}
			}

			max = Math.max(max, sum);
		}

		System.out.println(max);
    }
}
