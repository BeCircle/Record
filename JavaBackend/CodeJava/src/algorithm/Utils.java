package algorithm;

import java.util.List;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/12
 */
public class Utils {
    public static void printLinkList(ListNode head) {
        // 打印链表
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val);
            System.out.print("->");
            p = p.next;
        }
        System.out.print("\n");
    }

    public static void printArray(int[] nums) {
        for (int item : nums) {
            System.out.print(item + "\t");
        }
        System.out.print("\n");
    }

    public static void print2DList(List<List<Integer>> input) {
        for (List<Integer> line : input) {
            for (Integer item : line) {
                System.out.print(item + "\t");
            }
            System.out.print("\n");
        }
    }
}
