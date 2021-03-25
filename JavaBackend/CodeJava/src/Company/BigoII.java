package Company;

import java.util.LinkedList;

public class BigoII {
    // String to int

    /**
     * -1
     * 12334
     * 1. 正负数字
     * sdsf1213afa
     * 2. 非法字符：从第一个数字开始，之后再遇到非法字符就结束
     * 134v dsf
     * 3. 进位，
     *
     * 死锁的条件：
     * 1. 互斥
     * 2. 持有并获取
     * 3. 循环依赖资源
     * 4. 不释放
     *
     * 选择排序
     * 冒泡
     * 快排
     * 归并排序
     * 堆排序
     * 桶排序
     */
    private static int StrToInt(String strIn) {
        // TODO 异常输入
        // 记录位数、正负号, 存储数字（暂不考虑进位）
        int bitNum = 0;
        boolean positive = true;
        LinkedList<Integer> numbers = new LinkedList<>();
        int state = 0;
        for (char c : strIn.toCharArray()) {
            if (state == 0 && c != '-' && (c < '0' || c > '9')) {
                continue;
            } else if (state == 0 && c == '-') {
                positive = false;
                state = 1;
            } else if (c >= '0' && c <= '9') {
                state = 1;
                numbers.add(c - '0');
            } else {
                // 提前终止
                break;
            }
        }
        //
        int res = 0;
        for (Integer bit : numbers) {
            res = res * 10 + bit;
        }
        if (!positive) {
            res = -res;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] cases = {
                "121",
                "-123",
                "0",
                "sdsf1213afa",
                "134v dsf"
        };
        for (String caseItem: cases){
            System.out.println(StrToInt(caseItem));
        }
    }
}
