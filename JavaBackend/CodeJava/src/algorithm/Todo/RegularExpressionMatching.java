package algorithm.Todo;


public class RegularExpressionMatching {
    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     */
    public static boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int pIndex = 0;
        int sIndex = 0;
        // 两个字符串都未结束
        while (sIndex < sLen && pIndex < pLen) {
            char cS = s.charAt(sIndex);
            char cP = p.charAt(pIndex);
            if (cS >= 'a' && cS <= 'z' && ((cP >= 'a' && cP <= 'z') || cP == '*' || cP == '.')) {
                if (cP >= 'a' && cP <= 'z') {
                    if (pIndex + 1 < pLen && p.charAt(pIndex + 1) == '*') {
                        // 字母后带星号
                        if (cS != cP) {
                            return false;
                        }
                    } else {
                        // 字母后不带星号
                        if (cS != cP) {
                            return false;
                        }
                        cP++;
                    }
                } else if (cP == '.') {
                    if (pIndex + 1 < pLen && p.charAt(pIndex + 1) == '*') {
                        // 点后带星号
                    } else {
                        // 点后不带星号
                        cP++;
                    }
                }
                sIndex++;
            }else {
                // 异常字符
                return false;
            }
        }
        // TODO ".*c" 以上思路不可行
        return false;
    }


    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }
}
