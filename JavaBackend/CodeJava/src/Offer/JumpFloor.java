package Offer;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/1
 */
public class JumpFloor {
    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * T(n) = T(n-1)+T(n-2)
     * T(1) = 1
     * T(2) = 2
     */
    public static int JumpFloor(int target) {
        int[] old = {1, 2};
        if (target > 0 && target < 3) {
            return old[target - 1];
        } else if (target >= 3) {
            int n = 3;
            while (n < target) {
                int T = old[0] + old[1];
                old[0] = old[1];
                old[1] = T;
                n++;
            }
            return old[0] + old[1];
        } else {
            return 0;
        }
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * T(n) = T(n-1)+T(n-2)+...+T(n-n+1)T(n-n)
     * T(n-1) = T(n-2)+...+T(n-n+1)T(n-n)
     * T(n) = T(n-1)+T(n-1)
     * T(1) = 1;
     * 也可理解为：n个台阶，最后一个必须存在，剩余n-1个可以存在或不存在，pow(2,n-1)
     * */
    public int JumpFloorII(int target) {
        if (target > 0) {
            return (int) Math.pow(2, target-1);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
