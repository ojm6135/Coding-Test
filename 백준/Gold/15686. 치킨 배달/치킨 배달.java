import java.io.*;
import java.util.*;

public class Main {
    static int M;
    static List<int[]> houses;
    static List<int[]> chickens;
    static int minTotalDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int u;
        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                u = Integer.parseInt(st.nextToken());

                switch (u) {
                    case 1:
                        houses.add(new int[]{i, j});
                        break;
                    case 2:
                        chickens.add(new int[]{i, j});
                }
            }
        }

        List<Integer> picked = new ArrayList<>();

        for (int i = 0; i < chickens.size() - M + 1; i++) {
            picked.add(i);
            solve(i + 1, M - 1, picked);
            picked.remove(picked.size() - 1);
        }

        System.out.println(minTotalDist);

        br.close();
    }

    static void solve(int start, int m, List<Integer> picked) {
        if (m == 0) {
            int totalDist = 0;
            int dist;
            int nearest;

            for (int[] house : houses) {
                nearest = Integer.MAX_VALUE;

                for (int p : picked) {
                    dist = Math.abs(chickens.get(p)[0] - house[0]) + Math.abs(chickens.get(p)[1] - house[1]);
                    nearest = Math.min(nearest, dist);
                }

                totalDist += nearest;
            }

            minTotalDist = Math.min(minTotalDist, totalDist);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            picked.add(i);
            solve(i + 1, m - 1, picked);
            picked.remove(picked.size() - 1);
        }
    }
}
