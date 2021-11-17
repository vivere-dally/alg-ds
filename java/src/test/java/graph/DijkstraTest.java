package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void solve() {
        // Arrange
        Dijkstra dijkstra = new Dijkstra();
        int n = 6;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 4},
                {1, 2, 1},
                {1, 3, 7},
                {2, 4, 3},
                {3, 5, 1},
                {4, 3, 2},
                {4, 5, 5}
        };

        int[] expectedPi = {-1, 0, 1, 4, 2, 3};
        int[] expectedD = {0, 2, 3, 8, 6, 9};

        // Act
        Dijkstra.Result actual = dijkstra.solve(n, edges, 0);

        // Assert
        assertArrayEquals(expectedPi, actual.pi);
        assertArrayEquals(expectedD, actual.d);
    }
}