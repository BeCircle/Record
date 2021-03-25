package Company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/29
 */
public class Shopee {

    public static boolean test1(String strIn) {
        // 回文串
        // 特殊情况
        if (strIn.equals(""))
            return true;
        int pStart = 0;
        int pEnd = strIn.length() - 1;
        // 从两边向中间靠近
        while (pStart < pEnd) {
            //  忽略非数字和字母的字符
            while (!((strIn.charAt(pStart) >= 'a' && strIn.charAt(pStart) <= 'z') || (strIn.charAt(pStart) >= '0' && strIn.charAt(pStart) <= '9')))
                pStart++;
            while (!((strIn.charAt(pEnd) >= 'a' && strIn.charAt(pEnd) <= 'z') || (strIn.charAt(pEnd) >= '0' && strIn.charAt(pEnd) <= '9')))
                pEnd--;
            if (strIn.charAt(pStart) != strIn.charAt(pEnd)) {
                return false;
            }
            pStart++;
            pEnd--;
        }
        return true;
    }

    public static ArrayList<Integer> test2(ArrayList<Integer> nums) {
        // 数组右边比自己小的元素个数
        // [4,3,9,2] ->[2,1,1,0]
        // 存储结果
        int len = nums.size();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            res.add(0);
        }
        // 从右到左扫描nums
        for (int i = len - 1; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums.get(j) < nums.get(i)) {
                    // 右边比自己小
                    count++;
                }
                if (nums.get(i).equals(nums.get(j))) {
                    count += res.get(j);
                    break;
                }
            }
            res.set(i, count);
        }
        return res;
    }

    public static ArrayList<Integer> test2_2(ArrayList<Integer> nums) {
        class Node {
            int val;
            int count;

            Node(int v, int c) {
                val = v;
                count = c;
            }
        }

        int len = nums.size();
        LinkedList<Node> stack = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            res.add(0);
        }
        for (int i = len - 1; i >= 0; i--) {
            int item = nums.get(i);
            int oldC = 0;
            if (stack.size() > 0) {
                while (stack.getFirst().val >= item) {
                    stack.pop();
                }
                if (stack.size() > 0) {
                    oldC = stack.getFirst().count + 1;
                }
            }
            stack.push(new Node(item, oldC));
            res.set(i, oldC);
        }
        return res;
    }

    public static void runTest2() {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        String str = in.nextLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9')
                input.add(c - '0');
        }
        in.close();
        ArrayList<Integer> res = test2_2(input);
        System.out.println(res.toString());
    }


    public static void test3() {
        // 嵌套列表字符串替换为"\n"分割
        // ["a",["b", "c"], "d"]
        // "a"\n"b"\n"c"\n"d"
    }

    public static void runTest3() {
        Scanner in = new Scanner(System.in);
        List<Object> inList = new ArrayList<>();
        String str = in.nextLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
        }
        in.close();
    }

    public static void interview1(String[] args) {
        // 最大连续子数组 561234589
        int[] inArray = {5, 6, 1, 2, 3, 4, 5, 8, 9};
        int lastLen = 0;
        int i = 0;
        int thisLen = 0;
        while (i < inArray.length) {
            if (i == 0 || (i > 0 && inArray[i] == inArray[i - 1] + 1)) {
                thisLen++;
            } else {
                lastLen = Math.max(lastLen, thisLen);
                thisLen = 1;
            }
            i++;
        }
        lastLen = Math.max(lastLen, thisLen);
        System.out.println(lastLen);
    }

    public static void main(String[] args) {
        runTest2();
//        runTest3();
    }
}
