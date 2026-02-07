import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static boolean[] exist;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        exist = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        Arrays.fill(exist, true);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            dfs(i, M, sb);
        }

        br.close();
        bw.close();
    }

    static void dfs(int i, int M, StringBuilder sb) throws IOException {
        int len = String.valueOf(nums[i]).length();

        sb.append(nums[i]);
        M--;

        if (M == 0) {
            bw.write(sb.toString().trim());
            bw.newLine();
            sb.delete(sb.length() - len, sb.length());
            return;
        }

        exist[i] = false;
        sb.append(" ");

        for (int j = 0; j < N; j++) {
            if (exist[j]) {
                dfs(j, M, sb);
            }
        }

        exist[i] = true;
        sb.delete(sb.length() - len - 1, sb.length());
    }
}
