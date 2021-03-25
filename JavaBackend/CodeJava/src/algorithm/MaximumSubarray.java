package algorithm;

public class MaximumSubarray {
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 双指针，快指针从左到右扫描
     * 慢指针，sum<0时往右移动
     * 其实和动态规划 f(x) = max{f(x-1)+ num[x], num[x]}异曲同工；
     * 还有分治方法，但是时间复杂度不是最优。
     * */
    public static int maxSubArray(int[] nums) {
        if (nums == null ||nums.length<1)
            return 0;
        // 快指针从左到右移动
        int curSum=nums[0];
        int maxSum=curSum;
        for (int i = 1; i < nums.length; i++) {
            if (curSum<0){
                // curSum <0, 丢弃之前的和
                curSum = nums[i];
            }else {
                curSum += nums[i];
            }
            if (curSum >maxSum) maxSum = curSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int [] input = {1};
        int res = maxSubArray(input);
        System.out.println(res);
    }
}
