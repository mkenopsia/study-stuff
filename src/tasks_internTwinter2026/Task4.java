package tasks_internTwinter2026;

import java.util.*;

public class Task4 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int size = scanner.nextInt();
        final int edges = scanner.nextInt();
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minCycle = Integer.MAX_VALUE;
        boolean found = false;

        for (int u = 0; u < graph.size(); u++) {
            for (int v : graph.get(u)) {
                if (u >= v) continue;

                Queue<Integer> queue = new LinkedList<>();
                int[] dist = new int[graph.size()];
                Arrays.fill(dist, -1);
                dist[u] = 0;
                queue.add(u);

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int neighbor : graph.get(cur)) {
                        if ((cur == u && neighbor == v) || (cur == v && neighbor == u)) {
                            continue;
                        }
                        if (dist[neighbor] == -1) {
                            dist[neighbor] = dist[cur] + 1;
                            queue.add(neighbor);
                        }
                    }
                }

                if (dist[v] != -1) {
                    int cycleLength = dist[v] + 1;
                    if (cycleLength >= 3) {
                        minCycle = Math.min(minCycle, cycleLength);
                        found = true;
                    }
                }
            }
        }

        int res = found ? minCycle : -1;
        System.out.println(res);
    }
}
