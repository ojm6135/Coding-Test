import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int k;

        while(N-- > 0) {
            k = Integer.parseInt(br.readLine());

            if (k == 0) {
                bw.write(String.valueOf(!pq.isEmpty() ? pq.poll() : "0"));
                bw.newLine();
            } else {
                pq.add(k);
            }
        }

        br.close();
        bw.close();
    }
}
