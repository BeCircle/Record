package Offer;

import util.BTreeNode;

public class CommonFatherInBTree {
    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * */
    public BTreeNode lowestCommonAncestor(BTreeNode root, BTreeNode p, BTreeNode q) {
        /* 利用BST的特点
        * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        * 输出: 6
        * pq和root的大小关系共9种，第一个if涵盖了7种
        * */

        if (p.val==root.val||q.val==root.val||(p.val<root.val&&q.val>root.val)||(p.val>root.val&&q.val<root.val)){
            return root;
        }else if (p.val < root.val){// p&&q<root
            return lowestCommonAncestor(root.left, p,q);
        }
        else {// p&&q>root
            return lowestCommonAncestor(root.right, p,q);
        }

    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
     * */
    public BTreeNode lowestCommonAncestor2(BTreeNode root, BTreeNode p, BTreeNode q) {
        /***
         *递归，左右子树中没有目标，返回null；
         **/
        if(root == null||root.val==p.val||root.val==q.val){
            return root;
        }

        BTreeNode lRes = lowestCommonAncestor2(root.left, p, q);
        BTreeNode rRes = lowestCommonAncestor2(root.right, p, q);
        if(lRes!=null&&rRes!=null){// 左右子树各一个，root就是结果
            return root;
        }
        else if(lRes != null){
            return lRes;
        }
        else {
            return rRes;
        }
    }
}
