package Offer;

public class QuickSort {
    public static void sort(int [] array){
        if (array==null || array.length<2){
            return;
        }
        sort(array, 0, array.length-1);
    }
    private static void sort(int [] array, int left, int right){
        if (array==null || left>=right){
            return;
        }
        int index = partition(array, left, right);
        sort(array, left, index-1);
        sort(array, index+1, right);
    }
    private static int partition(int[] arr, int l, int r){
        // l和r都取
        // 第一个坑
        int base = arr[l];
        int lp = l, rp = r;
        while (lp<rp) {
            // 从右往左找第一个小于等于base的数
            while (rp>lp&&arr[rp]>base)
                rp --;
            if (lp<rp){ // 拆东墙补西墙
                arr[lp] = arr[rp];
                lp ++;
            }
            while (lp<rp&&arr[lp]<=base)
                lp++;
            if (lp<rp){// 拆西墙补东墙
                arr[rp] = arr[lp];
                rp --;
            }
        }
        // lp==rp, 补最后一个坑
        arr[lp] = base;
        return lp;
    }

    public static void main(String[] args) {
        int [] inArray = {49, 38, 65, 97, 76, 13, 27, 49 };
        sort(inArray);
        System.out.println("");
    }
}
