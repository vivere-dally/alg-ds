package tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InorderTraversalTest {

    @Test
    void preorderTraversal() {
        // Arrange
        InorderTraversal.TreeNode root = new InorderTraversal.TreeNode(1,
                new InorderTraversal.TreeNode(2,
                        new InorderTraversal.TreeNode(4),
                        new InorderTraversal.TreeNode(5)
                ),
                new InorderTraversal.TreeNode(3)
        );

        List<Integer> expectedValues = Arrays.asList(1, 2, 4, 5, 3);

        // Act
        List<Integer> actual = InorderTraversal.preorderTraversal(root);

        // Assert
        assertEquals(expectedValues, actual);
    }

    @Test
    void inorderTraversal() {
        // Arrange
        InorderTraversal.TreeNode root = new InorderTraversal.TreeNode(1,
                new InorderTraversal.TreeNode(2,
                        new InorderTraversal.TreeNode(4),
                        new InorderTraversal.TreeNode(5)
                ),
                new InorderTraversal.TreeNode(3)
        );

        List<Integer> expectedValues = Arrays.asList(4, 2, 5, 1, 3);

        // Act
        List<Integer> actual = InorderTraversal.inorderTraversal(root);

        // Assert
        assertEquals(expectedValues, actual);
    }

    @Test
    void postorderTraversal() {
        // Arrange
        InorderTraversal.TreeNode root = new InorderTraversal.TreeNode(1,
                new InorderTraversal.TreeNode(2,
                        new InorderTraversal.TreeNode(4),
                        new InorderTraversal.TreeNode(5)
                ),
                new InorderTraversal.TreeNode(3)
        );

        List<Integer> expectedValues = Arrays.asList(4, 5, 2, 3, 1);

        // Act
        List<Integer> actual = InorderTraversal.postorderTraversal(root);

        // Assert
        assertEquals(expectedValues, actual);
    }
}