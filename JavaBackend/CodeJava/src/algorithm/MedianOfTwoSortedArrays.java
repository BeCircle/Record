package algorithm;

public class MedianOfTwoSortedArrays {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * O(n)的思路是从左到右同时找两个对列，找到第(m+n)/2
     *
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // TODO 太难了
        return -1;
    }

    public static void main(String[] args) {
        int [] nums1 = {1, 2};
        int [] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
