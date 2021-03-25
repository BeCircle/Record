package Offer;

public class huaweiI {

    /**
     * 双指针实现两个有序数组合并为同一个有序数组；
     * arrBase中已有数据需要全部位于数组开始端；
     * */

    public static void merge(int [] arrBase, int arrBaseSize, int[] arr){
        // 异常输入处理
        if (arrBase==null||arrBase.length<1||arr==null||arr.length<1){
            // 输入数据为空
            return;
        }else if(arrBase.length<arrBaseSize||arrBase.length<arrBaseSize+arr.length){
            // 空间不足
            return;
        }

        int used = arrBaseSize + arr.length-1;
        int p1 = arrBaseSize-1, p2=arr.length-1;
        // 合并
        while (p1>=0&&p2>=0){
            if (arrBase[p1]>=arr[p2]){
                arrBase[used--] = arrBase[p1--];
            }else{
                arrBase[used--] = arr[p2--];
            }
        }

        // 处理合并后剩余的部分,arrBase剩余部分不需要复制
        while (p2>=0){
            arrBase[used--] = arr[p2--];
        }
    }
    public static void main(String[] args) {
        // case1
        int[] case1ArrBase = {1,2,3,0,0,0};
        int [] case1Arr = {2,5,6};
        merge(case1ArrBase, 3, case1Arr);
        for (int n: case1ArrBase){
            System.out.print(n+"\t");
        }
        System.out.println();

        // case2
        int[] case2ArrBase = {2,5,8,0,0,0};
        int [] case2Arr = {1,5,6};
        merge(case2ArrBase, 3, case2Arr);
        for (int n: case2ArrBase){
            System.out.print(n+"\t");
        }
        System.out.println();
    }
}
