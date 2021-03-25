package topics.dp;

public class knapsack {
    /**
     * 简单背包问题：
     * 背包容量9，5个物品，重量分别为{2,2,4,6,3}，物品不可分割，求能装入的物品重量最大值
     * R(i,j)表示从0-i的最优解，i表示第i个物品，j表示当前剩余容量
     * w[i]>j,R(i,j)=R(i-1, j);
     * w[i]<=j, R(i,j)=MAX{R(i-1, j), R(i-1, j-w[i])+w[i]}
     * */
    public static int simpleKnapsack(int capacity, int [] weights){
        // 每一行表示当前层存在的状态
        int [][]status = new int[weights.length][capacity+1];
        for (int i = 0, len = weights.length; i < len; i++) {
            int w = weights[i];
            // 容量不足的情
            for (int j = 0; j <w ; j++) {
                status[i][j]=i-1>=0?status[i-1][j]:0;
            }
            for (int j = w; j <=capacity ; j++) {
                int giveUp = i-1>=0?status[i-1][j]:0;
                int keep = i-1>=0?status[i-1][j-w]+w:w;
                status[i][j]=Math.max(giveUp, keep);
            }
        }
        return status[weights.length-1][capacity];
    }

    public static void simpleKnapsackStart(){
        int capacity = 9;
        int [] weights = {2,2,4,6,3};
        System.out.println(simpleKnapsack(capacity, weights));
    }

    /**
     * 0-1背包
     * 背包容量9，5个物品，重量分别为{2,2,4,6,3}，价值分别{3,4,8,9,6}，物品不可分割，求能装入的物品重量最大价值
     * */

    public static void main(String[] args) {
        simpleKnapsackStart();
    }
}