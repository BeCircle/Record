package Company;

import org.junit.Test;

import java.util.*;

public class PDD {
    
    /**
     * 最省钱的（买三送一，价格低的那个免费）拼团方式
     * eg. 100,200,300,400
     * 200,300,400一起买，200的是免费送，花费700元
     * 100 单独买，花费100
     * 结果是800
     */
    public static long Prob1(int[] price) {
        // 按照价格降序
        Arrays.sort(price);
        // 循环，第三个不用付钱
        long sum = 0;
        for (int i = price.length - 1, j = 1; i >= 0; i--, j++) {
            if (j % 3 != 0) {
                sum += price[i];
            }
        }
        return sum;
    }

    public static void RunProb1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] price = new int[N];
        for (int i = 0; i < N; i++) {
            price[i] = sc.nextInt();
        }
        System.out.println(Prob1(price));
    }


    /**
     * 超时，通过率60%
     * */
    public static int Prob2(int[] arr, int treeNum, int M) {
        int[] cache = new int[treeNum];
        int resCount = 0;
        // j 表示长度
        for (int j = 0; j < treeNum; j++) {
            // i表示起点
            for (int i = 0; i < treeNum && i + j < treeNum; i++) {
                cache[i] += (arr[i+j]%M);
                if (cache[i] % M == 0) {
                    resCount++;
                    // 清零，防止溢出
                    cache[i] = 0;
                }
            }
        }
        return resCount;
    }

    public static void RunProb2() {
        Scanner sc = new Scanner(System.in);
        // 树的数量
        int treeNum = sc.nextInt();
        // 和谐
        int M = sc.nextInt();
        int[] arr = new int[treeNum];
        for (int i = 0; i < treeNum; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(Prob2(arr, treeNum, M));
    }

    /**
     * 6 5
     * 787585
     * */
    public static void Prob3(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int [] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();
        // 计数
        int BASE=10;
        int [] nums = new int[BASE];
        for (int n:arr){
            nums[n]++;
        }
        int globalDiff = 0;
        // 10种base
        for (int i = 0; i <BASE ; i++) {
            // 选择和base最好的
            int BaseDiff = 0;
            // 还差多少个
            int need = K-nums[i];
            // 根据绝对值范围进行搜索；
            for (int diff = 1; diff < BASE-1&&need>0; diff++) {
                if (i+diff<BASE){
                    int use = Math.min(need, nums[i + diff]);
                    need -= use;
                    BaseDiff+=use*(i+diff);
                }
                if (need>0&& i-diff>=0){
                    int use = Math.min(need, nums[i - diff]);
                    need -= use;
                    BaseDiff+=use*(i-diff);
                }
            }
            if (globalDiff>BaseDiff||globalDiff==0){
                globalDiff = BaseDiff;
            }
        }
        System.out.println(globalDiff);

        System.out.println(arr);
    }
    public static void main(String[] args) {
        RunProb2();
    }
}
