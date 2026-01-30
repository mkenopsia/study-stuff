package tasks;

import java.util.Scanner;

public class Task1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt(); // текущее число
        int m = scanner.nextInt(); // ласт запись

        if(n <= 7) {
            System.out.println(m + 7);
        } else {
            System.out.println(n - 7);
        }
    }
}
