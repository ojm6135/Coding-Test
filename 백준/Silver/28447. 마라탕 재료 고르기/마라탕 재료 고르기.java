import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] materials;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        materials = new int[N][N];
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                materials[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (M == 1) {
            System.out.println(0);
            br.close();
            return;
        }

        for (int i = 0; i <= N - M; i++) {
            res = Math.max(res, dfs(new Stack<>(), i + 1, i, 0));
        }

        System.out.println(res);

        br.close();
    }

    static int dfs(Stack<Integer> stack, int start, int picked, int score) {
        for (int m : stack) {
            score += materials[picked][m];
        }

        stack.push(picked);

        if (stack.size() == M) {
            stack.pop();
            return score;
        }

        int max = Integer.MIN_VALUE;

        for (int i = start; i < N; i++) {
            max = Math.max(max, dfs(stack, i + 1, i, score));
        }

        stack.pop();

        return max;
    }
}
