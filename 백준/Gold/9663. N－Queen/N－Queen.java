import java.io.*;

public class Main {
	static int N;
	static int[][] attacked;
	static int res = 0;
	static int[][] dirs = {
		{-1, 0}, {1, 0}, {0, -1}, {0, 1},  {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
	};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		attacked = new int[N][N];
		solve(0, 0);
		System.out.println(res);
    }

	static void solve(int r, int cnt) {
		if (cnt == N) {
			res++;
			return;
		}

		for (int c = 0; c < N; c++) {
			if (attacked[r][c] == 0) {
				update(r, c, false);
				solve(r + 1, cnt + 1);
				update(r, c, true);
			}
		}
	}

	static void update(int r, int c, boolean isDeleted) {
		int i;
		int j;
		int amount = isDeleted ? -1 : 1;

		for (int[] dir : dirs) {
			i = r;
			j = c;

			while (i >= 0 && i < N && j >= 0 && j < N) {
				attacked[i][j] += amount;
				i += dir[0];
				j += dir[1];
			}
		}

		attacked[r][c] += -amount * 7;
	}
}
