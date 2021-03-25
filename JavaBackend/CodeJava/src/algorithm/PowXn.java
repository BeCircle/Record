package algorithm;

public class PowXn {
    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * 示例 1:
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * <p>
     * 示例 2:
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * <p>
     * 示例 3:
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     * 链接：https://leetcode-cn.com/problems/powx-n
     * <p>
     * n为负数，最后取分数
     * x为负数，根据n的奇数偶数来确定负号
     * 挨个乘法复杂度O(n), 分奇数偶数乘法;
     * -2147483648 是最小的int，无法直接取负数,先转为long之后在取负数
     */

    public static double myPow(double x, int n) {
        // 循环写法
        // 先扩容，再转负号
        long pow = n;
        if (n <0){
            pow = -pow;
            x = 1/x;
        }
        return PowCore(x, pow);
    }


    public double powIteration(double x, int n) {
        /**
         * 考虑n的二进制
         * eg. n=10 = 1010(2)
         * x^10 = x^(1*2^3)+x^(0*2^2)+x^(1*2^1)+x^(0*2^0)
         * */
        double res = 1;
        double curr=x;
        while (n>0){
            // 扫描每一位
            if ((n^1) == 1){
                // 某一位为1
                res *= curr;
            }
            curr *= curr;
            n >>=1;
        }
        return res;
    }

    public static double PowCore(double x, long n) {
        double res;
        if (n == 0) {
            res = 1.0;
        } else {
            double half = PowCore(x, n / 2);
            if (n % 2 == 1) {
                // 奇数次方
                res = x * half* half;
            } else {
                // 偶数次方
                res  = half * half;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(myPow(1,
                -2147483648));
    }
}
