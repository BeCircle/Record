package algorithm;

import static algorithm.Utils.printArray;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/18
 */
public class NextPermutation {
    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 链接：https://leetcode-cn.com/problems/next-permutation
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int hit = 0;
        for (int i = len - 1; hit <= 0 && i > 0; i--) {
            // S1. 从右往左找到第一个组 a[i-1] < a[i](a[i]之后都是降序);
            if (nums[i - 1] < nums[i]) {
                // S2. 从右往左找到第一个>a[i-1] 的数字a[j]；
                for (int j = len - 1; hit <= 0 && j >= i; j--) {
                    if (nums[j] > nums[i - 1]) {
                        hit = i;
                        //S3. a[j] 与a[i-1]交换;
                        int temp = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
        }
        // S4. 再将a[i-1]之后的数字倒序/倒置（这样形成的才是刚好大于现在的排列）。
        // 没有更大的组合时hit等于0，也是从hit开始倒置
        int left = hit, right = len - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3};
        nextPermutation(input1);
        printArray(input1);

        int[] input2 = {3, 2, 1};
        nextPermutation(input2);
        printArray(input2);

        int[] input3 = {1, 1, 5};
        nextPermutation(input3);
        printArray(input3);
    }
}
