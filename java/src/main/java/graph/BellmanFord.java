package graph;

import java.util.Arrays;

/**
 * Bellman-Ford Single Source Shortest Path Algorithm
 * --------------------------------------------------
 * Sources:
 * - https://www.youtube.com/watch?v=FtN3BYH2Zes
 */
public class BellmanFord {

    public static class Result {
        public int[] pi;
        public int[] d;
        public boolean hasNegativeWeightCycle;

        public Result(int[] pi, int[] d, boolean hasNegativeWeightCycle) {
            this.pi = pi;
            this.d = d;
            this.hasNegativeWeightCycle = hasNegativeWeightCycle;
        }
    }

    /**
     * Algorithm main ides:
     * 1. Mark the distance from the starting node to 0.
     * 2. Iterate |V| (number of vertices) times through all the edges.
     * 3. If an edge can be relaxed, update its distance:
     *      Relaxation:
     *      - if d(u) + w < d(v), then update the distance of v and mark u as parent
     *
     * 4. If there is a vertex whose distance gets updated on the nth iteration, then the graph has a negative weight
     * cycle.
     *
     * Time Complexity:
     * - Normal     : O(|V| * |E|) ~ O(n^2)
     * - Worst-Case : O(|V| * |E|) ~ O(n^3) when the graph is complete i.e. n * (n - 1) / 2 edges.
     *
     * @param n number of vertices
     * @param edges array of pattern [u, v, w]
     * @param start vertex to start the algorithm from
     * @return an array of parents, an array of distances and whether the graph has a negative weight cycle
     */
    public Result solve(int n, int[][] edges, int start) {
        int[] pi = new int[n];
        Arrays.fill(pi, -1);

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);

        d[start] = 0;

        boolean hasNegativeWeightCycle = false;
        for (int i = 0; i < n; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (d[u] == Integer.MAX_VALUE) {
                    continue;
                }

                int new_w = d[u] + w;
                if (new_w < d[v]) {
                    d[v] = new_w;
                    pi[v] = u;
                    if (i == n - 1) {
                        hasNegativeWeightCycle = true;
                    }
                }
            }
        }

        return new Result(pi, d, hasNegativeWeightCycle);
    }
}
