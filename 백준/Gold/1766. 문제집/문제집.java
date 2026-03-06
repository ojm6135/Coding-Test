import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] graph = new ArrayList[N + 1];
		int[] inDegree = new int[N + 1];
		int A;
		int B;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int prob;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			graph[A].add(B);
			inDegree[B]++;
		}

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				pq.add(i);
			}
		}

		while (!pq.isEmpty()) {
			prob = pq.poll();
			sb.append(prob).append(" ");

			for (int p : graph[prob]) {
				if (--inDegree[p] == 0) {
					pq.add(p);
				}
			}
		}

		System.out.println(sb);
    }
}
