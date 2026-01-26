import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] trees = new int[N];
        int lo = 1;
        int hi = Integer.MIN_VALUE;
        int h = 0, i;

        st = new StringTokenizer(br.readLine().trim());

        for (i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > hi) {
                hi = trees[i];
            }
        }

        long sum;
        int res = 0;

        while (lo <= hi) {
            sum = 0;
            h = lo + (hi - lo) / 2;

            for (i = 0; i < N; i++) {
                sum += Math.max(trees[i] - h, 0);
            }

            if (sum == M) {
                res = h;
                break;
            }

            if (sum < M) {
                hi = h - 1;
            } else {
                res = h;
                lo = h + 1;
            }
        }

        System.out.println(res);

        br.close();
    }
}
