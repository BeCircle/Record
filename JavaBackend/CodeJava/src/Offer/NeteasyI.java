package Offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class NeteasyI {
    // 数组拆分能组成的素数最多多少个
    public static Map<Integer, Integer> cache = new HashMap<>();
    public static Map<Integer, Integer> numCache = new HashMap<>();

    // 素数
    private static int suNum(int num){
        if (numCache.containsKey(num)){
            return numCache.get(num);
        }
        int res = 1;
        for (int i = 2; i < num/2; i++) {
            if (num%i==0){
                return 0;
            }
        }
        numCache.put(num, res);
        return res;
    }

    private static int SuNumOfNum(int num) {
        if (num ==1){
            return 0;
        }
        if (cache.containsKey(num)){
            return cache.get(num);
        }
        // 没有计算过
        int count = suNum(num);
        for (int i = 1; i <= num/2; i++) {
            int part1 = SuNumOfNum(i);
            int part2 = SuNumOfNum(num-i);
            count = Math.max(count, part1+part2);
        }

        // 缓存
        cache.put(num, count);
        return count;
    }

    public static int SuNumOfArr(int [] arr) {
        int sum = 0;
        for (int n: arr){
            int res = SuNumOfNum(n);
            sum += res;
        }
        return sum;
    }

    // 问题1输入输出
    public static void Problem1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(SuNumOfArr(arr));
    }


    //***************

    /**
     * 从小于第一个大于当前数字位置开始补充
     * 补充的数字为目前未出现的最小数字
     * 5 3
     * 2 1 5
     * 2 1 3 4 5
     * */
    public static int[] MinDixSe(int[] arr, int n){
        if (n<=arr.length){
            return arr;
        }
        int [] res = new int[n];
        // 将已有数字存入set
        HashSet<Integer> old = new HashSet<>();
        for (int a:arr){
            old.add(a);
        }
        //
        int small = 1;
        int fillLoc = 0;
        int oldLoc = 0;
        while (fillLoc<n){
            if (old.contains(small)){
                small ++;
                continue;
            }
            if (oldLoc>=arr.length || small<arr[oldLoc]){
                res[fillLoc++] = small++;
            }else{
                res[fillLoc++] = arr[oldLoc++];
            }

        }
        return res;
    }

    public static void Problem2() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int [] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
        }
        int[] res = MinDixSe(arr, n);
        for (int i=0, len = res.length; i<len; i++){
            if (i>0){
                System.out.print(' ');
            }
            System.out.print(res[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Problem1();
    }
}
