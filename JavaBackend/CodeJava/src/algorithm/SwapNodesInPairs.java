package algorithm;

import static algorithm.Utils.printLinkList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/12
 */
public class SwapNodesInPairs {
    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head != null && head.next != null ) {
            ListNode last = dummy;
            ListNode P1,P2;
            while (last.next!=null && last.next.next!=null){
                P1=last.next;
                P2=last.next.next;
                // swap
                last.next = P2;
                P1.next = P2.next;
                P2.next = P1;
                // move
                last=P1;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        // 构造链表
        for (int i = 2; i < 10; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }

        // 交换节点之前
        printLinkList(head);
        head=swapPairs(head);
        // 交换节点之后
        printLinkList(head);
    }
}
