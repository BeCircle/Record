package Offer;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/13
 */
public class FindTargetIn2DArray {
    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * */
    public static boolean Find(int target, int [][] array){
        /**
         * S1. 利用从左到右，从上到下有序的特点O(n+m)???；
         * S2. 每一行进行二分查找O(nlogn)；
         * */
        boolean find = false;
        if (array.length>0) {
            // 行,列
            int row = array.length;
            int column = array[0].length;
            // 从左下角开始
            int r = row-1, c = 0;
            while (!find&&r>=0&&r<row&&c>=0&&c<column){
                int temp = array[r][c];
                if (temp==target){
                    // 找到目标数字
                    find = true;
                }else if (temp>target){
                    // 目标数字>temp，向上寻找更小的
                    r --;
                }else {
                    // 目标数字>temp，向右寻找更大的
                    c ++;
                }
            }
        }
        return find;
    }
}
