package Offer;

import java.util.ArrayList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/17
 */
public class FindPath {
    /**
     * 输入一颗二叉树的根节点和一个整数，
     * 打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return results;
        ArrayList<Integer> path = new ArrayList<Integer>();
        int sum = 0;
        trackBack(root, target, results, path, sum);
        return results;
    }

    public void trackBack(TreeNode root, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, int sum) {
        if (root == null)
            return;
        sum += root.val;
        if (root.left == null && root.right == null) {
            // 到达叶节点
            if (sum == target) {
                // 命中
                path.add(root.val);
                // 这里将路径复制的形式加入结果
                res.add(new ArrayList<Integer>(path));
                // 移除页节点，回溯
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(root.val);
        trackBack(root.left, target, res, path, sum);
        trackBack(root.right, target, res, path, sum);
        // 移除末尾节点，回溯
        path.remove(path.size() - 1);
    }
}
