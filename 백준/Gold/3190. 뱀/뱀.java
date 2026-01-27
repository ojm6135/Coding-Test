import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;  // 1: 뱀, 2: 사과

    enum DIR {
        U(-1, 0, 0),
        R(0, 1, 1),
        D(1, 0, 2),
        L(0, -1, 3);

        private final int dr;
        private final int dc;
        private final int idx;
        private static final DIR[] values = {U, R, D, L};

        DIR(int dr, int dc, int idx) {
            this.dr = dr;
            this.dc = dc;
            this.idx = idx;
        }

        // 좌회전 시 진행 방향 계산
        public static DIR turnLeft(DIR from) {
            return values[(from.idx + 3) % 4];
        }

        // 우회전 시 진행 방향 계산
        public static DIR turnRight(DIR from) {
            return values[(from.idx + 1) % 4];
        }
    }

    /**
     * @param dir 진행할 방향
     * @param r 현재 행
     * @param c 현재 열
     * @return 충돌 여부
     */
    static boolean isCollided(int r, int c, DIR dir) {
        int nr = r + dir.dr;
        int nc = c + dir.dc;

        if (dir == DIR.U && nr < 0) {
            return true;
        }

        if (dir == DIR.D && nr > N - 1) {
            return true;
        }

        if (dir == DIR.L && nc < 0) {
            return true;
        }

        if (dir == DIR.R && nc > N - 1) {
            return true;
        }

        return board[nr][nc] == 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        int K = Integer.parseInt(br.readLine().trim());
        board = new int[N][N];
        Map<Integer, Character> rotations = new HashMap<>();
        int r, c;
        StringTokenizer st;
        int t;
        DIR dir;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            r = Integer.parseInt(st.nextToken().trim());
            c = Integer.parseInt(st.nextToken().trim());

            board[r - 1][c - 1] = 2;
        }

        int L = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine().trim());
            t = Integer.parseInt(st.nextToken().trim());
            rotations.put(t, st.nextToken().trim().charAt(0));
        }

        Queue<DIR> history = new ArrayDeque<>();
        int[] tail = {0, 0};  // r, c

        t = 0;
        r = 0;
        c = 0;
        dir = DIR.R;
        board[0][0] = 1;

        while (++t > 0) {
            if (isCollided(r, c, dir)) {
                break;
            }

            r += dir.dr;
            c += dir.dc;

            history.add(dir);

            switch (rotations.getOrDefault(t, ' ')) {
                case 'L':
                    dir = DIR.turnLeft(dir);
                    break;
                case 'D':
                    dir = DIR.turnRight(dir);
            }

            if (board[r][c] == 2) {
                board[r][c] = 1;
                continue;
            }

            board[tail[0]][tail[1]] = 0;
            board[r][c] = 1;
            tail[0] += history.peek().dr;
            tail[1] += history.poll().dc;
        }

        System.out.println(t);

        br.close();
    }
}
