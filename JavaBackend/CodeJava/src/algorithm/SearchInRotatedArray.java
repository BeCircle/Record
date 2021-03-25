package algorithm;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/17
 */
public class SearchInRotatedArray {
    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 示例 1:
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     */
    public static int search(int[] nums, int target) {
        /**
         * O(log n)的复杂度+有序的数组->二分搜索
         * M1. 想办法先恢复旋转；
         * S1. mid >right 表示旋转点在右边，mid<right 旋转点在左边； O(log n)
         * S2. 找到旋转点后以旋转点为第一个mid进行二分查找；O(log n)
         * */
        int len = nums.length;
        int res = -1;
        int rotate = -1;
        if (len > 0) {
            // 利用二分思路查找旋转点位置
            if (len > 1) {
                int left = 0, right = len - 1;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (nums[mid] > nums[right]) {
                        left = mid + 1;
                    } else if (nums[mid] < nums[right]) {
                        right = mid;
                    } else if (mid - 1 >=0 && nums[mid - 1] > nums[mid]) {
                        rotate = mid - 1;
                        break;
                    }else {
                        left = mid + 1;
                    }
                }
            }
            // 分两段二分查找
            int l, r;
            if (rotate >= 0) {
                if (target <= nums[len - 1]) {
                    // 在右半段
                    l = rotate + 1;
                    r = len - 1;
                } else if (target >= nums[0]) {
                    l = 0;
                    r = rotate;
                } else {
                    return -1;
                }
            } else {
                l = 0;
                r = len - 1;
            }
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    res = mid;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 1,3};
        System.out.println(search(nums1, 5));
    }
}
