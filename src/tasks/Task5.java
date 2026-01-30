package tasks;

import java.util.*;

public class Task5 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int N = scanner.nextInt();
        final int Q = scanner.nextInt();

        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < Q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int u = l - 1;
            int v = r;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> queue = new ArrayDeque<>();
        dist[0] = 0;
        queue.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : adj.get(curr)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);
                }
            }
        }

        if (dist[N] != -1) {
            System.out.println("Yes");
            System.out.println(dist[N]);
        } else {
            System.out.println("No");
        }
    }
}