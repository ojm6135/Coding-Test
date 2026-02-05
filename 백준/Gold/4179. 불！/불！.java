import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] maze = new int[R][C];
        int[] jpos;
        int[] fpos;
        String s;
        Queue<int[]> jq = new ArrayDeque<>();
        Queue<int[]> fq = new ArrayDeque<>();

        for (int r = 0; r < R; r++) {
            s = br.readLine();

            for (int c = 0; c < C; c++) {
                switch (s.charAt(c)) {
                    case '#':
                        maze[r][c] = Integer.MAX_VALUE;
                        break;
                    case 'J':
                        jq.add(new int[]{r, c});
                        break;
                    case 'F':
                        maze[r][c] = Integer.MIN_VALUE;
                        fq.add(new int[]{r, c});
                }
            }
        }

        int t = 0;
        int jsize;
        int fsize;
        int nr, nc;
        boolean isEscaped = false;
        long start = System.currentTimeMillis();
        // MIN_VALUE: 불이 지나간 자리
        // MAX_VALUE: 벽
        // -1: 지훈이가 지나간 자리 (== visited)
        while (!jq.isEmpty() && !isEscaped) {
            t++;
            fsize = fq.size();

            // 불 확산
            while (fsize-- > 0) {
                fpos = fq.poll();

                for (int[] dir : dirs) {
                    nr = fpos[0] + dir[0];
                    nc = fpos[1] + dir[1];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                        continue;
                    }

                    if (maze[nr][nc] == 0) {
                        maze[nr][nc] = Integer.MIN_VALUE;
                        fq.add(new int[]{nr, nc});
                    }
                }
            }

            // 지훈이 이동
            jsize = jq.size();

            while (jsize-- > 0) {
                jpos = jq.poll();

                if (maze[jpos[0]][jpos[1]] == -1) {
                    continue;
                }

                // 탈출
                if (jpos[0] == 0 || jpos[0] == R - 1 || jpos[1] == 0 || jpos[1] == C - 1) {
                    isEscaped = true;
                    break;
                }

                maze[jpos[0]][jpos[1]] = -1;

                for (int[] dir : dirs) {
                    nr = jpos[0] + dir[0];
                    nc = jpos[1] + dir[1];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                        continue;
                    }

                    if (maze[nr][nc] == 0) {
                        jq.add(new int[]{nr, nc});
                    }
                }
            }
        }

        System.out.println(isEscaped ? t : "IMPOSSIBLE");

        br.close();
    }
}
