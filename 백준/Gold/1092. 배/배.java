import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cranes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] boxes = new int[M];
        boolean[] processed = new boolean[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);

        if (cranes[N - 1] < boxes[M - 1]) {
            System.out.println(-1);
            br.close();
            return;
        }

        int i;
        int j;
        int cnt = 0;
        int t = 0;

        while (cnt < M) {
            t++;
            i = N - 1;
            j = M - 1;

            while (i >= 0 && j >= 0 && cnt < M) {
                if (!processed[j] && cranes[i] >= boxes[j]) {
                    processed[j] = true;
                    cnt++;
                    i--;
                }

                j--;
            }
        }

        System.out.println(t);

        br.close();
    }
}
