package tasks_internTwinter2026;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task7 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final long MOD = 1000000007L;

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<List<Long>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        dp.add(new ArrayList<>());

        for (int p = 0; p <= 1; p++) {
            List<Integer> a = new ArrayList<>();

            for (int s = 2; s <= 2 * n; s++) {
                if ((s & 1) == p) {
                    int x = s - 1;
                    int y = 2 * n + 1 - s;
                    a.add(Math.min(x, y));
                }
            }

            Collections.sort(a);

            List<Long> currentDp = getDP(n, p, a);

            dp.set(p, currentDp);
        }

        int m0 = dp.get(0).size() - 1;
        int m1 = dp.get(1).size() - 1;

        if (k > m0 + m1) {
            System.out.println(0);
            return;
        }

        long res = 0;
        int max = Math.max(0, k - m1);
        int min = Math.min(k, m0);

        for (int x = max; x <= min; x++) {
            int y = k - x;
            long product = (dp.get(0).get(x) * dp.get(1).get(y)) % MOD;
            res = (res + product) % MOD;
        }

        System.out.println(res);
    }

    private static List<Long> getDP(int n, int p, List<Integer> a) {
        int m = 0;
        for (int d = -(n - 1); d <= (n - 1); d++) {
            if ((d & 1) == p) {
                m++;
            }
        }

        int lim = Math.min(a.size(), m);
        List<Long> currentDp = new ArrayList<>(Collections.nCopies(lim + 1, 0L));
        currentDp.set(0, 1L);

        for (int len : a) {
            for (int j = lim; j >= 1; j--) {
                if (len >= j) {
                    long add = (currentDp.get(j - 1) * (len - (j - 1))) % MOD;
                    currentDp.set(j, (currentDp.get(j) + add) % MOD);
                }
            }
        }
        return currentDp;
    }
}
