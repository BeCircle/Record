package Offer;

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
public class PostOrderOfBTree {

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     * BST 左子树<根节点<右子树；
     * Post Order：末尾节点是根节点
     */
    public static boolean VerifySquenceOfBST(int[] sequence) {
        boolean res = false;
        if (sequence.length > 0)
            res = verifySeq(sequence, 0, sequence.length-1);
        return res;
    }

    private static boolean verifySeq(int[] sequence, int l, int r) {
        // 左右节点都属于那一段
        if (l>=r-1){
            // 递归终点
            return true;
        }
        // 根节点
        int root = sequence[r];
        // 小于根节点的个数
        int mid = l-1;
        while (mid+1 < r && sequence[mid+1] <= root) {
            mid++;
        }
        // 大于根节点的个数
        int larger = mid;
        while (larger+1 < r && sequence[larger+1] > root) {
            larger++;
        }
        return larger == r - 1
                && verifySeq(sequence, l, mid)
                && verifySeq(sequence, mid+1, r-1);
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
//        4,8,6,12,16,14,10
        // 1,2,3,4,5
        System.out.println(VerifySquenceOfBST(array));
    }

}
