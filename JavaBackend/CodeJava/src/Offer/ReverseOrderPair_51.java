package Offer;

import java.util.Arrays;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 考虑是否和冒泡排序的swap次数相等？
 *
 * */
public class ReverseOrderPair_51 {
    // 暴力，n*n，超出时间限制
    public static int reversePairs1(int[] nums) {
        if (nums==null||nums.length<2)
            return 0;
        int sum = 0;
        for (int i = 0, len=nums.length; i < len-1; i++) {
            int thisTurn=0;
            int curr = nums[i];
            for (int j = i+1; j < len; j++) {
                if(nums[j]<curr){
                    thisTurn ++;
                }
            }
            sum+=thisTurn;
        }
        return sum;
    }

    public static int reversePairs(int[] nums) {
        if (nums==null||nums.length<2)
            return 0;
        int len = nums.length;
        return mergeSort(nums, 0, len-1);
    }

    public static int mergeSort(int[] nums, int l, int r) {
        if (l>=r) return 0;
        // 并
        int mid = (l+r)/2;
        int res=mergeSort(nums, l, mid)+mergeSort(nums, mid+1, r);
        // 归
        int[] a1 = Arrays.copyOfRange(nums, l, mid+1);
        int[] a2 = Arrays.copyOfRange(nums, mid+1, r+1);
        int k=l, p1=0,p2=0;
        while (p1<a1.length&&p2<a2.length){
            // 这里等号将影响case中存在相等数字时是否判定
            if(a1[p1]<=a2[p2]){
                nums[k++] = a1[p1++];
            }else{
                // 右边数组选中一个数时，左边数组剩余个数就是逆序对贡献数字
                res+=a1.length-p1;
                nums[k++] = a2[p2++];
            }
        }
        // 剩余的地方
        if (p1<a1.length){
            while (p1<a1.length){
                nums[k++] = a1[p1++];
            }
        }else if(p2<a2.length){
            while (p2<a2.length){
                nums[k++] = a2[p2++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] case1={7,5,6,4,2,3,1};
        int[] case2= {1,3,2,3,1};
        System.out.println(reversePairs(case2));
    }
}
