package Offer;

import util.BTreeNode;

import java.util.LinkedList;

public class kThLargestInBST {
    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 4
     */
    public int kthLargest2(BTreeNode root, int k) {
        // 非递归方案
        LinkedList<BTreeNode> stack = new LinkedList<>();
        int sum = 0, ans = -1;
        BTreeNode point = root;
        while (stack.size()>0||point!=null){
            // 当前节点的右节点递归入栈
            while (point!=null){
                stack.push(point);
                point = point.right;
            }
            // 右节点处理完，出栈最新的，判断是否有左节点
            point = stack.pop();
            if (++sum == k){
                ans = point.val;
                break;
            }
            // left为null也要赋值，下一轮pop
            point = point.left;
            // 下一轮从这个左节点开始，递归处理它及其右节点
        }
        return ans;
    }
    public int kthLargest(BTreeNode root, int k) {
        // ans 和sum
        // 利用数组传递引用，基本类型是值传递
        int[] cache = {0, 0};
        traceBack(root, cache, k);
        return cache[0];
    }

    private void traceBack(BTreeNode father,int[] cache,  int k) {
        if (father!=null) {
            if (father.right != null) {// 右子树
                traceBack(father.right,cache,  k);
            }
            // 这里++在前，表示加上当前father之后的个数
            if (++cache[1] == k) {// 父节点
                cache[0] = father.val;
                return;
            }
            if (cache[1] < k && father.left != null) {// 左子树
                traceBack(father.left,cache, k);
            }
        }
    }
}
