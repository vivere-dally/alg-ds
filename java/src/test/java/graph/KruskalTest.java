package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {

    @Test
    void solve() {
        // Arrange
        Kruskal kruskal = new Kruskal();
        int n = 7;
        int[][] edges = {
                {0, 1, 28},
                {1, 0, 28},
                {0, 5, 10},
                {5, 0, 10},
                {1, 2, 16},
                {2, 1, 16},
                {1, 6, 14},
                {6, 1, 14},
                {2, 3, 12},
                {3, 2, 12},
                {3, 4, 22},
                {4, 3, 22},
                {4, 5, 25},
                {5, 4, 25},
                {4, 6, 24},
                {6, 4, 24}
        };

        int[] expectedPi = {0, 6, 6, 6, 6, 0, 0};

        // Act
        Kruskal.Result result = kruskal.solve(n, edges);

        // Assert
        assertArrayEquals(expectedPi, result.pi);
        assertEquals(99, Arrays.stream(result.mst).mapToInt(edge -> edge[2]).sum());
    }
}