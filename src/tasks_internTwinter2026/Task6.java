package tasks_internTwinter2026;

import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        // Храним операции: каждая — (l, r, длина_до_операции)
        List<long[]> operations = new ArrayList<>();
        long currentLength = n;

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                long l = Long.parseLong(st.nextToken());
                long r = Long.parseLong(st.nextToken());
                operations.add(new long[]{l, r, currentLength});
                currentLength += (r - l + 1);
            } else {
                long pos = Long.parseLong(st.nextToken()); // 1-indexed
                // Рекурсивно находим исходную позицию
                long originalPos = resolvePosition(pos, operations);
                out.println(s.charAt((int) (originalPos - 1)));
            }
        }

        out.close();
    }

    private static long resolvePosition(long pos, List<long[]> operations) {
        // Идём от последней операции к первой
        for (int i = operations.size() - 1; i >= 0; i--) {
            long[] op = operations.get(i);
            long l = op[0];
            long r = op[1];
            long lenBefore = op[2];

            if (pos > lenBefore) {
                // Позиция находится в добавленной части
                long offset = pos - lenBefore; // 1-indexed offset in the copied segment
                pos = l + offset - 1; // новая позиция в строке до операции
            }
            // Если pos <= lenBefore — ничего не делаем, идём дальше
        }
        return pos;
    }
}