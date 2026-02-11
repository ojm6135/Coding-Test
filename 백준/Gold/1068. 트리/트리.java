import java.io.*;
import java.util.*;

public class Main {
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int deleted = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new ArrayList[N];
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int root = 0;
        int u = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            u = Integer.parseInt(st.nextToken());

            if (u == -1) {
                root = i;
                q.add(root);
                continue;
            }

            tree[u].add(i);
        }

        if (deleted == root) {
            System.out.println(0);
            br.close();
            return;
        }

        while (!q.isEmpty()) {
            u = q.poll();

            if (visited[u]) {
                continue;
            }

            visited[u] = true;

            if (tree[u].isEmpty()) {
                cnt++;
            }

            for (int v : tree[u]) {
                if (v == deleted) {
                    if (tree[u].size() == 1) {
                        cnt++;
                    }

                    continue;
                }

                if (visited[v]) {
                    continue;
                }

                q.add(v);
            }
        }

        System.out.println(cnt);

        br.close();
    }
}
