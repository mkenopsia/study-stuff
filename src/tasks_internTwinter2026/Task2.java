package tasks_internTwinter2026;

import java.util.Scanner;

public class Task2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final char[] str = scanner.nextLine().toCharArray();
        final char[] study = "study".toCharArray(), tbank = "tbank".toCharArray();
        final int[] matchStudy = new int[str.length - 4], matchTbank = new int[str.length - 4];
        final int[] pref = new int[str.length - 4], suf = new int[str.length - 4];

        for (int i = 0; i < str.length - 4; i++) {
            for (int j = 0; j < 5; j++) {
                matchStudy[i] += (str[i + j] != study[j] ? 1 : 0);
                matchTbank[i] += (str[i + j] != tbank[j] ? 1 : 0);
            }
        }

        for (int i = 0; i < str.length - 4; i++) {
            pref[i] = matchStudy[i];
            if (i > 0) {
                pref[i] = Math.min(pref[i - 1], pref[i]);
            }
        }
        for (int i = str.length - 5; i >= 0; i--) {
            suf[i] = matchStudy[i];
            if (i + 1 < str.length - 4) {
                suf[i] = Math.min(suf[i + 1], suf[i]);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < str.length - 4; i++) {
            int countStudy = Integer.MAX_VALUE / 2;
            if (i - 5 >= 0) {
                countStudy = Math.min(countStudy, pref[i - 5]);
            }
            if (i + 5 < str.length - 4) {
                countStudy = Math.min(countStudy, suf[i + 5]);
            }
            res = Math.min(res, countStudy + matchTbank[i]);
        }
        System.out.println(res);
    }
}
