package algorithm;

import java.util.Arrays;

public class ThreeSumClosest {
    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     */
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            int base = nums[i];
            if (i > 0 && nums[i - 1] == base) {
                continue;
            }
            int pL = i + 1, pR = len - 1;
            while (pL < pR) {
                int tempSum = base + nums[pL] + nums[pR];
                if (Math.abs(sum - target) > Math.abs(tempSum - target)) {
                    sum = tempSum;
                }
                if (tempSum > target) {// 和大于目标
                    pR--;
                    while (pL < pR - 1 && nums[pR] == nums[pL - 1]) {// 去重优化
                        pR--;
                    }
                } else if (tempSum < target) {// 和小于大于目标
                    pL++;
                    while (pL + 1 < pR && nums[pL] == nums[pL + 1]) { // 去重优化
                        pL++;
                    }
                } else {
                    return target;
                }

            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
