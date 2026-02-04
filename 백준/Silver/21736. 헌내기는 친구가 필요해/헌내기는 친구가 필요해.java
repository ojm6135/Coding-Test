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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] campus = new String[N];
        int startR = -1;
        int startC = -1;
        boolean flag = false;
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            campus[i] = br.readLine();
            for (int j = 0; j < M && !flag; j++) {
                if (campus[i].charAt(j) == 'I') {
                    startR = i;
                    startC = j;
                    flag = true;
                }
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startR, startC));

        Node curr;
        int nr, nc;
        int cnt = 0;

        while (!q.isEmpty()) {
            curr = q.poll();

            if (visited[curr.r][curr.c]) {
                continue;
            }

            visited[curr.r][curr.c] = true;

            if (campus[curr.r].charAt(curr.c) == 'P') {
                cnt++;
            }

            for (int[] dir : dirs) {
                nr =  curr.r + dir[0];
                nc = curr.c + dir[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (campus[nr].charAt(nc) == 'X') {
                    continue;
                }

                q.add(new Node(nr, nc));
            }
        }

        System.out.println(cnt != 0 ? cnt : "TT");

        br.close();
    }
}
