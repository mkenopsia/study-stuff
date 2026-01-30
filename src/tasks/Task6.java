package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task6 {
    static List<Edge>[] graph;
    static boolean[] used;
    static int[] ansDir;
    static List<int[]> edges;
    static int[] ptr;
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if (m % 2 == 1) {
            System.out.println(-1);
            return;
        }

        graph = new ArrayList[n + 2];
        for (int i = 1; i <= n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] deg = new int[n + 1];
        edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            deg[u]++;
            deg[v]++;
            edges.add(new int[]{u, v, i});
        }

        int fake = n + 1;
        for (int i = 1; i <= n; i++) {
            if (deg[i] % 2 == 1) {
                edges.add(new int[]{i, fake, edges.size()});
            }
        }

        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i)[0];
            int v = edges.get(i)[1];
            graph[u].add(new Edge(v, i));
            graph[v].add(new Edge(u, i));
        }

        used = new boolean[edges.size()];
        ansDir = new int[edges.size()];
        ptr = new int[n + 2];
        List<Integer> path = new ArrayList<>();
        dfs(1, path);
        
        int cur = -1;
        for (int idx : path) {
            if (idx < m) {
                int u = edges.get(idx)[0];
                int v = edges.get(idx)[1];
                if (cur == u) {
                    ansDir[idx] = 1;
                    cur = v;
                } else {
                    ansDir[idx] = -1;
                    cur = u;
                }
            } else {
                int u = edges.get(idx)[0];
                int v = edges.get(idx)[1];
                if (cur == u) cur = v;
                else cur = u;
            }
        }

        for (int i = 0; i < m; i++) {
            int u = edges.get(i)[0];
            int v = edges.get(i)[1];
            if (ansDir[i] == 1) {
                System.out.println(u + " " + v);
            } else {
                System.out.println(v + " " + u);
            }
        }
    }

    static void dfs(int v, List<Integer> path) {
        while (ptr[v] < graph[v].size()) {
            Edge e = graph[v].get(ptr[v]++);
            if (used[e.id]) continue;
            used[e.id] = true;
            dfs(e.to, path);
            path.add(e.id);
        }
    }

    static class Edge {
        int to, id;
        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }
}