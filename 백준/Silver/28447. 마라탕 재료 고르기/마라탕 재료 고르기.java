import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] materials;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        materials = new int[N][N];
        visited = new boolean[N];
        int max = Integer.MIN_VALUE;

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

        for (int i = 0; i < N; i++) {
            max = Math.max(max, dfs(new Stack<>(), i, 0));
            Arrays.fill(visited, false);
        }

        System.out.println(max);

        br.close();
    }

    static int dfs(Stack<Integer> stack, int picked, int score) {
        for (int m : stack) {
            score += materials[picked][m];
        }

        stack.push(picked);
        visited[picked] = true;

        if (stack.size() == M) {
            stack.pop();
            visited[picked] = false;
            return score;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                max = Math.max(max, dfs(stack, i, score));
            }
        }

        stack.pop();
        visited[picked] = false;

        return max;
    }
}
