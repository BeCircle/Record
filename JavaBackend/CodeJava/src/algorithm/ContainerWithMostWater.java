package algorithm;

import javax.sound.midi.Soundbank;

public class ContainerWithMostWater {
    /**
     *给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例:
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * */
    public static int maxArea(int[] height) {
        /**
         * 1. 横坐标之差*两个数中最小的那个为容量
         * C = (i_2 - i_1)*min(a_1, a_2)
         * S1. 暴力，所有组合
         * */
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j =i+1; j <height.length ; j++) {
                int area= (j-i)*Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
                System.out.print(i+"-"+j+":"+area+"\t");
            }
            System.out.print("\n");
        }
        return maxArea;
    }

    public static int maxArea2(int[] height) {
        /**
         * 1. 横坐标之差*两个数中最小的那个为容量
         * C = (i_2 - i_1)*min(a_1, a_2)
         * S2. 枚举优化
         * 两个变量影响结果时，控制住一个变量，利用这个变量进行剪枝；
         * 这里控制宽度随着i移动变小，移动i高度得不到提高，这些组合可跳过。
         * */
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j =height.length-1; j >i; j--) {
                int area= (j-i)*Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
                System.out.print(i+"-"+j+":"+area+"\t");
                if (height[i] <= height[j]){
                    // 当前宽度最大，如果h[i]<h[j],j变小，h = min(h[i], h[j])，h永远不会大于h[i];
                    // 由于宽度也变小，j变小不会得到大于当前area的值；
                    break;
                }
            }
            System.out.print("\n");
        }
        return maxArea;
    }

    public static int maxArea3(int[] height) {
        /**
         * 1. 横坐标之差*两个数中最小的那个为容量
         * C = (i_2 - i_1)*min(a_1, a_2)
         * S3. i可以优化，j也可以优化
         * */
        int i = 0, j = height.length -1;
        int maxArea = 0;
        while (i <j){
            int area= (j-i)*Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, area);
            System.out.print(i+"-"+j+":"+area+"\t");
            if (height[i] <= height[j]){
                i++;
            }else {
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] input = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(input));
        System.out.println(maxArea2(input));
        System.out.println(maxArea3(input));
    }
}
