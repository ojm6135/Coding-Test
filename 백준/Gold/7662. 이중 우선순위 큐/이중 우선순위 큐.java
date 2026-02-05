import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int K;
        StringTokenizer st;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> map = new HashMap<>();
        String op;
        int n;
        int target = -1;

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                op = st.nextToken();
                n = Integer.parseInt(st.nextToken());
                switch (op) {
                    case "I":
                        map.put(n, map.getOrDefault(n, 0) + 1);
                        minHeap.add(n);
                        maxHeap.add(n);
                        break;
                    case "D":
                        if (map.isEmpty()) {
                            continue;
                        }

                        if (n == -1) {
                            do {
                                target = minHeap.poll();
                            } while (!map.containsKey(target));
                        } else if (n == 1) {
                            do {
                                target = maxHeap.poll();
                            } while (!map.containsKey(target));
                        }

                        if (map.get(target) == 1) {
                            map.remove(target);
                            continue;
                        }

                        map.put(target, map.get(target) - 1);
                }
            }

            if (map.isEmpty()) {
                bw.write("EMPTY");
            } else {
                int max, min;
                do {
                    min = minHeap.poll();
                } while (!map.containsKey(min));

                do {
                    max = maxHeap.poll();
                } while (!map.containsKey(max));
                bw.write(String.format("%d %d", max, min));
            }

            bw.newLine();
            minHeap.clear();
            maxHeap.clear();
            map.clear();
        }

        br.close();
        bw.close();
    }
}
