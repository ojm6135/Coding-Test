import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] cnt = new int[100_001];
        boolean[] visited = new boolean[100_001];

        List<Function<Integer, Integer>> functions = Arrays.asList(
            x -> x * 2,
            x -> x - 1,
            x -> x + 1
        );
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[N] = true;

        int curr, next;

        while (!q.isEmpty()) {
            curr = q.poll();

            if (curr == K) {
                break;
            }

            for (int i = 0; i < functions.size(); i++) {
                next = functions.get(i).apply(curr);

                if (next < 0 || next > 100_000) {
                    continue;
                }

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.add(next);
                cnt[next] = cnt[curr];

                if (i > 0) {
                    cnt[next]++;
                }
            }
        }

        System.out.println(cnt[K]);

        br.close();
    }
}
