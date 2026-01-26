import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] src = new boolean[N][M];
        boolean[][] dest = new boolean[N][M];
        String s;
        int cnt = 0;
        boolean isSame = true;

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < M; j++) {
                src[i][j] = s.charAt(j) == '1';
            }
        }

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < M; j++) {
                dest[i][j] = s.charAt(j) == '1';
            }
        }

        if (N < 3 || M < 3) {
            for (int i = 0; i < N && isSame; i++) {
                for (int j = 0; j < M; j++) {
                    if (src[i][j] != dest[i][j]) {
                        isSame = false;
                        cnt = -1;
                        break;
                    }
                }
            }

            System.out.println(cnt);
            br.close();
            return;
        } 

        for (int i = 0; i < N && isSame; i++) {
            for (int j = 0; j < M; j++) {
                if (i > N - 3 || j > M - 3) {
                    if (src[i][j] != dest[i][j]) {
                        isSame = false;
                        cnt = -1;
                        break;
                    }
                }

                if (src[i][j] == dest[i][j]) {
                    continue;
                }

                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        src[k][l] = !src[k][l];
                    }
                }

                cnt++;
            }
        }

        System.out.println(cnt);

        br.close();
    }
}
