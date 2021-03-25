package algorithm;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/16
 */
public class MultiplyStrings {
    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 示例 1:
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     */
    public static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len2 - 1; i >= 0; i--) {
            int n2 = num2.charAt(i) - '0';
            // 从右访问每一位num2；
            for (int j = len1 - 1; j >= 0; j--) {
                // 从右访问每一位num1；
                int n1 = num1.charAt(j) - '0';
                int temp = n1 * n2;
                // 存入暂存区，暂不处理进位
                res[i + j + 1] += temp;
            }
        }
        // 统一进位处理
        int ten = 0;
        for (int i = len1 + len2 - 1; i >= 0; i--) {
            res[i] += ten;
            if (res[i] >= 10) {
                ten = res[i] / 10;
                res[i] %= 10;
            }else{
                // 防止受前面影响
                ten = 0;
            }
        }
        StringBuilder result = new StringBuilder();
        boolean start = false;
        for (int i : res) {
            if (i > 0 || (i == 0 && start)) {
                start = true;
                result.append(i);
            }
        }
        if (!start){
            // 结果为0
            result.append(0);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "5";
        String num2 = "12";
        System.out.println(multiply(num1, num2));
    }
}
