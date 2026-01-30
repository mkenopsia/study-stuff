package tasks_ads2026;

import java.util.Scanner;
import java.util.TreeMap;

public class Task4 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int length = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[] arr = new int[length];
        for(int i = 0; i < length; i++) {
            arr[i] = scanner.nextInt();
        }

        int total = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        for (int right = 0; right < length; right++) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > k) {
                if(map.get(arr[left]) > 1) {
                    map.put(arr[left], map.get(arr[left]) - 1);
                } else {
                    map.remove(arr[left]);
                }
                left++;
            }

            total += right - left + 1;
        }

        System.out.println(total);
    }
}
