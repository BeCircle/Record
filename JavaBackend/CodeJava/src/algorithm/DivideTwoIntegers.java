package algorithm;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/13
 */
public class DivideTwoIntegers {
    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * 示例 1:
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 示例 2:
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 说明:
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     * 链接：https://leetcode-cn.com/problems/divide-two-integers
     * */
    public static int divide(int dividend, int divisor) {
        /**
         * 正数，负数都1以补码形式存储
         * 正数右移左边填0，负数右移左边填1，->数字变宽时数值和符号都不变。
         * */
        boolean negative = (dividend ^divisor) <0;
        // 负数的范围更大
        if (dividend>0) dividend = -dividend;
        if (divisor>0) divisor = -divisor;
        int res = 0;
        while (dividend <=divisor){
            int tempDivisor = divisor;
            int tempRes = -1;
            // 这里可从2^31 次方往下循环，并且下一次恶意用更小的次方
            // tempDivisor >= (Integer.MIN_VALUE >> 1) 再扩大两倍tempDivisor就溢出了
            while (dividend<=tempDivisor<<1 && tempDivisor >= (Integer.MIN_VALUE >> 1)){
                // 大于除数的2^n
                tempDivisor <<=1;
                tempRes <<=1;
            }
            dividend -= tempDivisor;
            res += tempRes;
        }
        if (!negative){
            if(res <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
            res = -res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -1));
    }
}
