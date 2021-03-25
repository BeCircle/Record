package Offer;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/13
 */
public class ReverseList {
    public static class ListNode{
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
        }
    }
    public static ListNode ReverseList(ListNode head){
        /**
         * S1. 3指针向下，逐渐修改顺序
         * S2. 利用递归，向下搜索，然后向上回退时构建反向链表；
         * */
        ListNode newHead = new ListNode(-1);
        // null 不能传递给递归作为函数引用
        if (head!=null) {
            recursive(newHead, head);
            // 将原首节点的next置为空
            head.next = null;
        }
        return newHead.next;
    }
    public static void recursive(ListNode newHead, ListNode head){
        if (head.next == null){
            // 到达尾结点
            newHead.next = head;
        }else {
            recursive(newHead, head.next);
                head.next.next = head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode pointer = head;
        for (int i = 1; i < 10; i++) {
            pointer.next = new ListNode(i);
            pointer = pointer.next;
        }
        ReverseList(head);
    }
}

