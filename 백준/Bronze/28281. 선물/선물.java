import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            min = Math.min(min, cost[i - 1] + cost[i]);
        }

        System.out.println(min * X);
    }
}