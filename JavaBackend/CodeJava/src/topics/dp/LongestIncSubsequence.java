package topics.dp;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * s[i] = max{s[j], a[j]<a[i]}+1
 */
public class LongestIncSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length<1){
            return 0;
        }
        int len = nums.length;
        // 记录以i结尾的LTS，后续依赖前面的
        int []states = new int[len];
        int allLis = 0;
        for (int i=0; i<len;i++){
            int num = nums[i];
            // 寻找左侧<num并且LIS最大的
            int lis = 0;
            for (int j = 0; j <i ; j++) {
                if (nums[j]<num&&states[j]>lis){
                    lis = states[j];
                }
            }
            states[i]=lis+1;
            if (lis+1>allLis){
                allLis = lis+1;
            }
        }
        return allLis;
    }

    public static void main(String[] args) {
        int[][] arrs = {
                {10, 9, 2, 5, 3, 7, 101, 18}
        };
        for (int[] arr : arrs) {
            System.out.println(lengthOfLIS(arr));
        }
    }
}
