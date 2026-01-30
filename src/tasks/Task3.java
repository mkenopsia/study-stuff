package tasks;

import java.util.*;

public class Task3 {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        int currIndex = 0;

        int N = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();

        for(int i = 1; i < N + 1; i++) {
            char curr = str.charAt(i - 1);
            if(curr == 'L') {
                list.add(currIndex, i);
            } else {
                currIndex++;
                list.add(currIndex, i);
            }
        }

        int last = list.removeLast();
        for(int i : list) {
            System.out.print(i + " ");
        }
        System.out.println(last);
    }
}
