package Offer;

public class RectCover {
    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * 比如n=3时，2*3的矩形块有3种覆盖方法
     * */
    public static int RectCover(int target) {
        /**
         * T(n) = T(n-1)+T(n-2)
         * 1->1;2->2
         * */
        int[] cache = {1,2};
        if (target<1){
            return 0;
        }else if (target<3){
            return cache[target-1];
        }
        while (target-->=3){
            int temp = cache[0];
            cache[0] = cache[1];
            cache[1] = cache[0]+temp;
        }
        return cache[1];
    }

    public static void main(String[] args) {
        System.out.println(RectCover(3));
    }
}
