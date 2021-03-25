package Offer;


import java.util.ArrayList;
import java.util.List;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 不是最优的方式
 * */
public class UglyNum_49 {
    public static int nthUglyNumber(int n) {
        List<Integer> uglyNum = new ArrayList<>();
        int p1=0, p2=0, p3=0;
        uglyNum.add(1);
        while (uglyNum.size()<n){
            int can1=uglyNum.get(p1)*2, can2 = uglyNum.get(p2)*3, can3=uglyNum.get(p3)*5;
            int curr = Math.min(can1, can2);
            if (curr>can3){
                curr = can3;
            }
            if (curr==can1) p1++;
            if (curr==can2) p2++;
            if (curr==can3) p3++;
            uglyNum.add(curr);
        }
        return uglyNum.get(uglyNum.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
