import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int[][] room;
    static int airCleanerTop;
    static int airCleanerBottom;
    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static class Dust {
        int r;
        int c;
        int amount;

        public Dust(int r, int c, int amount) {
            this.r = r;
            this.c = c;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        airCleanerTop = -1;
        airCleanerBottom = -1;
        Queue<Dust> q = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());

                if (room[i][j] > 0) {
                    q.add(new Dust(i, j, room[i][j]));
                    continue;
                }

                if (room[i][j] == -1) {
                    if (airCleanerTop == -1) {
                        airCleanerTop = i;
                    } else {

                        airCleanerBottom = i;
                    }

                    room[i][j] = 0;
                }
            }
        }

        int size;
        Dust dust;
        int spreadAmount;
        int spreadCnt;
        int spreadR;
        int spreadC;

        while (!q.isEmpty()) {
            size = q.size();

            if (T-- == 0) {
                break;
            }

            for (int i = 0; i < size; i++) {
                dust = q.poll();
                spreadAmount = dust.amount / 5;
                spreadCnt = 0;

                if (spreadAmount == 0) {
                    continue;
                }

                for (int[] dir : dirs) {
                    spreadR = dust.r + dir[0];
                    spreadC = dust.c + dir[1];

                    if (spreadR < 0 || spreadR >= R || spreadC < 0 || spreadC >= C) {
                        continue;
                    }

                    if ((spreadR == airCleanerTop || spreadR == airCleanerBottom) && spreadC == 0) {
                        continue;
                    }

                    room[spreadR][spreadC] += spreadAmount;
                    spreadCnt++;
                }

                room[dust.r][dust.c] -= spreadAmount * spreadCnt;
            }

            operateAirCleaner();

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (room[r][c] > 0) {
                        q.add(new Dust(r, c, room[r][c]));
                    }
                }
            }
        }

        int totalDustAmount = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                totalDustAmount += room[r][c];
            }
        }

        System.out.println(totalDustAmount);

        br.close();
    }

    static void operateAirCleaner() {
        int r;
        int c;

        // 상단 - 반시계 방향
        for (r = airCleanerTop - 1; r > 0; r--) {
            room[r][0] = room[r - 1][0];
        }

        for (c = 0; c < C - 1; c++) {
            room[0][c] = room[0][c + 1];
        }

        for (r = 0; r < airCleanerTop; r++) {
            room[r][c] = room[r + 1][c];
        }

        for (c = C - 1; c > 0; c--) {
            room[r][c] = room[r][c - 1];
        }

        // 하단 - 시계 방향
        for (r = airCleanerBottom + 1; r < R - 1; r++) {
            room[r][0] = room[r + 1][0];
        }

        for (c = 0; c < C - 1; c++) {
            room[r][c] = room[r][c + 1];
        }

        for (r = R - 1; r > airCleanerBottom; r--) {
            room[r][c] = room[r - 1][c];
        }

        for (c = C - 1; c > 0; c--) {
            room[r][c] = room[r][c - 1];
        }
    }
}
