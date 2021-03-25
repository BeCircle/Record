package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * 链接：https://leetcode-cn.com/problems/3sum
     * 1. 元素可以在不同元组中多次出现
     * 1. 同元组中不重复出现，但可能有相同对的元素。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        /**
         * S1. 先将数组排序，然后双指针相向移动移动。
         * */
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 2 && nums[i] + nums[i + 1] + nums[i + 2] <= 0; i++) {
            // 基数
            int base = nums[i];
            if ((i > 0 && base == nums[i - 1]) || base + nums[len - 1] + nums[len - 2] < 0) {
                // base和最大两个数之和<0直接跳过此base；
                continue;
            }
            // 双指针一个从左，一个从右
            // P1 向右，调大，P2向左，是调小
            int p1 = i + 1, p2 = len - 1;
            while (p1 < p2) {
                int sum = base + nums[p1] + nums[p2];
                if (sum < 0) {
                    p1++;
                } else if (sum > 0) {
                    p2--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    // 去重，前探去重
                    while (p1<p2&&nums[p1]==nums[p1+1]){
                        p1++;
                    }
                    while (p1<p2&&nums[p2]==nums[p2-1]){
                        p2--;
                    }
                    p2--;
                    p1++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(input);
        for (List<Integer> line : res) {
            for (Integer item : line) {
                System.out.print(item + "\t");
            }
            System.out.print("\n");
        }
    }
}
