package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloydWarshallTest {

    @Test
    void solve() {
        // Arrange
        FloydWarshall floydWarshall = new FloydWarshall();
        int n = 4;
        int[][] edges = {
                {0, 1, 3},
                {0, 3, 7},
                {1, 0, 8},
                {1, 2, 2},
                {2, 0, 5},
                {2, 3, 1},
                {3, 0, 2}
        };

        int[][] expectedMatrix = {
                {0, 3, 5, 6},
                {5, 0, 2, 3},
                {3, 6, 0, 1},
                {2, 5, 7, 0}
        };

        // Act
        FloydWarshall.Result result = floydWarshall.solve(n, edges);

        // Assert
        for (int i = 0; i < n; i++) {
            assertArrayEquals(expectedMatrix[i], result.matrix[i]);
        }
    }
}