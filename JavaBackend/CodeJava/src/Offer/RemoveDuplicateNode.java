package Offer;

import java.util.List;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/16
 */
public class RemoveDuplicateNode {
    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
     * 重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     */
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        // 头结点也可能删除，使用哑结点
        ListNode hairNode = new  ListNode(-1);
        hairNode.next = pHead;
        if (pHead != null&& pHead.next!= null) {
            ListNode lastNode = hairNode;
            while (lastNode.next != null) {
                ListNode pointer = lastNode.next;
                boolean dup = false;
                while (pointer.next!=null && pointer.next.val == pointer.val){
                    pointer = pointer.next;
                    dup = true;
                }
                if (dup){
                    lastNode.next = pointer.next;
                }else {
                    // 只有命中重复时才后移lastNode，命中之后即使跳过了重复点，也可能导致新的next是重复节点
                    lastNode = lastNode.next;
                }
            }
        }
        return hairNode.next;
    }

    public static ListNode array2List(int[] array) {
        //根据array构造链表
        ListNode pHead = new ListNode(-1);
        ListNode pointer = pHead;
        for (int value : array) {
            pointer.next = new ListNode(value);
            pointer = pointer.next;
        }
        return pHead.next;
    }

    public static void printLinkList(ListNode pHead) {
        ListNode pointer = pHead;
        while (pointer != null) {
            System.out.print(pointer.val + "--");
            pointer = pointer.next;
        }
        System.out.println(".");
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,3,4,4,5};
        ListNode input = array2List(array);
        printLinkList(input);
        ListNode output = deleteDuplication(input);
        printLinkList(output);
    }
}
