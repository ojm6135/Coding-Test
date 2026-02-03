import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] cnt = new int[100_001];

        Arrays.fill(cnt, -1);
        cnt[N] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        int curr, next;

        while (!q.isEmpty()) {
            curr = q.poll();

            if (curr == K) {
                break;
            }

            next = curr * 2;
            if (next <= 100_000 && cnt[next] == -1) {
                q.add(next);
                cnt[next] = cnt[curr];
            }

            next = curr - 1;
            if (next >= 0 && cnt[next] == -1) {
                q.add(next);
                cnt[next] = cnt[curr] + 1;
            }

            next = curr + 1;
            if (next <= 100_000 && cnt[next] == -1) {
                q.add(next);
                cnt[next] = cnt[curr] + 1;
            }
        }

        System.out.println(cnt[K]);

        br.close();
    }
}
