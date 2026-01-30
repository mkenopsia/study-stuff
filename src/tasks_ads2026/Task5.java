package tasks_ads2026;

import java.util.Arrays;
import java.util.Scanner;

public class Task5 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            final int curr = scanner.nextInt();
            arr[i] = countLatticePoints(curr);
        }

        System.out.println(countRemovals(arr));
    }

    static int countLatticePoints(int radius) {
        return 1 + 4 * (radius + (int) (radius / Math.sqrt(2)));
    }

    static int countRemovals(int[] arr) {
        long total = 0L;
        for (int i : arr) {
            total += i;
        }
        int toRemove = (int) (total % 8);
        if (toRemove == 0) return 0;

        final int inf = Integer.MAX_VALUE / 2;
        int dp[] = new int[8];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int curr : arr) {
            int rem = curr % 8;
            int[] newDp = Arrays.copyOf(dp, 8);
            for(int i = 0; i < 8; i++) {
                if(dp[i] != inf) {
                    int newRem = (i + rem) % 8;
                    newDp[newRem] = Math.min(newDp[newRem], dp[i] + 1);
                }
            }
            dp = newDp;
        }

        return dp[toRemove] == inf ? arr.length : dp[toRemove];
    }
}
