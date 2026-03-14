import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int i;
		int a;
		int b;

		for (i = 1; i <= X; i++) {
			X -= i;
		}

		if (X == 0) {
			i--;
		}

		a = X == 0 ? i : X;
		b = i - (a - 1);

		if (i % 2 != 0) {
			a ^= b;
			b ^= a;
			a ^= b;
		}

		System.out.printf("%d/%d", a, b);
	}
}