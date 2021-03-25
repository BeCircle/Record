package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     * 链接：https://leetcode-cn.com/problems/permutations
     * S1. 选一个节点做根节点，其余节点中选次根节点
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length<1){
            return results;
        }
        results.add(new ArrayList<>());

        if (nums.length<2){
            results.get(results.size()-1).add(nums[0]);
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
        for (int i = 0; i < nums.length; i++) {
            if (flags[i] == 0) {
                // 未使用的数字
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
        int[] input = {1};
        List<List<Integer>> result = permute(input);
        for (List<Integer> line : result) {
            for (Integer item : line) {
                System.out.print(item + "\t");
            }
            System.out.print("\n");
        }
    }
}
