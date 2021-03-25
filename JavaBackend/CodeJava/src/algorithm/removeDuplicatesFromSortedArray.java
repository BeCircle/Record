package algorithm;

import java.lang.reflect.Array;

public class removeDuplicatesFromSortedArray {
    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     *
     * S1. 扫描，遇到和前一个相同的删除，并移动后面的，时间复杂度为O(n^2).
     * S2. S1 的问题是后面的元素多次复制，因此通过从左到右扫描，记录不重复的位置 validIndex，
     * 和不重复的最大数字 LastNum，当前数字和不重复 LastNum 不相等时，复制到 validIndex+1 的位置，并移动 validIndex；
     * 最后输出 validIndex。
     * 这就是快慢指针的思路。
     * */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len<1) return 0;
        int validIndex=0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[validIndex]){
                // 不重复
                if (validIndex < i -1){
                    // 判断内部复制可以避免数字不重复时发生的复制
                    nums[validIndex+1]=nums[i];
                }else {
                    System.out.println(validIndex+1 +"_"+i);
                }
                validIndex ++;
            }
        }
        return validIndex+1;
    }

    public static void main(String[] args) {
        int [] input = {1,2,3};
        int resLen = removeDuplicates(input);
        for (int i = 0; i < resLen; i++) {
            System.out.println(input[i]);
        }
    }
}
