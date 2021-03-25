package Offer;

import java.util.HashMap;
import java.util.Map;

public class CountOfNumInArr_56 {
    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * 1 <= nums.length <= 10000
     * 1 <= nums[i] < 2^31
     * */

    /**
     *   hash 存储数字和数量，达到3个则移除。
     *   时间复杂度O(n)
     *   空间复杂度最坏O(n/3)
     * */
    public static int singleNumberHash(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n:nums){
            if (count.containsKey(n)){
                int already = count.get(n);
                if (already==2){
                    count.remove(n);
                }else{
                    count.put(n, count.get(n)+1);
                }
            }else {
                count.put(n, 1);
            }
        }
        for (int i:count.keySet()){
            return i;
        }
        return 0;
    }
    /**
     * 理论上时间复杂度高于前者，可能由于hash的维护，实际运行时间更优
     * */
    public static int singleNumber(int[] nums) {
        // 记录每一位
        int[] bits = new int[32];
        for (int n:nums){
            int num = n;
            for (int i = 0; i < 32; i++) {
                bits[i]+=num&1;
                num>>>=1;
            }
        }
        // 每一位%3之后的合起来表示的数字就是结果。
        for (int i=0; i<32; i++){
            bits[i]%=3;
        }
        int res=0;
        for (int i=31; i>=0; i--){
            res=res*2+bits[i];
        }
        return res;
    }

    /**
     * 数组中除了两个数字只出现1次之外，其余的都出现两次。要求空间复杂度O(1)时间复杂度O(n)
     * 根据上一题会想到位运算，全部数字异或可以得到两个只出现一次的数字异或的结果；
     * 但这样无法分开两个数字。
     * 如果能将两个特殊的分组，其余的数字成对的不分开，利用异或就可分别求出两个数字。
     * */
    public int[] singleNumbers(int[] nums) {
        int total=0;
        for (int n:nums){
            total^=n;
        }
        // total的任一为1的位可将两个数字分开；同时不会分开成对的数
        int mask=1;
        int divide=0;
        for (int i = 0; i < 32; i++) {
            divide = total&mask;
            if (divide>0){
                break;
            }
            mask<<=1;
        }
        int res1 = 0, res2 = 0;
        for (int n:nums) {
            if ((n&divide)>0){
                res1^=n;
            }else{
                res2^=n;
            }
        }
        return new int[]{res1, res2};
    }

    public static void main(String[] args) {
        int[]case1={9,1,7,9,7,9,7};
        int[] case2= {3,4,3,3};
        System.out.println(singleNumber(case2));
    }
}
