import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N];
        int[] sortedIdx = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sortedIdx[i] = i;
        }

        int M = Integer.parseInt(br.readLine());
        int min, t;

        // argsort
        for (int i = 0; i < N; i++) {
            min = i;
            for (int j = i + 1; j < N; j++) {
                if (cost[sortedIdx[j]] <= cost[sortedIdx[min]]) {
                    min = j;
                }
            }
            t = sortedIdx[i];
            sortedIdx[i] = sortedIdx[min];
            sortedIdx[min] = t;
        }

        StringBuilder sb = new StringBuilder();

        // 일단 cost가 가장 작은 번호만 골라서 자릿수를 최대로
        for (int i = 0; i < M / cost[sortedIdx[0]]; i++) {
            sb.append(sortedIdx[0]);
        }

        M %= cost[sortedIdx[0]];

        int j;
        int cnt = Integer.MIN_VALUE;
        boolean flag = false;

        if (sb.charAt(0) == '0') {
            int len = sb.length();

            if (N > 1) {
                // 최대한 많은 자릿수 유지를 위해 cost가 두 번째로 낮은 숫자로 계산
                cnt = (int) Math.ceil((double) (cost[sortedIdx[1]] - M) / cost[0]);
                M += cost[0] * cnt;

                if (cnt <= len) {
                    // 마련한 예산으로 살 수 있는 숫자의 최댓값 계산
                    for (j = N - 1; j > sortedIdx[1] && M < cost[j]; j--);
                    sb.replace(0, cnt, String.valueOf(j));
                    M -= cost[j];
                    flag = true;
                }
            }

            if (N == 1 || cnt > len) {
                sb.delete(1, sb.length());
                System.out.println(sb);
                br.close();
                return;
            }
        }

        int digit = flag ? 1 : 0;
        j = N - 1;

        // 높은 자리부터 현재 자리의 수를 팔아서 더 큰 수를 구매
        while (digit < sb.length() && j > sortedIdx[0]) {
            if (M + cost[sortedIdx[0]] < cost[j]) {
                j--;
                continue;
            }

            sb.replace(digit, digit + 1, String.valueOf(j));
            M += cost[sortedIdx[0]] - cost[j];
            digit++;
        }

        System.out.println(sb);

        br.close();
    }
}
