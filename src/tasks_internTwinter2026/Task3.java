package tasks_internTwinter2026;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int dataSetsCount = scanner.nextInt();
        scanner.nextLine();
        List<Integer> res = new ArrayList<>(dataSetsCount);
        while (dataSetsCount > 0) {
            final String str = scanner.nextLine();
            final char[] flat = (str + str).toCharArray();
            int count = 0, max = 0;
            for(int i = 0; i < flat.length; i++) {
                if(flat[i] == '1') {
                    count++;
                    count = Math.min(count, str.length());
                    max = Math.max(count, max);
                } else {
                    count = 0;
                }
            }

            if(max == str.length()) {
                res.add(max * max);
            } else if (max == 0) {
                res.add(0);
            } else {
                final int H = (max + 1) / 2;
                final int W = max + 1 - H;
                res.add(H * W);
            }
            dataSetsCount--;
        }

        for (Integer i : res) {
            System.out.println(i);
        }
    }
}
