package tasks;

import java.util.Scanner;

public class Task2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();

        long count = 0;

        for(int i = 0; i < str.length() - 2; i++) {
            for(int j = i + 1; j < chars.length - 1; j++) {
                for (int k = j + 1; k < chars.length; k++) {
                    if(chars[i] < chars[j] && chars[j] < chars[k]) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
