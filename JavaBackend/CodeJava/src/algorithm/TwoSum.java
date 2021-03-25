package algorithm;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 链接：https://leetcode-cn.com/problems/two-sum
     * */
    public static int[] twoSum(int[] nums, int target) {
        /**
         * S1. 排序，从左从右双指针 O(nlogn)+O(n)
         * S2. hash保存差和数字的键值对 O(n)
         * */
        int[] res = {-1, -1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                res[1] = i;
                res[0] = map.get(nums[i]);
                break;
            }
            if (!map.containsKey(target-nums[i])){
                // 不在map中,建立差和索引的映射
                map.put(target-nums[i], i);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int []nums = {2, 7, 11, 15};
        int [] res = twoSum(nums, 9);
        for (int re : res) {
            System.out.println(re);
        }
    }
}
