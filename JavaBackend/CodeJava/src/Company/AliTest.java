package Company;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/5
 */
public class AliTest {
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public ListNode str2List(String num) {
        // 字符串转链表
        ListNode dummy = new ListNode(-1);
        for (int i = 0; i < num.length(); i++) {
            ListNode temp = new ListNode(num.charAt(i) - '0');
            ListNode old = dummy.next;
            dummy.next = temp;
            temp.next = old;
        }
        return dummy.next;
    }

    public ListNode add2LinkedList(ListNode head1, ListNode head2) {
        // 输入为低位在前的链表
        // 哑节点
        ListNode dummy = new ListNode(0);

        ListNode p1 = head1, p2 = head2;
        ListNode current = dummy;
        int value = 0;
        while (p1 != null || p2 != null) {
            // 为null时，高位填充0
            int x = (p1 != null) ? p1.val : 0;
            int y = (p2 != null) ? p2.val : 0;

            // 进位和当前位的和
            int sum = value + x + y;
            value = sum / 10; // 进位
            // 余数保存在当前位
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        // 还有加完之后还有进位
        if (value > 0) {
            current.next = new ListNode(value);
        }
        return dummy.next;
    }

    public void runProb1() {
        String n1 = "345";
        String n2 = "456";
        // 构造链表
        ListNode h1 = str2List(n1);
        ListNode h2 = str2List(n2);
        // 求和
        ListNode res = add2LinkedList(h1, h2);
        // 展示结果
        boolean start = true;
        while (res != null) {
            if (!start) {
                System.out.print("->");
            }
            System.out.print(res.val);
            res = res.next;
            start = false;
        }
    }

    class BtNode {
        public int val;
        public BtNode left;
        public BtNode right;

        BtNode(int v) {
            val = v;
        }
    }

    public void sumBTreePath() {
        BtNode root = new BtNode(2);
        root.left = new BtNode(3);
        root.right = new BtNode(4);
        root.right.right = new BtNode(5);

        System.out.println(reverse(root, 0));
    }

    private int reverse(BtNode father, int high) {
        // 深度优先遍历
        if (father == null) {
            return 0;
        }
        // 高位的值
        int sum = 10 * high + father.val;
        if (father.left == null && father.right == null) {
            return sum;
        }else {
            return reverse(father.left, sum) + reverse(father.right, sum);
        }
    }


    public static void main(String[] args) {
        AliTest ali = new AliTest();
//        ali.runProb1();

        ali.sumBTreePath();
    }
}
