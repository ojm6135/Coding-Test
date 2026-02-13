import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        int[] pos = new int[11];
        int l = 0;
        int r = 0;
        int min = 0;
        int max = 0;
        int len = 0;
        int res = 0;

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(pos, -1);

        while (l <= r && r < N) {
            pos[seq[r]] = r;

            if (seq[r] <= seq[min]) {
                min = r;
            }

            if (seq[r] >= seq[max]) {
                max = r;
            }

            if (seq[max] - seq[min] > 2) {
                if (max == r) {
                    for (int i = 1; i < seq[max] - 2; i++) {
                        l = Math.max(l, pos[i] + 1);
                        pos[i] = -1;
                    }

                    for (int i = seq[max]; i >= seq[max] - 2; i--) {
                        if (pos[i] == -1) {
                            continue;
                        }

                        if (pos[i] < l) {
                            pos[i] = -1;
                            continue;
                        }

                        min = pos[i];
                    }
                } else if (min == r) {
                    for (int i = seq[min] + 3; i <= 10; i++) {
                        l = Math.max(l, pos[i] + 1);
                        pos[i] = -1;
                    }

                    for (int i = seq[min]; i <= seq[min] + 2; i++) {
                        if (pos[i] == -1) {
                            continue;
                        }

                        if (pos[i] < l) {
                            pos[i] = -1;
                            continue;
                        }

                        max = pos[i];
                    }
                }

                len = r - l + 1;
                r++;
                continue;
            }

            len++;
            res = Math.max(res, len);
            r++;
        }

        System.out.println(res);

        br.close();
    }
}
