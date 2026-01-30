package tasks_internTwinter2026;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        if(chars[0] == '0') {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != '0') {
                    chars[0] = chars[i];
                    chars[i] = '0';
                    break;
                }
            }
        }
        System.out.println(String.valueOf(chars));
    }
}
