import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid;
    static int[] cnt = {0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, N - 1, 0, N - 1);

        System.out.println(cnt[0]);
        System.out.println(cnt[1]);

        br.close();
    }

    static void solve(int r1, int r2, int c1, int c2) {
        if (r1 < 0 || r2 < 0 || c1 < 0 || c2 < 0) {
            return;
        }

        if (r1 >= N || r2 >= N || c1 >= N || c2 >= N) {
            return;
        }

        int base = grid[r1][c1];
        int rm = (r1 + r2) / 2;
        int cm = (c1 + c2) / 2;

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (grid[r][c] != base) {
                    solve(r1, rm, c1, cm);
                    solve(r1, rm, cm + 1, c2);
                    solve(rm + 1, r2, c1, cm);
                    solve(rm + 1, r2, cm + 1, c2);
                    return;
                }
            }
        }

        cnt[base]++;
    }
}
