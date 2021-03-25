package algorithm;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * {
 *   {1,3,1},
 *   {1,5,1},
 *   {4,2,1}
 * }
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * */
public class MinimumPathSum_64 {
    // 存在重复计算
    public static int minPathSum(int[][] grid) {
        if (grid.length==0||grid[0].length==0){
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int [][] cache = new int[row][column];
        return traceBack(grid.length-1, grid[0].length-1, grid, cache);
    }
    private static int traceBack(int i, int j, int[][] grid, int[][] cache) {
        // 边界不能用0，因为值可能为0
        if (i<0||j<0){
            return -1;
        }
        if (cache[i][j]>0){
            return cache[i][j];
        }
        int res= grid[i][j];
        int l = traceBack(i-1, j,grid, cache);
        int up = traceBack(i, j-1,grid, cache);
        int min = Math.min(l, up);
        if (min>=0){
            res+=min;
        }else{
            int max = Math.max(l, up);
            if (max>=0){
                res += max;
            }
        }
        cache[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        int [][] case1 = {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println(minPathSum(case1));
    }
}
