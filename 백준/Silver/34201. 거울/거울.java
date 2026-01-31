import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long pos = Long.parseLong(st.nextToken());
        Deque<Integer> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            deque.add(Integer.parseInt(st.nextToken()));
        }

        if (deque.size() % 2 == 0) {
            pos = jump(pos, deque.pollFirst());
        }

        while (deque.size() > 1) {
            pos = jump(pos, deque.pollLast());
            pos = jump(pos, deque.pollFirst());
        }

        pos = jump(pos, deque.pollLast());

        System.out.println(pos);
    }

    static long jump(long pos, int point) {
        return 2L * point - pos;
    }
}