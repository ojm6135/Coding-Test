import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N;
        int rs, cs;
        int rd, cd;
        int r, c;
        int nr, nc;
        StringTokenizer st;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[300][300];
        int cnt, size;

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            rs = Integer.parseInt(st.nextToken());
            cs = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            rd = Integer.parseInt(st.nextToken());
            cd = Integer.parseInt(st.nextToken());

            q.add(new int[] {rs, cs});
            cnt = -1;

            while (!q.isEmpty() && !visited[rd][cd]) {
                size = q.size();
                cnt++;

                for (int i = 0; i < size; i++) {
                    r = q.peek()[0];
                    c = q.poll()[1];

                    if (visited[r][c]) {
                        continue;
                    }

                    visited[r][c] = true;

                    if (r == rd && c == cd) {
                        break;
                    }

                    for (int[] dir : dirs) {
                        nr = r + dir[0];
                        nc = c + dir[1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            continue;
                        }

                        q.add(new int[]{nr, nc});
                    }
                }
            }

            q.clear();

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], 0, N, false);
            }

            System.out.println(cnt);
        }

        br.close();
    }
}
