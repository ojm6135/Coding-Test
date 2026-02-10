import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l, r;
        int resL = 0, resR = 0;
        int res = Integer.MAX_VALUE;
        int sum;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        l = 0;
        r = N - 1;

        while (l < r) {
            sum = arr[l] + arr[r];

            if (Math.abs(sum) < res) {
                res = Math.abs(sum);
                resL = l;
                resR = r;
            }

            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                break;
            }
        }

        System.out.printf("%d %d", arr[resL], arr[resR]);

        br.close();
    }
}
