package algorithm;

import java.util.*;

public class Permutations2 {
    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 示例:
     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     * 链接：https://leetcode-cn.com/problems/permutations-ii
     * S1. 在permutations的基础上：同一父节点下存在相同子节点时跳过这种可能。用Set实现；
     * S2. 将数字排序，然后进行回溯，同一层中，如果和前面的节点相同则表示重复。时间复杂度稍高，但是不适用hashset，空间复杂度低。
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length < 1) {
            return results;
        }
        results.add(new ArrayList<>());

        if (nums.length < 2) {
            results.get(results.size() - 1).add(nums[0]);
            return results;
        }

        // 用于记录是否使用, 0 表示没有使用，其余数字表示使用
        int[] flags = new int[nums.length];
        Arrays.fill(flags, 0);

        traceBack(results, nums, flags);

        results.remove(results.size() - 1);
        return results;
    }

    public static void traceBack(List<List<Integer>> results, int[] nums, int[] flags) {

        // 记录同父节点下已经被使用的节点
        Set<Integer> layerUsed = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (flags[i] == 0 && !layerUsed.contains(nums[i])) {
                // 未使用的数字
                layerUsed.add(nums[i]);
                // 占用
                flags[i] = 1;
                List<Integer> store = results.get(results.size() - 1);
                store.add(nums[i]);
                if (store.size() == nums.length) {
                    // 完成一种可能
                    List<Integer> newList = new ArrayList<>(store);
                    results.add(newList);
                }
                traceBack(results, nums, flags);

                List<Integer> store2 = results.get(results.size() - 1);
                store2.remove(store2.size() - 1);
                // 取消占用
                flags[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 1, 2};
        List<List<Integer>> result = permuteUnique(input);
        for (List<Integer> line : result) {
            for (Integer item : line) {
                System.out.print(item + "\t");
            }
            System.out.print("\n");
        }
    }
}
