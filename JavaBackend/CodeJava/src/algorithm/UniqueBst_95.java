package algorithm;

import java.util.LinkedList;
import java.util.List;

public class UniqueBst_95 {

    //     Definition for a binary tree node.
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

    public static List<TreeNode> generateTrees(int n) {
        if (n==0){
            return new LinkedList<>();
        }
        return traceBack(1, n);
    }

    public static List<TreeNode> traceBack( int l, int r) {
        List<TreeNode> res = new LinkedList<>();
        if (l>r){
            res.add(null);
            return res;
        }
        for (int i = l; i <=r ; i++) {
            List<TreeNode> left = traceBack(l, i-1);
            List<TreeNode> right = traceBack(i+1, r);
            for(TreeNode le:left){
                for (TreeNode ri:right){
                    TreeNode root= new TreeNode(i);
                    root.left = le;
                    root.right = ri;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
