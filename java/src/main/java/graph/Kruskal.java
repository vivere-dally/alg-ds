package graph;

import java.util.*;

/**
 * Kruskal Minimum Cost Spanning Tree
 * ----------------------------------
 * https://www.youtube.com/watch?v=4ZlRH0eK-qQ
 */
public class Kruskal  {

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
     * 1. Create a parent array where the parent of the vertex i is the vertex itself (i.e. each vertex is 'separated'
     * from the rest.
     * 2. Create a Priority Queue with the given edges allowing us to get the shortest edge in O(log(n)) time.
     * 3. Iterate until we don't have any edges left in the priority queue, or until we find |V| - 1 edges (i.e. a MST
     * contains only |V| - 1 edges).
     * 4. Given an edge, find the parent of u and the parent of v. If they have different parents, then:
     *      - mark the parent of v as being the parent of u
     *      - add the edge to the MST
     *
     * Time Complexity:
     * - Normal           : O(|V| * |E|) ~ O(n^2)
     * - Normal (w/ heap) : O(|V| * log(|E|)) ~ O(n*log(n))
     *
     * @param n number of vertices
     * @param edges array of pattern [u, v, w]
     * @return an array of parents, an array of pattern [u, v, w] representing the selected edges forming a MST
     */
    public Result solve(int n, int[][] edges) {
        int[][] mst = new int[n - 1][];
        int[] pi = new int[n];
        for (int i = 0; i < n; i++) {
            pi[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[2]));
        Collections.addAll(pq, edges);

        for (int i = 0; i < n - 1 && !pq.isEmpty();) {
            int[] edge = pq.poll();
            int u = edge[0], v = edge[1];
            int piu = parent(pi, u), piv = parent(pi, v);
            if (piu != piv) {
                mst[i++] = edge;
                pi[piv] = piu;
            }
        }

        return new Result(pi, mst);
    }

    /**
     * Finds the parent of the given vertex. Since Kruskal does not allow a cycle to be formed, we must update the
     * parent array with the most dominant parent (i.e. the parent that started the MST).
     *
     * @param pi array of parents
     * @param vertex to find parent for
     * @return the parent of vertex
     */
    private int parent(int[] pi, int vertex) {
        if (pi[vertex] == vertex) {
            return vertex;
        }

        pi[vertex] = parent(pi, pi[vertex]);
        return pi[vertex];
    }
}
