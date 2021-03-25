package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/9
 */
public class LetterCombinationsOfPhoneNumber {
    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     */
    public static final char[][] numCharMap = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'o', 'n'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    public static List<String> letterCombinations(String digits) {
        /**
         * S1. 回溯
         * 字符串长度就是每种组合的长度；
         * 扫描一遍字符可以知道组合的个数；
         * */
        List<String> result = new ArrayList<>();
        if (digits!=null && digits.length()>0) {
            int len = digits.length();
            char[] comb = new char[len];
            recursive(result, comb, digits, 0);
        }
        return result;
    }

    public  static void recursive(List<String> result, char[] comb, String digits, int deepth){
        if (deepth> digits.length()-1){
            // 叶节点，产生组合结果
            result.add(String.valueOf(comb));
        }else {
            int index = digits.charAt(deepth)-'2';
            char [] chars = numCharMap[index];
            for (char c:chars){
                comb[deepth] = c;
                recursive(result, comb, digits, deepth+1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> result = letterCombinations("23");
        for (String res:result){
            System.out.println(res);
        }
    }
}
