package Offer;

import util.BTreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/19
 */
public class PrintBTreeZ {
    /**
     * 请实现一个函数按照之字形打印二叉树，
     * 即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，
     * 第三行按照从左到右的顺序打印，其他行以此类推。
     */


    public static ArrayList<ArrayList<Integer>> Print(BTreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot != null) {
            // 双向链表当成栈使用
            ArrayList<LinkedList<BTreeNode>> caches = new ArrayList<>();
            caches.add(new LinkedList<>());
            caches.add(new LinkedList<>());
            int layer = 1;
            // 将第一个节点加入cache
            caches.get(layer % 2).push(pRoot);
            while (caches.get(layer % 2).size() > 0) {
                result.add(new ArrayList<>());
                int flag = layer % 2;
                int size = caches.get(flag).size();
                for (int i = 0; i < size; i++) {
                    BTreeNode item = caches.get(flag).pop();
                    if (item != null) {
                        // 已经由栈产生逆序效果
                        result.get(result.size() - 1).add(item.val);
                        if (flag == 0) {
                            // 偶数层右左，下一层出栈时就是左右
                            if (item.right != null) {
                                caches.get(1 - flag).push(item.right);
                            }
                            if (item.left != null) {
                                caches.get(1 - flag).push(item.left);
                            }
                        } else {
                            // 奇数层左右，下一层出栈时就是右左
                            if (item.left != null) {
                                caches.get(1 - flag).push(item.left);
                            }
                            if (item.right != null) {
                                caches.get(1 - flag).push(item.right);
                            }
                        }
                    }
                }
                layer++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {8, 6, 10, 5, 7, 9, 11};
        BTreeNode inTree = TreeUtil.array2BTree(array);
        ArrayList<ArrayList<Integer>> res = Print(inTree);
    }
}
