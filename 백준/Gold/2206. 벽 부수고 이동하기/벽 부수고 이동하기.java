import java.io.*;
import java.util.*;

/**
 * 풀이 방법
 *
 * 1. bfs를 수행하며 과거 벽 부숨 여부와 다음 지점의 벽 여부를 통해 방문 지점의 벽 부숨 여부 갱신
 * 2. 이전에 벽을 부수고 방문한 지점을 벽을 부수지 않고 갈 수 있는 경우, 재탐색 필요
 *    (시간은 증가만 하기 때문에 고려 X)
 * 3. 목적지는 항상 최솟값으로 갱신
 */
public class Main {
    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] map = new String[N];
        int[][] dist = new int[N][M];
        boolean[][] broken = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = 1;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));

        Node curr;
        int nr, nc;
        int t = 1;
        boolean isWall;
        int size;

        while (!q.isEmpty()) {
            t++;
            size = q.size();

            for (int i = 0; i < size; i++) {
                curr = q.poll();

                for (int[] dir : dirs) {
                    nr = curr.r + dir[0];
                    nc = curr.c + dir[1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    isWall = map[nr].charAt(nc) == '1';

                    // 다음 지점이 벽이지만, 더 이상 벽을 부술 수 없는 경우
                    if (broken[curr.r][curr.c] && isWall) {
                        continue;
                    }

                    // 이미 방문한 지점에 대한 처리
                    // 이전에 벽을 부수고 방문한 지점을 벽을 부수지 않고 갈 수 있는 경우, 재탐색 필요
                    if (dist[nr][nc] != Integer.MAX_VALUE) {
                        // 벽 부숨 여부가 동일한 경우
                        if (broken[nr][nc] == (broken[curr.r][curr.c] || isWall)) {
                            continue;
                        }

                        // 벽을 부수지 않고 갈 수 있는 지점을 부수고 가는 경우
                        if (!broken[nr][nc] && broken[curr.r][curr.c]) {
                            continue;
                        }
                    }
                    
                    if (nr == N - 1 && nc == M - 1) {
                        dist[nr][nc] = Math.min(dist[nr][nc], t);
                    } else {
                        dist[nr][nc] = t;
                    }

                    broken[nr][nc] = broken[curr.r][curr.c] || isWall;
                    q.add(new Node(nr, nc));
                }
            }
        }

        System.out.println(dist[N - 1][M - 1] != Integer.MAX_VALUE ? dist[N - 1][M - 1] : -1);

        br.close();
    }
}
