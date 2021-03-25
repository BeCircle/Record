package Offer;

import util.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/28
 */
public class PrintListFromTail2Head {
    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        /**
         * 递归的方式
         * */
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode != null) {
            printTrail2Head(listNode, res);
        }
        return res;
    }

    private static void printTrail2Head(ListNode listNode, ArrayList<Integer> res) {
        if (listNode != null) {
            printTrail2Head(listNode.next, res);
            // 递归返回之后将节点加入res，实现倒序
            res.add(listNode.val);
        }
    }

    public ArrayList<Integer> printListFromTailToHeadLoop(ListNode listNode) {
        /**
         * 循环+栈实现
         * */
        // 入栈
        LinkedList<Integer> store = new LinkedList<>();
        while (listNode!=null){
            store.push(listNode.val);
            listNode = listNode.next;
        }
        // 出栈
        ArrayList<Integer> res = new ArrayList<>();
        while (store.size()>0) {
            res.add(store.pop());
        }
        return res;
    }

}
