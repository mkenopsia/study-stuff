package tasks;

import java.util.Arrays;
import java.util.Scanner;

public class Task4 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE);
        }
        dp[0][0] = 0;

        long[] maxPrev = new long[k + 1];
        Arrays.fill(maxPrev, Long.MIN_VALUE);
        maxPrev[0] = 0;
        for (int i = 1; i <= n; i++) {
            int moodChange = a[i - 1];
            for (int j = k; j >= 0; j--) {
                if (dp[i - 1][j] != Long.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + moodChange);
                }

                if (i >= 2 && dp[i - 2][j] != Long.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j] + moodChange);
                }

                if (j > 0 && maxPrev[j - 1] != Long.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], maxPrev[j - 1] + moodChange);
                }
            }
            for (int j = 0; j <= k; j++) {
                if (dp[i][j] != Long.MIN_VALUE) {
                    if (maxPrev[j] == Long.MIN_VALUE) {
                        maxPrev[j] = dp[i][j];
                    } else {
                        maxPrev[j] = Math.max(maxPrev[j], dp[i][j]);
                    }
                }
            }
        }

        long result = Long.MIN_VALUE;
        for (int j = 0; j <= k; j++) {
            if (dp[n][j] != Long.MIN_VALUE) {
                result = Math.max(result, dp[n][j]);
            }
        }
        System.out.println(result);
    }
}