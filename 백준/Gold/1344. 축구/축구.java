import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] notPrimes = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
        double pA = Integer.parseInt(br.readLine()) / 100.0;
        double pB = Integer.parseInt(br.readLine()) / 100.0;
        double pNotA = 1 - pA;
        double pNotB = 1 - pB;
        double pANotPrime = 0.0;
        double pBNotPrime = 0.0;
        double pBothNotPrime;
        long comb;

        for (int score : notPrimes) {
            comb = comb(18, score);
            pANotPrime += Math.pow(pA, score) * Math.pow(pNotA, 18 - score) * comb;
            pBNotPrime += Math.pow(pB, score) * Math.pow(pNotB, 18 - score) * comb;
        }

        pBothNotPrime = pANotPrime * pBNotPrime;

        System.out.println(1 - pBothNotPrime);

        br.close();
    }

    static long comb(int n, int k) {
        long res = 1;

        for (int i = 0; i < k; i++) {
            res *= n - i;
        }

        for (int i = k; i > 1; i--) {
            res /= i;
        }

        return res;
    }
}
