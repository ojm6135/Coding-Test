import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int R;
    private static int C;
    private static String[] board;
    private static boolean[] visited;
    private static int res = 1;
    private static final int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new String[R];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().trim();
        }

        solve(0, 0, 0);
        System.out.println(res);

        br.close();
    }

    static void solve(int r, int c, int cnt) {
        if (r < 0 || r >= R || c < 0 || c >= C) {
            return;
        }

        char ch = board[r].charAt(c);

        if (visited[ch - 'A']) {
            res = Math.max(res, cnt);
            return;
        }

        visited[ch - 'A'] = true;

        for (int[] dir : dirs) {
            solve(r + dir[0], c + dir[1], cnt + 1);
        }

        visited[ch - 'A'] = false;
    }
}
