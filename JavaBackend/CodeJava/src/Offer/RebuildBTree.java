package Offer;

import util.BTreeNode;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/28
 */
public class RebuildBTree {
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    public static BTreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return buildRecursive(pre, in, 0, 0, in.length);
    }

    private static BTreeNode buildRecursive(int[] pre, int[] in, int lp, int li, int len) {
        if (len > 0) {
            int rVal = pre[lp];
            BTreeNode root = new BTreeNode(rVal);
            // 根节点在中序的位置
            int rLoc = li;
            while (in[rLoc] != rVal) {
                rLoc++;
            }
            // 左子树节点数量
            int lLen = rLoc - li;
            // 右子树节点数量
            int rLen = li + len - 1-rLoc;
            if (rLen>0) {
                root.right = buildRecursive(pre, in, lp + lLen + 1, rLoc + 1,rLen );
            }
            if (lLen>0) {
                root.left = buildRecursive(pre, in, lp + 1, li, lLen);
            }
            return root;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        BTreeNode root = reConstructBinaryTree(pre, in);
        System.out.println("reConstructBinaryTree finished");
    }
}
