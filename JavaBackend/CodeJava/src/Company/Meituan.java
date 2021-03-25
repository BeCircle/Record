package Company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Meituan {
    public static int findPaths(int[][] table) {
        int[] cache = {1, 0};
        for (int i = 1; i < table[0].length; i++) {
            int p0 = 0, p1 = 0;
            if (table[0][i] != 0)// 没有障碍
                p0 = cache[0] + cache[1];
            if (table[1][i] != 0)
                p1 = cache[0] + cache[1];
            cache[0] = p0;
            cache[1] = p1;
        }
        if (cache[1] == 0)
            return -1;
        return cache[1];
    }

    public static void runProb1() {
        // 45%
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[][] table = new int[2][len];
        sc.nextLine();
        for (int i = 0; i < 2; i++) {
            String line = sc.nextLine();

            for (int j = 0; j < len; j++) {
                char c = line.charAt(j);
                if (c == '.')
                    table[i][j] = 1;
                else if (c == 'X')// 障碍
                    table[i][j] = 0;
            }
        }
        // 0表示障碍
        System.out.println(findPaths(table));
    }

    public static int findSame(HashMap<Integer, Integer> array, int target, int largest) {
        /**
         * 给出一个序列包含n个正整数的序列A，然后给出一个正整数x，
         * 你可以对序列进行任意次操作的，每次操作你可以选择序列中的一个数字，让其与x做按位或运算。你的目的是让这个序列中的众数出现的次数最多。
         * 请问众数最多出现多少次
         *
         * 亦或一次才有效，多次无效；
         * */
        for (Map.Entry<Integer, Integer> item : array.entrySet()) {
            int orig = item.getKey();
            int afterC = orig | target;
            if (afterC != orig) {
                if (array.containsKey(afterC)) {
                    int temp = array.get(afterC) + item.getValue();
                    // 记录最大值
                    if (temp > largest) largest = temp;
                    array.put(afterC, temp);
                }
            }
        }
        return largest;
    }

    public static void runProb2() {
        // 90%
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int target = sc.nextInt();
        HashMap<Integer, Integer> input = new HashMap<>();
        int largest = 0;
        for (int i = 0; i < len; i++) {
            int temp = sc.nextInt();
            int val = 1;
            if (input.containsKey(temp)) {
                val = input.get(temp) + 1;
            }
            if (largest < val) largest = val;
            input.put(temp, val);
        }
        System.out.println(findSame(input, target, largest));
    }

    /**
     * 小美曾经有一个特殊的数组，这个数组的长度为len。
     * 但是她在打恐怖游戏的时候被吓得忘记了这个数组长什么样了。不过她还记得这个数组满足一些条件。
     * 首先这个数组的每个数的范围都在L和R之间。包括端点。
     * 除此之外，这个数组满足数组中的所有元素的和是k的倍数。
     * 但是这样的数组太多了，小美想知道有多少个这样的数组。你只需要告诉她在模1e9+7意义下的答案就行了
     * 9 1 1 3
     * 19683
     * 暴力法
     * */
    public static long traceBack(int deep,int k,  long sum, long l, long r, long largest){
        if (sum+l>=largest){
            return 0;
        }else if(deep==0 && sum%k==0){
            return 1;
        }
        long res = 0;
        for (long i = l; i<=r; i++){
            res+= traceBack(deep -1,k, sum+i, l, r, largest);
        }
        return res;
    }
    public static void runProb3(){
        // 18%
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int k = sc.nextInt();
        long l = sc.nextLong();
        long r  = sc.nextLong();

        System.out.println(traceBack(len, k,0, l,r,Long.MAX_VALUE));
    }

    public static void main(String[] args) {
        runProb1();
    }
}
