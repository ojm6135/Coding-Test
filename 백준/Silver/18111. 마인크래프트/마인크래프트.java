import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] cnt = new int[257];
        int h;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                h = Integer.parseInt(st.nextToken());
                cnt[h]++;
                min = Math.min(min, h);
                max = Math.max(max, h);
            }
        }

        int time = Integer.MAX_VALUE;
        int groundLevel = -1;
        int reqTime;
        int fillCnt;
        int digCnt;
        int i;
        h = min - 1;

        while (++h <= max) {
            fillCnt = 0;
            digCnt = 0;

            for (i = min; i < h; i++) {
                fillCnt += (h - i) * cnt[i];
            }

            for (i = h + 1; i <= max; i++) {
                digCnt += (i - h) * cnt[i];
            }

            if (fillCnt > digCnt + B) {
                continue;
            }

            reqTime = fillCnt + digCnt * 2;

            if (reqTime < time) {
                time = reqTime;
                groundLevel = h;
            } else if (reqTime == time) {
                groundLevel = Math.max(groundLevel, h);
            }
        }

        System.out.printf("%d %d", time, groundLevel);

        br.close();
    }
}
