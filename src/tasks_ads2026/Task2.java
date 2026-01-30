package tasks_ads2026;

import java.util.Scanner;
import java.util.TreeSet;

public class Task2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int length = scanner.nextInt();
        final int k = scanner.nextInt();
        final byte[] canReach = new byte[length];

        TreeSet<Integer> set = new TreeSet<>();
        set.add(scanner.nextInt());
        canReach[0] = 1;
        for(int i = 1; i < length; i++) {
            int current = scanner.nextInt();
            int hight = current + k;
            int low = current - k;
            Integer candidate = set.ceiling(low);
            if(candidate != null && candidate <= hight) {
                canReach[i] = 1;
                set.add(current);
            }
        }

        for(int i = 0; i < length - 1; i++) {
            System.out.print(canReach[i] + " ");
        }
        System.out.println(canReach[length - 1]);
    }
}
