package tasks_internTwinter2026;

import java.util.Scanner;

public class Task5 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_VALUE = 100_000;

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] freq = new int[MAX_VALUE + 1];
        boolean[] canBeUsed = new boolean[MAX_VALUE + 1];

        for (int x : a) {
            if (x >= 0 && x <= MAX_VALUE) {
                freq[x]++;
            }
        }

        for (int i = 0; i < n; i++) {
            int first = a[(i % n + n) % n];
            int second = a[((i + 1) % n + n) % n];
            int third = a[((i + 2) % n + n) % n];

            int min = Math.min(Math.min(first, second), third);
            int max = Math.max(Math.max(first, second), third);
            canBeUsed[min] = true;
            canBeUsed[max] = true;
        }

        int[] res = new int[MAX_VALUE + 1];
        for (int v = 1; v <= MAX_VALUE; v++) {
            if (freq[v] > 0) {
                if (freq[v] == n) {
                    res[v] = 0;
                } else {
                    res[v] = (n - freq[v]) + (canBeUsed[v] ? 0 : 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[a[i]]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
