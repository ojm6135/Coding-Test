import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int t = -1;
        int size;
        int curr;
        int next;
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> visitedQ = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];
        int[] cnt = new int[100_001];
        boolean finished = false;

        q.add(N);
        visited[N] = true;
        cnt[N] = 1;

        while (!q.isEmpty() && !finished) {
            t++;
            size = q.size();

            while (size-- > 0) {
                curr = q.poll();

                if (curr == K) {
                    finished = true;
                    continue;
                }

                next = curr - 1;
                if (next >= 0 && !visited[next]) {
                    cnt[next]++;
                    q.add(next);
                    visitedQ.add(next);
                }

                next = curr + 1;
                if (next <= 100_000 && !visited[next]) {
                    cnt[next]++;
                    q.add(next);
                    visitedQ.add(next);
                }

                next = curr * 2;
                if (next <= 100_000 && !visited[next]) {
                    cnt[next]++;
                    q.add(next);
                    visitedQ.add(next);
                }
            }

            while (!visitedQ.isEmpty()) {
                visited[visitedQ.poll()] = true;
            }
        }

        System.out.println(t);
        System.out.println(cnt[K]);

        br.close();
    }
}
