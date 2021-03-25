package Offer;

public class reOrderArray {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * */
    public void reOrderArray(int [] array){
        // 不使用额外空间只能相邻比较，交换
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2==1){
                int p = i-1, pO = i;
                while (p>=0&&array[p]%2==0){
                    // 向左和偶数交换
                    int temp = array[pO];
                    array[pO] = array[p];
                    array[p] = temp;
                    pO = p;
                    p = pO-1;
                }
            }
        }
    }
    public void reOrderArray2(int [] array){
        int [] arrOld = new int[array.length];
        // 使用额外空间
        int p1 = 0, p2=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2==1){
                if (p1!=i){
                    // 需要移动
                    array[p1] = array[i];
                }
                p1++;
            }else{ //偶数
                arrOld[p2++] = array[i];
            }
        }
        for (int i = p1, j=0; i <array.length&&j<p2 ; i++,j++) {
            array[i] = arrOld[j];
        }
    }
}
