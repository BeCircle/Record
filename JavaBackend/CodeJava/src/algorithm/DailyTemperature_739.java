package algorithm;

import java.util.*;

public class DailyTemperature_739 {
    /**
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     * 链接：https://leetcode-cn.com/problems/daily-temperatures
     * 利用Stack存储未找到更大温度时间的温度
     * */

    public static int[] dailyTemperatures(int[] T) {
        int [] res = new int[T.length];
        // 只需要存储位置
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0, len = T.length; i<len;i++){
            int t = T[i];
            // 先判断stack中是否有小于当前温度的
            while (stack.size()>0 && T[stack.peekFirst()]<t) {
                Integer oldT= stack.pop();
                res[oldT] = i-oldT;
            }
            // 没有则将当前温度及其位置入栈
            stack.push(i);
        }
        // 未出栈的设置为0
        while (stack.size()>0) {
            Integer oldT= stack.pop();
            res[oldT] = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        int [] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int [] res = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res));
    }
}
