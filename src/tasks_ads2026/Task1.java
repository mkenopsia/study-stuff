package tasks_ads2026;

import java.util.Scanner;

public class Task1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int L = scanner.nextInt();
        final int W = scanner.nextInt();
        final int A = scanner.nextInt();
        final int B = scanner.nextInt();

        int suitsL = L / A;
        int suitsW = W / B;
        System.out.println(suitsL * suitsW);
    }
}
