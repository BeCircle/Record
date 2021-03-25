package algorithm;

public class Atoi {

    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * 说明：
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231)
     *
     * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
     * */
    public static int myAtoi(String str) {
        /**
         * 1. 跳过开头空格字符
         * 2. 第一个有效字符可能是正负号
         * 3. 数字后多余字符应该不收影响
         *
         * */
        long res = 0;
        int index = 0;
        // 正负号
        int positive = 1;
        int len = str.length();
        // 跳过空格
        while (index < len && str.charAt(index) == 32){
            index ++;
        }
        // 判断正负号
        if (index <len) {
            if (str.charAt(index) == '-') {
                positive = -positive;
                index ++;
            } else if (str.charAt(index) == '+'){
                index ++;
            }
        }
        while (index <len && 48<=str.charAt(index)&& str.charAt(index)<=58){
            res = res*10 + positive * (str.charAt(index)-48);
            if (res >= 0x7fffffff){
                res = 0x7fffffff;
                break;
            }else if(res <= 0x80000000){
                res = 0x80000000;
                break;
            }
            index ++;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));
    }
}
