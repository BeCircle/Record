package Offer;

import util.BTreeNode;
import util.TreeUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/23
 */
public class MirrorBTree {
    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 二叉树的镜像定义：源二叉树
     *     	    8
     *     	   /  \
     *     	  6   10
     *     	 / \  / \
     *     	5  7 9 11
     *     	镜像二叉树
     *     	    8
     *     	   /  \
     *     	  10   6
     *     	 / \  / \
     *     	11 9 7  5
     * */
    public static void Mirror(BTreeNode root) {
        /**
         * 递归从下往上交换
         * */
        if (root==null)
            return;
        if (root.left!=null||root.right!=null){
            // 有左右子树，需要交换
            // 递归处理子树
            if (root.left!=null)
                Mirror(root.left);
            if (root.right!=null)
                Mirror(root.right);
            // 交换左右子树，交换代码可以在递归代码之前（从上到下交换）
            BTreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
    }

    public static void Mirror2(BTreeNode root){
        /**
         * 循环-从上往下交换
         * */
        if (root==null)
            return;
        Deque<BTreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BTreeNode ele = stack.pop();
            if (ele.left!=null||ele.right!=null){
                // 交换左右子树
                BTreeNode temp = ele.left;
                ele.left = ele.right;
                ele.right = temp;
            }
            if (ele.left!=null)
                stack.push(ele.left);
            if (ele.right!=null)
                stack.push(ele.right);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {};
        BTreeNode tree1 = TreeUtil.array2BTree(arr1);
        Mirror(tree1);
    }

}
