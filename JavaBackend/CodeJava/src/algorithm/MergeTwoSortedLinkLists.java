package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MergeTwoSortedLinkLists {
    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     */
    public static  ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 头结点
        ListNode nHead = null;
        if (l1==null){
            nHead= l2;
        }else if(l2 == null){
            nHead=l1;
        }else{
            // 确定头结点
            if (l1.val<l2.val){
                nHead = l1;
                l1=l1.next;
            }else{
                nHead=l2;
                l2= l2.next;
            }
            ListNode nCurr = nHead;
            while (l1 != null && l2!=null){
                if (l1.val<l2.val){
                    nCurr.next = l1;
                    l1 = l1.next;
                }else{
                    nCurr.next = l2;
                    l2 = l2.next;
                }
                nCurr = nCurr.next;
            }
            // 至少一个链表合并结束
            if (l1==null){
                nCurr.next=l2;
            }else {
                nCurr.next=l1;
            }
        }
        return nHead;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = mergeTwoLists(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}
