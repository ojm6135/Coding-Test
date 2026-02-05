import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fruits = new int[N];

        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int res = 1;
        int[] kinds = { fruits[0], -1 };
        int[] lastPos = {0, -1};

        for (r = 1; r < N; r++) {
            res++;

            if (fruits[r] != kinds[0]) {
                kinds[1] = fruits[r];
                lastPos[1] = r;
                break;
            }

            lastPos[0] = r;
        }

        while (++r < N) {
            // 이미 선택된 종류와 같은 종류
            if (fruits[r] == kinds[0] || fruits[r] == kinds[1]) {
                if (fruits[r] == kinds[0]) {
                    lastPos[0] = r;
                } else {
                    lastPos[1] = r;
                }

                res = Math.max(res, r - l + 1);
                continue;
            }

            // 과일의 종류가 2개를 초과
            if (fruits[r - 1] == kinds[0]) {
                l = lastPos[1] + 1;
                kinds[1] = fruits[r];
                lastPos[1] = r;
            } else {
                l = lastPos[0] + 1;
                kinds[0] = fruits[r];
                lastPos[0] = r;
            }

            res = Math.max(res, r - l + 1);
        }

        System.out.println(res);

        br.close();
    }
}
