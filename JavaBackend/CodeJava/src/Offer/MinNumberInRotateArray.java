package Offer;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/2
 */
public class MinNumberInRotateArray {
    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
     */
    public int minNumberInRotateArray(int[] array) {
        /**
         * 双指针，如果前一个小于后一个，则前一个是最小
         * 时间复杂度为O(n)
         **/
        int len = array.length;
        if (len < 1) {
            return 0;
        } else if (len == 1) {
            return array[0];
        } else {
            int pSlow = 0, pQuick = 1;
            while (pQuick < len && array[pQuick] >= array[pSlow]) {
                pQuick++;
                pSlow++;
            }
            if (pQuick < len) {
                return array[pQuick];
            }
            return 0;
        }
    }

    public static int minNumberInRotateArray2(int[] array) {
        /**
         * 数组是两部分有序的数组组成；
         * */
        int len = array.length;
        if (len < 1) {
            return 0;
        } else {
            int l = 0, r = len - 1;
            while (l < r) {
                // 子数组是非递减的数组，10111，下面和array[l]比较时的补救。
//                if (array[l] < array[r])
//                    return array[l];
                int mid = (l + r) >> 1;
                // 选择l作为推进时，如果和l比较，array[mid] > array[l]
                // 在[1,0,1,1,1]时，l移动到0，下一轮a[l]=0<a[m]=1,l再次左移，错失答案。
                if (array[mid] > array[r]) {
                    //分界在mid右边
                    l = mid + 1;
                } else if (array[mid] < array[r]) {
                    // 分界在mid左边
                    // mid-1，则可能会错过最小值，因为找的就是最小值
                    r = mid;
                } else {
                    // 相等时不确定 [1,0,1,1,1] 或者[1,1,1,0,1]
                    l++;
                }
            }
            return array[r];
        }
    }

    public static void main(String[] args) {
        int [] test = {3,4,5,1,2};
        minNumberInRotateArray2(test);
    }
}
