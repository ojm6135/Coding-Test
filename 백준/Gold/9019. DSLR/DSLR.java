import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;
        int src, dest;
        boolean[] visited = new boolean[10000];
        String[] commands = new String[10000];
        Queue<Integer> q = new ArrayDeque<>();
        int curr;
        int processed;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            Arrays.fill(commands,"");
            q.add(src);
            visited[src] = true;

            while (!q.isEmpty()) {
                curr = q.poll();

                if (curr == dest) {
                    System.out.println(commands[curr]);
                    break;
                }

                processed = curr * 2 % 10000;
                if (!visited[processed]) {
                    commands[processed] = commands[curr] + "D";
                    q.add(processed);
                    visited[processed] = true;
                }

                processed = curr > 0 ? curr - 1 : 9999;
                if (!visited[processed]) {
                    commands[processed] = commands[curr] + "S";
                    q.add(processed);
                    visited[processed] = true;
                }

                processed = curr % 1000 * 10 + curr / 1000;
                if (!visited[processed]) {
                    commands[processed] = commands[curr] + "L";
                    q.add(processed);
                    visited[processed] = true;
                }

                processed = curr % 10 * 1000 + curr / 10;
                if (!visited[processed]) {
                    commands[processed] = commands[curr] + "R";
                    q.add(processed);
                    visited[processed] = true;
                }
            }

            Arrays.fill(visited,false);
            q.clear();
        }

        br.close();
    }
}
