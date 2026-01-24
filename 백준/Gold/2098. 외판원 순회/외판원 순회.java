import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        // w[i][j]: 인접한 정점 i, j 사이의 비용
        // d[i][{...}]: 정점 i로부터 부분 집합 내 정점들을 순회한 후, 시작 정점으로 가는 최소 비용
        int[][] w, d;
        int i, j;
        int bitmap;  // 집합 표현

        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        d = new int[n][(1 << (n - 1))];

        for (i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(s[j]);
            }
        }

        // 초기화 (시작 정점: 0)
        for (i = 1; i < n; i++) {
            d[i][0] = w[i][0];  // d의 0: 공집합을 의미, w의 0: 정점 번호
        }

        // d[1...n-1][{1, ..., n-1}] 최소 비용 계산
        // i: 부분 집합에 포함되지 않는 정점
        // j: 부분 집합에 포함되는 정점
        for (bitmap = 1; bitmap < (1 << (n - 1)); bitmap++) {
            for (i = 1; i < n; i++) {
                if ((~bitmap & (1 << (i - 1))) == 0) {
                    continue;
                }

                for (j = 1; j < n; j++) {
                    if ((bitmap & (1 << (j - 1))) == 0) {
                        continue;
                    }
                    if (w[i][j] == 0 || d[j][bitmap ^ (1 << (j - 1))] == 0) {
                        continue;
                    }

                    int cost = w[i][j] + d[j][bitmap ^ (1 << (j - 1))];
                    d[i][bitmap] = (d[i][bitmap] == 0) ? cost : Math.min(d[i][bitmap], cost);
                }
            }
        }

        // d[0][{1, ..., n-1}]
        bitmap--;
        for (j = 1; j < n; j++) {
            if ((bitmap & (1 << (j - 1))) == 0) {
                continue;
            }
            if (w[0][j] == 0 || d[j][bitmap ^ (1 << (j - 1))] == 0) {
                continue;
            }

            int cost = w[0][j] + d[j][bitmap ^ (1 << (j - 1))];
            d[0][bitmap] = (d[0][bitmap]) == 0 ? cost : Math.min(d[0][bitmap], cost);
        }

        System.out.println(d[0][(1 << (n - 1)) - 1]);

        br.close();
    }
}