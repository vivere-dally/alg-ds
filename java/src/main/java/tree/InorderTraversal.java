package tree;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static void preorderTraversalRec(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        values.add(root.val);
        preorderTraversalRec(root.left, values);
        preorderTraversalRec(root.right, values);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        preorderTraversalRec(root, values);
        return values;
    }

    private static void inorderTraversalRec(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        inorderTraversalRec(root.left, values);
        values.add(root.val);
        inorderTraversalRec(root.right, values);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorderTraversalRec(root, values);
        return values;
    }


    private static void postorderTraversalRec(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        postorderTraversalRec(root.left, values);
        postorderTraversalRec(root.right, values);
        values.add(root.val);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        postorderTraversalRec(root, values);
        return values;
    }
}
