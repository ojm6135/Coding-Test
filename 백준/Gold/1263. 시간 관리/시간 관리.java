import java.io.*;
import java.util.*;

public class Main {
    static class Task implements Comparable<Task> {
        int duration;
        int deadline;

        Task(int duration, int deadline) {
            this.duration = duration;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Task o) {
            return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[N];
        int duration;
        int deadline;
        int t = 0;
        int savedTime;
        int minSavedTime = Integer.MAX_VALUE;
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            duration = Integer.parseInt(st.nextToken());
            deadline = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(duration, deadline);
        }

        Arrays.sort(tasks);

        for (int i = 0; i < N; i++) {
            t += tasks[i].duration;
            savedTime = tasks[i].deadline - t;

            if (savedTime < 0) {
                minSavedTime = -1;
                break;
            }

            minSavedTime = Math.min(minSavedTime, savedTime);
        }

        System.out.println(minSavedTime);

        br.close();
    }
}
