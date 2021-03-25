package util;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/19
 */
public class BTreeNode {
    public int val = 0;
    public BTreeNode left = null;
    public BTreeNode right = null;

    public BTreeNode(int val) {
        this.val = val;
    }
}