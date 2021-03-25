package algorithm;

import static algorithm.Utils.printLinkList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/10
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     */

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * S1. 双指针，前一个指针先走n步，两指针一起移动，直到前指针遇到null；
         * */
        if (head == null) {
            return head;
        }
        // 哑节点用于处理需要删除head节点的情况
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode P1 = dummy;
        ListNode P2 = dummy;

        // P1先走n步
        for (int i = 0; i <= n; i++) {
            P1 = P1.next;
        }
        // 定位节点

        while (P1 != null) {
            P1 = P1.next;
            P2 = P2.next;
        }
        // 删除节点
        P2.next = P2.next.next;
        return dummy.next;
    }


    public static void main(String[] args) {
        // 构造链表
        ListNode head = new ListNode(0);

        // 去除节点之前
        printLinkList(head);
        // 去除节点
        head = removeNthFromEnd(head, 1);
        // 去除节点之后
        printLinkList(head);
    }
}
