package Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HuaweiII {
    /**
     * 判断括号字符串是否有效
     * 左右括号对应
     * m1:使用stack存储左括号，遇到右括号出栈对应左括号
     * 没有找到对应最括号返回false
     * 最终stack不为空返回false。
     * */
    public static boolean validStr(Map<Character, Character> marks, String inStr) {
        if (inStr.length()==0){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c: inStr.toCharArray()){
            if (marks.containsValue(c)){
                stack.push(c);
            }else if (marks.containsKey(c)){
                // 遇到右括号，取出栈顶，判断是否和左括号相等
                if (stack.size()>0&&stack.peek()==marks.get(c)){
                    stack.pop();
                }else{
                    return false;
                }
            }else{
                // 存在其他字符
                return false;
            }
        }
        return stack.size() <= 0;
    }

    public static void main(String[] args) {
        // 存储括号对
        Map<Character, Character> marks = new HashMap<>();
        marks.put(')','(');
        marks.put('}','{');
        marks.put(']','[');
        // 测试用例
        String[] testCases = {"()", "()[]{}", "(]", "([)]", "{[]}", "","dg)(","])[("};
        for (String caseItem: testCases){
            System.out.println(validStr(marks, caseItem));
        }
    }
}
