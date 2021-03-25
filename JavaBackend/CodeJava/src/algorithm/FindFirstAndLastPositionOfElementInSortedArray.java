package algorithm;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/14
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     */

    public static int[] searchRange(int[] nums, int target) {
        /**
         * 时间复杂度必须是 O(log n)肯定和二分查找有关
         * S1. 两次二分查找，分别查找左边界和右边界
         * S2. 一次二分查找，容易出边界问题
         * */
        int[] res = {-1, -1};
        int len = nums.length;
        if (len < 1) return res;
        // 找左边界
        int left = 0, right = len - 1;
        // 双闭区间,只有1个元素时left=right
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                // target 在 mid右边
                left = mid + 1;
            } else if (nums[mid] > target) {
                // target在mid左边
                right = mid - 1;
            } else {
                // nums[mid]==target，缩小右边界，不减一会死循环
                right = mid-1;
            }
        }
        if (left < len && nums[left] == target) {
            res[0] = left;
        }
        // 找右边界
        left = 0;
        right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                // target 在 mid右边
                left = mid + 1;
            } else if (nums[mid] > target) {
                // target在mid左边
                right = mid - 1;
            } else {
                // nums[mid]==target，缩小左边界
                left = mid+1;
            }
        }
        if (right >= 0 && nums[right] == target) {
            // 前面缩小左边界，这里使用右边界
            res[1] = right;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2};
        int target = 2;
        int[] res = searchRange(nums, target);
        if (res.length == 2) {
            System.out.println(res[0] + "," + res[1]);
        }
    }
}
