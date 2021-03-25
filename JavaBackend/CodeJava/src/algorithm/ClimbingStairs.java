package algorithm;

public class ClimbingStairs {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * 示例 1：
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * <p>
     * 示例 2：
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 动态规划：最后一次可能是2级也可能是1级，f(n) = f(n-1)+f(n-2)
     * 从上往下分析，从下往上计算；
     */
    public static int climbStairs(int n) {
        int temp = 0;
        if (1 <= n && n <= 2) {
            temp = n;
        } else if (n > 2) {
            int n1 = 1, n2 = 2;
            for (int i = 3; i <= n; i++) {
                temp = n1 + n2;
                n1 = n2;
                n2 = temp;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }
}
