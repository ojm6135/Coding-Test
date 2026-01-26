import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        Map<Integer, List<Integer>> nodes = new HashMap<>();
        int i, p, root = -1;

        for (i = 0; i < n; i++) {
            nodes.put(i, new ArrayList<>());
        }

        int[] parents = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (i = 0; i < n; i++) {
            p = parents[i];

            if (p == -1) {
                root = i;
                continue;
            }

            nodes.get(p).add(i);
        }

        Integer deleted = Integer.parseInt(br.readLine().trim());

        if (deleted == root) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }

        List<Integer> childList;
        int leafCnt = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            childList = nodes.get(stack.pop());
            childList.remove(deleted);

            if (childList.size() == 0) {
                leafCnt++;
                continue;
            }

            stack.addAll(childList);
        }

        bw.write(leafCnt + "");

        br.close();
        bw.close();
    }
}