package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/11
 */
public class GenerateParentheses {
    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     */
    public static List<String> generateParenthesis(int n) {
        /**
         * S1. 回溯，有左括号才能有右括号
         * */
        int left = 0, right = 0;
        char[] comb = new char[2 * n];
        List<String> result = new ArrayList<>();
        traceBack(result, comb, n, left, right);
        return result;
    }

    public static void traceBack(List<String> result, char[] comb, int total, int left, int right) {
        if (left >= total && right >= total) {
            // 回溯叶节点
            result.add(String.valueOf(comb));
        }
        // 左括号使用个数大于右括号
        if (left < total) {
            // 还可以继续使用左括号
            comb[left + right] = '(';
            traceBack(result, comb, total, left + 1, right);
        }
        if (left > right) {
            comb[left + right] = ')';
            traceBack(result, comb, total, left, right + 1);
        }
    }

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        for (String res : result) {
            System.out.println(res);
        }
    }
}
