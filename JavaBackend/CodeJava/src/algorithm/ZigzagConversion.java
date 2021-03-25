package algorithm;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * 请你实现这个将字符串进行指定行数变换的函数：
     * string convert(string s, int numRows);
     * 示例 1:
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * */
    public static String convert(String s, int numRows) {
        if (numRows<=1 || s.length()<numRows){
            // 异常行数
            return s;
        }
        List<StringBuilder> zigZag = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            zigZag.add(new StringBuilder());
        }

        boolean increase = true;
        int lineNum = -1;
        for (int i = 0; i < s.length(); i++) {
            if (increase){
                lineNum ++;
            }else {
                lineNum --;
            }
            zigZag.get(lineNum).append(s.charAt(i));
            if ((increase &&lineNum == numRows-1) ||(!increase&& lineNum == 0)){
                increase = !increase;
            }
        }
        StringBuilder sb = zigZag.get(0);
        for (int i = 1; i < zigZag.size(); i++) {
            sb.append(zigZag.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "LEETCODEISHIRING";
        System.out.println(convert(input, 1));
    }
}
