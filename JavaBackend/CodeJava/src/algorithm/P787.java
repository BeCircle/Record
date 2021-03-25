package algorithm;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class P787 {
    /**
     * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
     *
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * 输出: 500
     *
     * n 范围是 [1, 100]，城市标签从 0 到 n - 1.
     * 航班数量范围是 [0, n * (n - 1) / 2].
     * 每个航班的格式 (src, dst, price).
     * 每个航班的价格范围是 [1, 10000].
     * k 范围是 [0, n - 1].
     * 航班没有重复，且不存在环路
     * dp[k][dst] = min{dp[k][dst], dp[k-1][dst]+price}
     * */
    public static  int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        return 0;
    }

    public static void main(String[] args) {
        int n = 3;
        int [][]edges = {{0,1,100},{1,2,100},{0,2,500}};
        int  src = 0, dst = 2, k = 1;
        System.out.println(findCheapestPrice(n, edges, src, dst, k));
    }
}

