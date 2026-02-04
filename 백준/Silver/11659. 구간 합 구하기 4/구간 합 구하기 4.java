import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] seq = new int[N + 1];
        int start, end;

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            seq[i] = seq[i - 1] + Integer.parseInt(st.nextToken());
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(seq[end] - seq[start - 1]));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
