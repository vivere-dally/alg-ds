package graph;

import java.util.*;

public class Prim {

    public static class Result {
        int[] pi;
        int[][] mst;

        public Result(int[] pi, int[][] mst) {
            this.pi = pi;
            this.mst = mst;
        }
    }

    /**
     * Algorithm main ides:
     * 1. Create a Set that contains all unvisited vertices.
     * 2. Create a parent array.
     * 3. Create an array of distances and initialize them with the maximum value (e.g. Integer.MAX_VALUE).
     * 4. Create an array of neighbours from the given edges.
     * 5. Iterate until we don't have any unvisited vertices left in the set, or until we find |V| - 1 edges (i.e. a MST
     * contains only |V| - 1 edges).
     * 6. Find the shortest distance unvisited vertex and iterate all his neighbours.
     * 7. Given a neighbour, if the distance between u and v is shorter than the one from the distance array:
     *      - mark the parent of v as being the parent of u
     *      - update the distance of v as being w
     * 8. Optionally, given the distance array and the parent array you can find the edges that form the MST.
     *
     * Time Complexity:
     * - Normal           : O(|V| * |E|) ~ O(n^2)
     * - Normal (w/ heap) : O((|V|+|E|) * log(|V|)) ~ O(n*log(n))
     *
     * @param n number of vertices
     * @param edges array of pattern [u, v, w]
     * @param start vertex to start from
     * @return an array of parents, an array of pattern [u, v, w] representing the selected edges forming a MST
     */
    public Result solve(int n, int[][] edges, int start) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            visited.add(i);
        }

        int[] pi = new int[n];

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);

        List<List<int[]>> neighbours = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            neighbours.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            neighbours.get(u).add(new int[]{v, w});
        }

        pi[start] = 0;
        d[start] = 0;
        for (int i = 0; i < n - 1 && !visited.isEmpty(); ) {
            int u = findSmallestUnvisitedVertex(d, visited);
            for (int[] edge : neighbours.get(u)) {
                int v = edge[0], w = edge[1];
                if (visited.contains(v) && w < d[v]) {
                    pi[v] = u;
                    d[v] = w;
                }
            }

            visited.remove(u);
        }

        int[][] mst = new int[n - 1][];
        for (int i = 0, j = 0; i < n; i++) {
            if (i == start) {
                continue;
            }

            for (int[] edge : neighbours.get(pi[i])) {
                if (edge[0] == i) {
                    mst[j++] = new int[]{pi[i], i, edge[1]};
                }
            }
        }

        return new Result(pi, mst);
    }

    private int findSmallestUnvisitedVertex(int[] d, Set<Integer> visited) {
        int min = Integer.MAX_VALUE, vertex = -1;
        for (int i = 0; i < d.length; i++) {
            if (visited.contains(i) && d[i] < min) {
                min = d[i];
                vertex = i;
            }
        }

        return vertex;
    }
}
