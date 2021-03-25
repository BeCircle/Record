package algorithm;

import java.util.*;

public class LSubSWithoutRepChar {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * " "
     * 思路：循环遍历，用queue装遍历的char，如果遇到重复，则清空重复char及其之前的char，与历史最大比较。
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int largest = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                // 重复字符的位置,abba这个用例，l>map.get(a)
                l = Math.max(l, map.get(c));
            }
            // 存储当前c的新位置，这里不加1，" "时，r-l=0;
            largest = Math.max(r - l+1, largest);
            map.put(c, r+1);
        }
        return largest;
    }
}
