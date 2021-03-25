package algorithm;

import javax.lang.model.type.UnknownTypeException;
import java.io.IOException;
import java.util.MissingResourceException;

public class CountAndSay {
    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     * <p>
     * 1.     1         // 输入
     * 2.     11        // 一个一
     * 3.     21        // 两个一
     * 4.     1211      // 一个二一个一
     * 5.     111221    //一个一一个二两个一
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     * 注意：整数顺序将表示为一个字符串。
     * 示例 1:
     * 输入: 1 输出: "1"
     * 示例 2:
     * 输入: 4 输出: "1211"
     * 链接：https://leetcode-cn.com/problems/count-and-say
     */
    public static String countAndSay(int n) {
        String countStr = "1";
        for (int generation = 2; generation <= n; generation++) {
            StringBuilder newCountStr = new StringBuilder();
            // 当前数字
            char currNum = countStr.charAt(0);
            // 当前数字的个数
            int count=1;
            for (int i = 0; i < countStr.length(); i++) {
                if (i > 0)  {
                    if (countStr.charAt(i) == currNum) {
                        // 计数
                        count++;
                    } else {
                        // 遇到不同数字
                        newCountStr.append(count).append(currNum);
                        count = 1;
                        currNum = countStr.charAt(i);
                    }
                }
            }
            newCountStr.append(count).append(currNum);
            countStr = newCountStr.toString();
            System.out.println(generation + ":" + countStr);
        }
        return countStr;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
}
