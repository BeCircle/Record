package util;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/19
 */
public class TreeUtil {
    /**
     * @param array int数值
     * @return 根据array构建的二叉树头结点
     * */
    public static BTreeNode array2BTree(int [] array){
        BTreeNode pRoot = null;
        if (array.length>0) {
            pRoot = new BTreeNode(array[0]);
            recurBuild(pRoot, 0, array);
        }
        return pRoot;
    }

    /**
     * @param pRoot 当前根节点
     * @param index 当前节点在数组中的索引
     * @param array 数组
     * 递归构建二叉树
     * */
    private static void recurBuild(BTreeNode pRoot, int index, int [] array){
        int left = 2*index+1;
        if (left<array.length){
            pRoot.left = new BTreeNode(array[left]);
            recurBuild(pRoot.left, left, array);
        }
        int right =2*index+2;
        if (right<array.length){
            pRoot.right = new BTreeNode(array[right]);
            recurBuild(pRoot.right, right, array);
        }
    }
}
