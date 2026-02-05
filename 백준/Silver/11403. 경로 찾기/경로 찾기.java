import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                graph[i][j] = st.nextToken().equals("1");
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!graph[j][k]) {
                        graph[j][k] = graph[j][i] && graph[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(graph[i][j] ? "1" : "0");
                if (j == N - 1) {
                    bw.newLine();
                    break;
                }
                bw.write(" ");
            }
        }

        bw.close();
        br.close();
    }
}
