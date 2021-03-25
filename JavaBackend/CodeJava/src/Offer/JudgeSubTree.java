package Offer;

import util.BTreeNode;
import util.TreeUtil;

import java.util.LinkedList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/20
 */
public class JudgeSubTree {
    /**
     * 输入两棵二叉树A，B，
     * 判断B是不是A的子结构。
     * （ps：我们约定空树不是任意一个树的子结构）
     */
    public static boolean HasSubtree(BTreeNode root1, BTreeNode root2) {
        // 遍历树1
        boolean res = false;
        if (root1 != null && root2 != null) {
            if (IsSubTree(root1, root2)) {
                res = true;
            } else {
                // 利用短路特性可以少很多计算
                res = HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
            }
        }
        return res;
    }

    /**
     * 在root节点相等时判断2是不是1的子树
     */
    public static boolean IsSubTree(BTreeNode root1, BTreeNode root2) {
        if (root2 == null) {
            // 小树叶节点
            return true;
        } else if (root1 != null && root1.val == root2.val) {
            return IsSubTree(root1.left, root2.left) && IsSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {2, 4, 5};
        BTreeNode tree1 = TreeUtil.array2BTree(arr1);
        BTreeNode tree2 = TreeUtil.array2BTree(arr2);
        System.out.println("HasSubtree:" + HasSubtree(tree1, tree2));
    }
}
