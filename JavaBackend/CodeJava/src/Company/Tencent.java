package Company;

import org.junit.Assert;

import java.util.Arrays;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/20
 */
public class Tencent {
    public static void main(String[] args) {
        int arr[] = {34, 45, 56, 6, 7, 78, 24, 76, 22, 11};
        System.out.println(hindex(arr, arr.length));
    }

    /**
     * 腾讯云中台，实习一面
     * 某个人n文章引用大于n；
     */
    public static int hindex(int[] arr, int size) {
        if (size > 0) {
            // 递增排序
            Arrays.sort(arr);
            // 从大到小遍历
            int paperCount = 0;
            for (int i = size - 1; i >= 0; i--) {
                int index = arr[i];
                if (paperCount >= index) {
                    // 当文章数增加到大于等于引用数时得到第一个结果
                    return paperCount;
                } else {
                    paperCount++;
                }
            }
        }
        return 0;
    }

    /**
     * 1. 实现 string的indexOf() 函数。
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     */
    @org.junit.Test
    public void testProb1() {
        Assert.assertEquals(problem1("hello", "ll"), 2);
        Assert.assertEquals(problem1("aaaaa", "bba"), -1);
    }

    public static int problem1(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int p = i;
            int j = 0;
            for (; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(p)) {
                    break;
                }
                // 当前字符匹配
                p++;
            }
            // needle完全匹配
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     * 示例 1:
     * 输入: [10,2]
     * 输出: 210
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: 9534330
     * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * 比较同一位数，该位相同的，比较
     */
    private static boolean bigger(int a, int b) {
        // a>b
        int comb1 = Integer.parseInt(a + "" + b);
        int comb2 = Integer.parseInt(b + "" + a);
        return comb1 > comb2;
    }

    @org.junit.Test
    public void runProb2() {
        int[] arr = {3, 30, 34, 5, 9};
        Assert.assertEquals(prob2(arr), "9534330");
    }

    public static String prob2(int[] arr) {
        // 按照一定规律排序
        quickSort(arr, 0, arr.length - 1);
        // 组合
        StringBuilder res = new StringBuilder();
        for (int i : arr) {
            res.append(i);
        }
        return res.toString();
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int index = partition(arr, l, r);
        quickSort(arr, l, index - 1);
        quickSort(arr, index + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int temp = arr[l];
        while (l < r) {
            while (l < r && !bigger(arr[r], temp)) {
                r--;
            }/// 寻找大于temp的数，
            arr[l] = arr[r];
            while (l < r && bigger(arr[l], temp)) {
                l++;
            }//寻找小于temp的数
            arr[r] = arr[l];
        }
        arr[l] = temp;
        return l;
    }
}
