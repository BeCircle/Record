package algorithm;

public class reverseInteger {
    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转.
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * */
    public int reverse(int x) {
        /**
         * 123 转成321可能超过int容量
         * */
        long res=0;
        while (x!=0) {
            res = 10*res + x % 10;
            x /= 10;
        }
        if(res>=0x7fffffff || res<=0x80000000){
            return 0;
        }
        return (int)res;
    }
}
