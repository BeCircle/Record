package algorithm;

import util.BTreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/24
 */
public class TreeTraversal {
    /**
     * 中序遍历二叉树
     */
    public static List<Integer> midOrderOfBT(BTreeNode pRoot) {
        LinkedList<BTreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        BTreeNode p = pRoot;
        while (!stack.isEmpty() || p != null) {
            // 当前子树上向左探索
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                // p为空表示上一个节点没有左子，出栈，访问右儿子
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
        }
        return result;
    }

    /**
     * 前序遍历二叉树
     */
    public static List<Integer> preOrderOfBT(BTreeNode pRoot) {
        LinkedList<BTreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        BTreeNode p = pRoot;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                // 出栈是为了访问右子树（中序），入栈之前就输出值（前序）
                result.add(p.val);
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历二叉树
     */
    public static List<Integer> postOrderOfBT(BTreeNode pRoot) {
        LinkedList<BTreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        BTreeNode p = pRoot;
        BTreeNode lastVisited = null;
        while (!stack.isEmpty() || p != null) {
            // 当前子树上向左探索
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                // p为空表示上一个节点没有左子，出栈，访问右儿子
                p = stack.peek();
                if (p.right == null) {
                    result.add(p.val);
                    stack.pop();
                    lastVisited = p;
                } else if (p.right == lastVisited) {
                    // 右子树访问完可能需要多次出栈
                    while (p != null && p.right != null && p.right == lastVisited) {
                        result.add(p.val);
                        stack.pop();
                        BTreeNode visited = p;
                        // 多回溯一次
                        p = stack.peek();
                        lastVisited = visited;
                    }
                }
                if (p != null)
                    p = p.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        BTreeNode inTree = TreeUtil.array2BTree(array);
        List<Integer> res = midOrderOfBT(inTree);
        for (int i : res) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");

        List<Integer> res2 = preOrderOfBT(inTree);
        for (int i : res2) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");

        List<Integer> res3 = postOrderOfBT(inTree);
        for (int i : res3) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");
    }

}
