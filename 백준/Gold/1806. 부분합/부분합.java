import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] seq = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int res = Integer.MAX_VALUE;
        int len = 0;
        int sum = 0;
        int tail = 0;

        for (int i = 0; i < N; i++) {
            sum += seq[i];

            if (sum >= S) {
                while (sum - seq[tail] >= S) {
                    sum -= seq[tail++];
                }

                len = i - tail + 1;
                res = Math.min(res, len);
            }
        }

        System.out.println(res != Integer.MAX_VALUE ? res : 0);

        br.close();
    }
}
