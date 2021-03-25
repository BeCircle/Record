package Company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class NetEasy {
    public static int prob1(int[] arr) {
        /**
         * 数组相邻数求差，然后对差求最大公约数
         * */
        int res = -1;
        if (arr.length >= 2) {
            int mcd = arr[1] - arr[0];
            for (int i = 2; i < arr.length && mcd > 0; i++) {
                mcd = maxCommonDivisor(mcd, arr[i] - arr[i - 1]);
            }
            if (mcd > 0) {
                res = mcd;
            }
        }
        return res;
    }

    private static int maxCommonDivisor(int a, int b) {
        if (a <= 0 || b <= 0) return 0;
        // 最大公约数
        while (true) {
            if (a > b) {
                a -= b;
            } else if (a < b) {
                b -= a;
            } else {
                return a;
            }
        }
    }

    public static void runProb1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(prob1(arr));
    }

    public static int prob2(ArrayList<Node> arr, int D){
        /**
         * 承受最小伤害
         * 3 50
         * 100 50 51
         * 1000 1000 1000
         *
         * 1000
         * */
        int heart=0;
        // 排序
        arr.sort(Comparator.comparingInt(o -> o.val1));
        int i=0;
        for (; i < arr.size()&&arr.get(i).val1<D; i++) {
            D++;
        }
        for (int j = arr.size()-1; j >i ; j--) {
            if (arr.get(j).val1>D){
                heart += arr.get(j).val2;
                
            }
            D++;
        }
        return heart;
    }

    static class Node{
        // 攻防
        int val1;
        // 伤害
        int val2;

        public Node(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }

    public static void runProb2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int D = sc.nextInt();
        ArrayList <Node> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Node(sc.nextInt(), 0));
        }
        for (Node node:arr){
            node.val2 = sc.nextInt();
        }
        System.out.println(prob2(arr, D));
    }

    public static void main(String[] args) {
        runProb2();
    }
}
