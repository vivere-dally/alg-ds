package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    @Test
    void solve() {
        // Arrange
        BellmanFord bellmanFord = new BellmanFord();
        int n = 7;
        int[][] edges = {
                {0, 1, 6},
                {0, 2, 5},
                {0, 3, 5},
                {1, 4, -1},
                {2, 1, -2},
                {2, 4, 1},
                {3, 2, -2},
                {3, 5, -1},
                {4, 6, 3},
                {5, 6, 3}
        };

        int[] expectedD = {0, 1, 3, 5, 0, 4, 3};
        int[] expectedPi = {-1, 2, 3, 0, 1, 3, 4};

        // Act
        BellmanFord.Result actual = bellmanFord.solve(n , edges, 0);

        // Assert
        assertArrayEquals(expectedD, actual.d);
        assertArrayEquals(expectedPi, actual.pi);
        assertFalse(actual.hasNegativeWeightCycle);
    }

    @Test
    void solve_negativeWeightCycle() {
        // Arrange
        BellmanFord bellmanFord = new BellmanFord();
        int n = 4;
        int[][] edges = {
                {0, 1, 4},
                {0, 3, 5},
                {1, 3, 5},
                {2, 1, -10},
                {3, 2, 3}
        };

        int[] expectedD = {0, -4, 6, 3};
        int[] expectedPi = {-1, 2, 3, 1};

        // Act
        BellmanFord.Result actual = bellmanFord.solve(n , edges, 0);

        // Assert
        assertArrayEquals(expectedD, actual.d);
        assertArrayEquals(expectedPi, actual.pi);
        assertTrue(actual.hasNegativeWeightCycle);
    }
}