package Company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Baidu {
    /**
     * 首先给出n个数字a1,a2,….an，然后给你m个回合，
     * 每回合你可以从中选择一个数取走它，剩下来的每个数字ai都要减去一个值bi。
     * 如此重复m个回合，所有你拿走的数字之和就是你所得到的分数。
     * 现在给定你a序列和b序列，请你求出最多可以得到多少分。
     * */

    public static int prob1(int M, int N, int[] arrA, int[]arrB){
        int[][] cache = new int[210][210];
        for (int i = 0; i <N ; i++) {
            for (int j = i+1; j <N ; j++) {
                if (arrB[i]<arrB[j]){
                    int temp = arrB[i];
                    arrB[i] = arrB[j];
                    arrB[j] = temp;
                    //
                    temp = arrA[i];
                    arrA[i] = arrA[j];
                    arrA[j] = temp;
                }
            }
        }
        // TODO
        for (int i = 1; i <N ; i++) {
            for (int j = 1; j <M ; j++) {
                cache[i][j] = Math.max(cache[i-1][j], cache[i-1][j-1]+arrA[i]-arrB[i]*(j-1));
            }
        }
        return cache[N][M];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 回合
        int M = sc.nextInt();
        int [] arrA = new int[N];
        for (int i = 0; i < N; i++) {
            arrA[i] = sc.nextInt();
        }
        int [] arrB = new int[N];
        for (int i = 0; i < N; i++) {
            arrB[i] = sc.nextInt();
        }
        System.out.println(prob1(M,N,arrA,arrB));
    }
}
