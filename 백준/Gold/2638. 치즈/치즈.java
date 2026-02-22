import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] grid;
    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        Queue<int[]> cheeseQ = new ArrayDeque<>();
        Queue<int[]> airQ = new ArrayDeque<>();
        int[] cheese;
        int[] air;
        int nr;
        int nc;
        int adjR;
        int adjC;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈 내외부 구분
        // 내부: 0, 외부: -1
        cheeseQ.add(new int[]{0, 0});

        while (!cheeseQ.isEmpty()) {
            cheese = cheeseQ.poll();

            if (grid[cheese[0]][cheese[1]] == -1) {
                continue;
            }

            grid[cheese[0]][cheese[1]] = -1;

            for (int[] dir : dirs) {
                nr = cheese[0] + dir[0];
                nc = cheese[1] + dir[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (grid[nr][nc] == 0) {
                    cheeseQ.add(new int[]{nr, nc});
                }
            }
        }

        // 최초로 녹는 치즈
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (grid[r][c] == 1 && shouldMelt(r, c, false)) {
                    cheeseQ.add(new int[]{r, c});
                }
            }
        }

        int t = 0;
        int size;

        while (!cheeseQ.isEmpty()) {
            t++;
            size = cheeseQ.size();

            for (int i = 0; i < size; i++) {
                cheese = cheeseQ.poll();

                if (grid[cheese[0]][cheese[1]] == -1) {
                    continue;
                }

                grid[cheese[0]][cheese[1]] = -1;

                // 현재 치즈와 인접한 치즈 검사
                for (int[] dir : dirs) {
                    nr = cheese[0] + dir[0];
                    nc = cheese[1] + dir[1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    if (grid[nr][nc] == 1 && shouldMelt(nr, nc, true)) {
                        cheeseQ.add(new int[]{nr, nc});
                    }
                }

                // 현재 치즈가 녹음으로써 내부에 외부 공기가 유입
                for (int[] dir : dirs) {
                    adjR = cheese[0] + dir[0];
                    adjC = cheese[1] + dir[1];

                    if (adjR < 0 || adjR >= N || adjC < 0 || adjC >= M) {
                        continue;
                    }

                    if (grid[adjR][adjC] == 0) {
                        airQ.add(new int[]{adjR, adjC});
                    }
                }

                while (!airQ.isEmpty()) {
                    air = airQ.poll();

                    if (grid[air[0]][air[1]] == -1) {
                        continue;
                    }

                    grid[air[0]][air[1]] = -1;

                    for (int[] dir : dirs) {
                        nr = air[0] + dir[0];
                        nc = air[1] + dir[1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            continue;
                        }

                        if (grid[nr][nc] == 0) {
                            airQ.add(new int[]{nr, nc});
                        }

                        if (grid[nr][nc] == 1 && shouldMelt(nr, nc, true)) {
                            cheeseQ.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        System.out.println(t);

        br.close();
    }

    static boolean shouldMelt(int r, int c, boolean isCascade) {
        int exAirCnt = 0;
        int adjR;
        int adjC;

        for (int[] dir : dirs) {
            adjR = r + dir[0];
            adjC = c + dir[1];

            if (adjR < 0 || adjR >= N || adjC < 0 || adjC >= M) {
                continue;
            }

            if (grid[adjR][adjC] == -1) {
                exAirCnt++;
            }
        }

        if (isCascade) {
            return exAirCnt == 2;
        }

        return exAirCnt >= 2;
    }
}
