package Company;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

public class AliBaBa {
    /**
     * @param n:人数
     * @return 组合情况数
     * 从n个人中选m个，再从m中选一个队长
     * 0%
     */


    public static int prob1(int n) {
        if (n < 1 || n > 1000000000) {
            return 0;
        }
        // 用于缓存阶乘
        HashMap<Integer, Long> cacheMulti = new HashMap<>();
        // 常用到n的阶乘
        long nMulti = multi(n, cacheMulti);
        long res = 0;
        for (int m = 1; m <= n; m++) {
            // 组合数
            long multi;
            if (m == n) {
                res += m;
            } else if (m == 1) {
                res += n;
            } else {
                // m-1省略一次乘法
                long a = multi(m-1, cacheMulti);
                long b = multi(n - m, cacheMulti);
                multi = nMulti / (a * b);
                res +=multi;
            }
        }
        return (int) (res % 1000000007);
    }

    // 计算num的阶乘
    public static long multi(int num, HashMap<Integer, Long> cacheMulti) {
        if (cacheMulti.containsKey(num)) {
            return cacheMulti.get(num);
        } else {
            long res = num;
            for (int i = num - 1; i > 1; i--) {
                res %=1000000007;
                res *= (i%1000000007);
            }
            cacheMulti.put(num, res);
            return res;
        }
    }

    @Test
    public void testProb1() {
        int res = prob1(6);
        Assert.assertEquals(res, 192);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(prob1(n));
    }

}

