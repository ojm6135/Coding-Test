import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] grid = new int[N + 1][3];
        int[][] dpMax = new int[N + 1][3];
        int[][] dpMin = new int[N + 1][3];
        int[] prev;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i <= N; i++) {
            prev = dpMax[i - 1];
            dpMax[i][0] = Math.max(prev[0], prev[1]) + grid[i][0];
            dpMax[i][1] = Math.max(prev[0], Math.max(prev[1], prev[2])) + grid[i][1];
            dpMax[i][2] = Math.max(prev[2], prev[1]) + grid[i][2];

            prev = dpMin[i - 1];
            dpMin[i][0] = Math.min(prev[0], prev[1]) + grid[i][0];
            dpMin[i][1] = Math.min(prev[0], Math.min(prev[1], prev[2])) + grid[i][1];
            dpMin[i][2] = Math.min(prev[2], prev[1]) + grid[i][2];
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int n : dpMax[N]) {
            max = Math.max(max, n);
        }

        for (int n : dpMin[N]) {
            min = Math.min(min, n);
        }

        System.out.println(max + " " + min);
    }
}
