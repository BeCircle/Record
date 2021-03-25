package algorithm;

import java.util.*;

import static algorithm.Utils.print2DList;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/19
 */
public class CombinationSum {
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * 回溯-数字可以重复使用，子树的节点包括父节点；
         * 候选排序组成字符串加入hashSet用于候选答案的去重；
         * */
        List<List<Integer>> res = new ArrayList<>();
        Set<String> resFilter = new HashSet<>();
        res.add(new ArrayList<>());
        traceBack(res, resFilter, candidates, target);
        // 移除末尾空的list
        res.remove(res.size()-1);
        return res;
    }

    public static void traceBack(List<List<Integer>> res,Set<String> resFilter, int[] candidates, int target){
        // 根节点有两种情况：1. 刚好凑齐target；2. item>target 不会继续向下搜索。
        for (int i = 0; i< candidates.length; i++) {
            int item = candidates[i];
            if (item==target){
                // 一种可能
                List<Integer> lastLine = res.get(res.size()-1);
                // 复制用于新的可能
                List<Integer> newLine = new ArrayList<>(lastLine);
                lastLine.add(item);
                // 判断是否重复
                Integer [] lastLineArray = new Integer[lastLine.size()];
                lastLine.toArray(lastLineArray);
                // 这里排序不能修改原始lastLine，否则remove末尾元素导致target错误。
                Arrays.sort(lastLineArray);
                String candidate = Arrays.toString(lastLineArray);
                if (!resFilter.contains(candidate)){
                    resFilter.add(candidate);
                    res.add(newLine);
                }else {
                    lastLine.remove(lastLine.size()-1);
                }
            }
            else if (item<target){
                List<Integer> lastLine = res.get(res.size()-1);
                lastLine.add(item);
                traceBack(res,resFilter, candidates, target - item);
                // 移除最后一个元素，回溯
                lastLine = res.get(res.size()-1);
                lastLine.remove(lastLine.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res;

        int []input1 = {2,3,6,7};
        res = combinationSum(input1, 7);
        print2DList(res);

        int [] input2 = {2,3,5};
        res = combinationSum(input2, 8);
        print2DList(res);
    }
}
