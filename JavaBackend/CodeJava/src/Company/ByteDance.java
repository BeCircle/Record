package Company;

import java.util.LinkedList;

public class ByteDance {
    /**
     * 奇数从左，偶数从右
     */
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int v) {
            val = v;
        }
    }

    private static void printTress(Node root) {
        // 异常输入
        if (root == null) {
            return;
        }
        // 存储每一层节点
        LinkedList<Node> layer1 = new LinkedList<>();
        LinkedList<Node> layer2 = new LinkedList<>();
        layer1.add(root);
        // 遍历中的层
        LinkedList<Node> inUse = layer1;
        int layerInUse = 1;
        while (inUse.size() > 0) {
            if (layerInUse % 2 == 0) {
                // 从右往左遍历
                // 1   2
                // 3 4 5 6 addFirst
                while (inUse.size() > 0) {
                    Node item = inUse.pollLast();
                    System.out.println(item.val);
                    if (item.right != null) {
                        layer2.addFirst(item.right);
                    }
                    if (item.left != null) {
                        layer2.addFirst(item.left);
                    }
                }
                inUse = layer1;
            } else {
                // 从左到右遍历
                while (inUse.size() > 0) {
                    Node item = inUse.pollFirst();
                    System.out.println(item.val);
                    if (item.left != null) {
                        layer2.addLast(item.left);
                    }
                    if (item.right != null) {
                        layer2.addLast(item.right);
                    }
                }
                inUse = layer2;
            }
            layerInUse++;
        }
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        a.left = new Node(2);
        a.right = new Node(3);
        a.left.left = new Node(4);
        a.left.right = new Node(5);
        a.right.left = new Node(6);
        a.right.right = new Node(7);
        printTress(a);
    }
}