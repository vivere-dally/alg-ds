package graph;

import java.util.Arrays;

/**
 * Floyd-Warshall All Pairs Shortest Path
 * --------------------------------------
 * Sources:
 * https://www.youtube.com/watch?v=oNI0rf2P9gE
 */
public class FloydWarshall {

    public static class Result {
        int[][] matrix;

        public Result(int[][] matrix) {
            this.matrix = matrix;
        }
    }

    /**
     * Algorithm main ideas:
     * 1. Build a |V| x |V| matrix, where the primary diagonal is full of 0 and the rest cells are infinity.
     * 2. We have 3 nested for loops, k -> |V|, i -> |V| and j -> |V|. For each combination, we execute the following
     * line of code: A[i, j] = min(A[i, j], A[i, k] + A[k, j]).
     * 3. In the i -> |V| for loop we should skip the A[i, k] cell if it equals infinity, same for the j -> |V| for loop
     * if A[k, j] is infinity.
     *
     * @param n number of vertices
     * @param edges array of pattern [u, v, w]
     * @return a matrix with the resulting distances between vertices.
     */
    public Result solve(int n, int[][] edges) {
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            int[] row = new int[n];
            Arrays.fill(row, Integer.MAX_VALUE);
            row[i] = 0;
            matrix[i] = row;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            matrix[u][v] = w;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (matrix[i][k] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int j = 0; j < n; j++) {
                    if (matrix[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        return new Result(matrix);
    }
}
