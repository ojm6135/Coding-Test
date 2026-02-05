import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] increaseCnt = new int[N];
        int[] decreaseCnt = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (seq[i] > seq[j]) {
                    increaseCnt[i] = Math.max(increaseCnt[i], increaseCnt[j] + 1);
                }
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (seq[i] > seq[j]) {
                    decreaseCnt[i] = Math.max(decreaseCnt[i], decreaseCnt[j] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, increaseCnt[i] + decreaseCnt[i]);
        }

        System.out.println(max + 1);  // 자기 자신 포함

        br.close();
    }
}
