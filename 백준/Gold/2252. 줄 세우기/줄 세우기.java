import java.io.*;
import java.util.*;

// 위상 정렬
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] afters = new ArrayList[N + 1];
        int[] beforeCount = new int[N + 1];
        int before, after;
        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int student;

        for (int i = 0; i <= N; i++) {
            afters[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            before = Integer.parseInt(st.nextToken());
            after = Integer.parseInt(st.nextToken());
            afters[before].add(after);
            beforeCount[after]++;
        }

        br.close();

        for (int i = 1; i <= N; i++) {
            if (beforeCount[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            student = q.poll();
            sb.append(student).append(" ");

            for (int aft : afters[student]) {
                beforeCount[aft]--;

                if (beforeCount[aft] == 0) {
                    q.add(aft);
                }
            }
        }

        System.out.println(sb);
    }
}
