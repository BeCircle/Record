package Offer;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrNoRepeat {
    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
     * */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()<=1){
            return s.length();
        }
        Map<Character, Integer> subStr = new HashMap<>();
        int l=0, longest=1;
        for (int i=0, len = s.length(); i<len; i++){
            char c = s.charAt(i);
            if (subStr.containsKey(c)){
                if (i-l>longest){
                    longest = i-l;
                }
                int newL = subStr.get(c);
                for (int j = l; j <=newL ; j++) {
                    char t = s.charAt(j);
                    subStr.remove(t);
                }
                l=newL+1;
            }
            subStr.put(c, i);
        }
        if (subStr.size()>longest){
            longest = subStr.size();
        }
        return longest;
    }

    public static void main(String[] args) {
        String [] cases = { "pwwkew","au","abcabcbb", "bbbbb"};
        for (String testCase : cases) {
            System.out.println(lengthOfLongestSubstring(testCase));
        }
    }
}
