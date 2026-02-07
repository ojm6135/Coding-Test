import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        int[] dp = new int[N];
        int res = 0;

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (seq[i] > seq[j]) {
                    if (dp[i] <= dp[j]) {
                        dp[i] = dp[j] + 1;
                        res = Math.max(res, dp[i]);
                    }
                }
            }
        }

        System.out.println(res + 1);

        br.close();
    }
}
