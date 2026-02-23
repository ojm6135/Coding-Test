import java.io.*;
import java.util.*;

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
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        String[] map = new String[N];
        int[][] brokenCnt = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
            Arrays.fill(brokenCnt[i], Integer.MAX_VALUE);
        }

        brokenCnt[0][0] = 0;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));

        Node curr;
        int nr, nc;
        boolean isWall;
        int currCnt;

        while (!q.isEmpty()) {
            curr = q.poll();

            for (int[] dir : dirs) {
                nr = curr.r + dir[0];
                nc = curr.c + dir[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                isWall = map[nr].charAt(nc) == '1';
                currCnt = brokenCnt[curr.r][curr.c] + (isWall ? 1 : 0);

                if (currCnt < brokenCnt[nr][nc]) {
                    brokenCnt[nr][nc] = currCnt;
                    q.add(new Node(nr, nc));
                }
            }
        }

        System.out.println(brokenCnt[N - 1][M - 1]);

        br.close();
    }
}
