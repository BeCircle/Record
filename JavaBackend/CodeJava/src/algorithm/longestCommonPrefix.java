package algorithm;

public class longestCommonPrefix {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * <p>
     * 所有输入只包含小写字母 a-z 
     * <p>
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     */
    public String lCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        } else if (strs.length > 1) {
            for (int i = 0; i < strs[0].length(); i++) {
                for (int j = 1; j < strs.length; j++) {
                    if (i > strs[j].length() - 1 || strs[j].charAt(i) != strs[0].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        longestCommonPrefix so = new longestCommonPrefix();
        String[] input = {"", "b"};
        System.out.println(so.lCommonPrefix(input));

    }
}
