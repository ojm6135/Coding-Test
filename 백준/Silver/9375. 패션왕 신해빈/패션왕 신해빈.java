import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int n;
        Map<String, Integer> clothes = new HashMap<>();
        StringTokenizer st;
        int res;
        String kind;

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                kind = st.nextToken();
                clothes.put(kind, clothes.getOrDefault(kind, 0) + 1);
            }

            res = 1;

            for (int cnt : clothes.values()) {
                res *= cnt + 1;
            }

            bw.write(String.valueOf(--res));
            bw.newLine();
            clothes.clear();
        }

        br.close();
        bw.close();
    }
}
