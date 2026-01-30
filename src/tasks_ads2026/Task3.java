package tasks_ads2026;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int k = scanner.nextInt();

        long[] prefix = new long[length - 1];
        long sum = 0;

        for (int i = 0; i < length; i++) {
            long x = scanner.nextLong();
            sum += x;
            if (i >= 1) {
                prefix[i - 1] = sum;
            }
        }

        Arrays.sort(prefix);
        long total = 0;
        int count = 0;
        for (int i = prefix.length - 1; i >= 0; i--) {
            if (prefix[i] <= 0 || count >= k) break;
            total += prefix[i];
            count++;
        }

        System.out.println(total);
    }
}
