import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] sushi = new int[N];
        int[] count = new int[D + 1];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine().trim());
        }

        int res = 0;

        for (int i = 0; i < K; i++) {
            if (count[sushi[i]] == 0) {
                res++;
            }

            count[sushi[i]]++;
        }

        if (count[C] == 0) {
            res++;
        }

        int curr = res;
        int head, tail;

        // window를 한 칸씩 이동하며 최댓값 갱신
        for (int i = K; i < N + K - 1; i++) {
            // tail
            tail = (i + N - K) % N;

            if (sushi[tail] == C) {
                if (count[C] == 1) {
                    curr--;
                }
            } else {
                if (count[sushi[tail]] == 1) {
                    curr--;
                }

                if (count[C] == 0) {
                    curr--;
                }
            }

            count[sushi[tail]]--;

            // head: 새로 추가할 접시
            head = i % N;

            if (sushi[head] == C) {
                if (count[C] == 0) {
                    curr++;
                }
            } else {
                if (count[sushi[head]] == 0) {
                    curr++;
                }

                if (count[C] == 0) {
                    curr++;
                }
            }

            count[sushi[head]]++;
            res = Math.max(res, curr);
        }

        System.out.println(res);
    }
}

