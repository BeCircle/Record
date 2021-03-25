package algorithm;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/17
 */
public class ReverseKGroup {
    /**
     * 给出一个链表，每 k 个节点为一组进行翻转，并返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
     * 示例 :
     * 给定这个链表：1->2->3->4->5
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * 说明 :
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * */
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    ListNode reverseKGroup(ListNode head, int k){
        if (head==null|| k==1) return head;
        // head可能受影响，增加哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = pre.next;
        int i = 0;
        while (cur!=null){
            i++;
            if (i%k==0){
                // pre和cur.next都不包括
                pre = reverseOneGroup(pre, cur.next);
                cur = pre.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static ListNode reverseOneGroup(ListNode pre, ListNode next){
        // 上一个
        ListNode last = pre.next;
        ListNode curr = last.next;
        while (curr!=next){
            // 交换curr和pre到last之间的节点
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            // 右移curr
            curr = last.next;
        }
        return last;
    }
}
