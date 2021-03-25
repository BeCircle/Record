package algorithm;

public class SearchInsertPosition {
    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * 示例 1:
     * 输入: [1,3,5,6], 5 输出: 2
     * 示例 2:
     * 输入: [1,3,5,6], 2 输出: 1
     * 示例 3:
     * 输入: [1,3,5,6], 7  输出: 4
     * 示例 4:
     * 输入: [1,3,5,6], 0 输出: 0
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * S1. 二分法查找，改进判断过程 O(log n)
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        } else if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        // 实现二分查找
        int left = 0, right = nums.length;
        int mid;
        while (left < right - 1) {
            mid = (right + left) / 2;
            if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid;
            } else {
                // 找到数字
                return mid;
            }
        }
        // 没有找到相同数字
        if (target <= nums[left]) {
            return left;
        }
        return right;
    }

    public static int searchInsert2(int[] nums, int target){
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 这里返回left，因为left>right
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        int i = 1;
        System.out.println(searchInsert(nums, i));
    }
}
