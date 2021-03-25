package algorithm;

import java.util.*;

public class ValidParentheses {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     *
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * */
    public static boolean isValid(String s) {
        // 用栈保存，遇到左括号入栈，遇到有括号出对应的左括号。
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> parents = new HashMap<>();
        parents.put(')','(');
        parents.put(']','[');
        parents.put('}','{');
        for (int i = 0; i < s.length(); i++) {
            char charItem = s.charAt(i);
            if(parents.containsValue(charItem)){
                // 如果是左括号则入栈
                stack.addLast(charItem);
            }else if(parents.containsKey(charItem)){
                // 输入右括号
                if (stack.size()>0 && stack.getLast()==parents.get(charItem)){
                    // 出栈
                    stack.removeLast();
                }else {
                    return false;
                }
            }
        }
        return stack.size() <= 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String strIn = sc.nextLine();
            System.out.println(isValid(strIn));
        }
    }
}
