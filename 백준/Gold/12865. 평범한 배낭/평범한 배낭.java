import java.io.*;
import java.util.*;

public class Main {
    static int[][] items;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        items = new int[N + 1][2];
        memo = new int[N + 1][K + 1];
        int w, v;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            items[i][0] = w;
            items[i][1] = v;
        }

        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        int maxValue = zeroOneKnapsack(N, K);

        System.out.println(maxValue);

        br.close();
    }

    static int zeroOneKnapsack(int item, int weight) {
        if (item == 0 || weight == 0) {
            return 0;
        }

        if (memo[item][weight] != -1) {
            return memo[item][weight];
        }

        int notInclude = zeroOneKnapsack(item - 1, weight);

        if (items[item][0] > weight) {
            memo[item][weight] = notInclude;
            return notInclude;
        }

        int include = zeroOneKnapsack(item - 1, weight - items[item][0]);
        int res = Math.max(notInclude, include + items[item][1]);

        memo[item][weight] = res;

        return res;
    }
}
