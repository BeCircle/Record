package algorithm;

import java.util.LinkedList;

public class NumOfIland {
    public static int numIslands(char[][] grid) {
        int ilandNum = 0;

        // 利用HashMap的key当作set，value存储岛屿编号
        int row = grid.length;
        if (row < 1 || grid[0].length < 1) {
            return ilandNum;
        }
        int column = grid[0].length;
        // 不能一行一行或者一列一列处理
        LinkedList<Integer> queX = new LinkedList<>();
        LinkedList<Integer> queY = new LinkedList<>();
        int[][] ext = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    ilandNum++;
                    grid[i][j] = '2';
                    // 发现一个病例，周围感染
                    queX.offer(i);
                    queY.offer(j);
                    while (queX.size() > 0) {
                        Integer X = queX.poll(), Y = queY.poll();
                        for (int e = 0; e < 4; e++) {
                            int NX = X + ext[e][0], NY = Y + ext[e][1];
                            if (NX >= 0 && NX < row && NY >= 0 && NY < column && grid[NX][NY] == '1') {
                                grid[NX][NY] = '2';
                                queX.offer(NX);
                                queY.offer(NY);
                            }
                        }
                    }
                }
            }
        }
        return ilandNum;
    }

    public static void main(String[] args) {
        char[][] map = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        char[][] map2 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        char[][] map3 = {};

        System.out.println(numIslands(map2));
    }
}
