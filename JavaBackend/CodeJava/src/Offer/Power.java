package Offer;

public class Power {
    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证 base 和 exponent 不同时为0
     * 1. 本题多种情况需要考虑完整
     * 0 -ex 错误
     * 0 其他 0
     * base 0 1
     * <p>
     * 2. 暴力乘 exponent 次 base 效率太低，应该减少计算
     */
    public static double Power(double base, int exponent) {
        if (base == 0 && exponent >= 0) {
            return 0;
        } else if (exponent == 0) {
            return 1;
        } else {
            // 记录符合
            boolean negExp = false;
            if (exponent < 0) {
                exponent = -exponent;
                negExp = true;
            }

            boolean negBase = false;
            if (base < 0) {
                base = -base;
                // 奇数次方最后结果才为负
                negBase = exponent % 2 == 1;
            }
            // 取得绝对值
            double res = 1, cur = 1;
            while (exponent != 0) {
                // 当前bit代表的数
                cur *= base;
                // 由低到高遍历每一bit
                if ((exponent & 1) > 0) {
                    res *= cur;
                }
                exponent >>= 1;
            }

            if (negExp) res = 1 / res;
            if (negBase) res = -res;
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(Power(2, 3));
    }
}
