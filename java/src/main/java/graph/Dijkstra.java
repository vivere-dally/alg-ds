package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dijkstra Single Source Shortest Path Algorithm
 * --------------------------------------------------
 * Sources:
 * - https://www.youtube.com/watch?v=XB4MIexjvY0
 */
public class Dijkstra {

    public static class Result {
        public int[] pi;
        public int[] d;

        public Result(int[] pi, int[] d) {
            this.pi = pi;
            this.d = d;
        }
    }

    /**
     * Algorithm main ides:
     * 1. Mark the distance from the starting node to 0.
     * 2. Iterate until all vertices are marked as visited.
     * 3. Find the smallest unvisited vertex by distance and consider it to be u.
     * 4. Iterate through all u's neighbours and relax them if possible:
     *      Relaxation:
     *      - if d(u) + w < d(v), then update the distance of v and mark u as parent
     *
     * Time Complexity:
     * - Normal     : O(|V| * neighbours(Vi))
     * - Worst-Case : O(|V| * |V|) ~ O(n^2) when the graph is complete i.e. n * (n - 1) / 2 edges.
     *
     * @param n number of vertices
     * @param edges array of pattern [u, v, w]
     * @param start vertex to start the algorithm from
     * @return an array of parents, an array of distances and whether the graph has a negative weight cycle
     */
    public Result solve(int n, int[][] edges, int start) {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        int[] pi = new int[n];
        Arrays.fill(pi, -1);

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);

        d[start] = 0;

        List<List<int[]>> neighbours = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            neighbours.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            neighbours.get(u).add(new int[]{v, w});
        }

        int u = start;
        while (u != -1) {
            visited[u] = true;
            for (int[] edge : neighbours.get(u)) {
                int v = edge[0], w = edge[1];
                int new_w = d[u] + w;
                if (new_w < d[v]) {
                    d[v] = new_w;
                    pi[v] = u;
                }
            }

            u = findSmallestUnvisitedVertex(d, visited);
        }

        return new Result(pi, d);
    }

    private int findSmallestUnvisitedVertex(int[] d, boolean[] visited) {
        int min = Integer.MAX_VALUE, vertex = -1;
        for (int i = 0; i < d.length; i++) {
            if (!visited[i] && d[i] < min) {
                min = d[i];
                vertex = i;
            }
        }

        return vertex;
    }
}
