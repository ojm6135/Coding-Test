import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        int r, c;

        for (r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (r = 1; r < N; r++) {
            arr[r][0] += arr[r - 1][0];
        }

        for (c = 1; c < N; c++) {
            arr[0][c] += arr[0][c - 1];
        }

        for (r = 1; r < N; r++) {
            for (c = 1; c < N; c++) {
                arr[r][c] += arr[r - 1][c] + arr[r][c - 1] - arr[r - 1][c - 1];
            }
        }

        int r1, c1, r2, c2;
        int res;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken()) - 1;
            c1 = Integer.parseInt(st.nextToken()) - 1;
            r2 = Integer.parseInt(st.nextToken()) - 1;
            c2 = Integer.parseInt(st.nextToken()) - 1;

            res = arr[r2][c2];

            if (r1 > 0) {
                res -= arr[r1 - 1][c2];
            }

            if (c1 > 0) {
                res -= arr[r2][c1 - 1];
            }

            if (r1 > 0 && c1 > 0) {
                res += arr[r1 - 1][c1 - 1];
            }

            bw.write(res + "\n");
        }

        br.close();
        bw.close();
    }
}
