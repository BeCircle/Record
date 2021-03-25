package algorithm;

import java.util.Scanner;

public class palindromeNumber {
    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 示例 1:
     * 输入: 121
     * 输出: true
     * 示例 2:
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     *
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     * */

    public boolean isPalindrome(int x) {
        // S1. 将整数转为字符串
        String inStr = Integer.toString(x);
        // S2. 同时从左和从右对比
        int i=0, j=inStr.length()-1;
        while (i<j){
            if (inStr.charAt(i) !=inStr.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 不转换成字符串的解法
    public boolean isPalindrome2(int x) {
        // 负数直接false
        // 依次取得高位和最低位的数比较TODO
//        11010
//        01010

        if (x <0){
            return false;
        }else{
            // TODO
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        palindromeNumber so = new palindromeNumber();
        while (sc.hasNext()){
            int input = sc.nextInt();
            System.out.println(so.isPalindrome(input));
        }

    }
}
