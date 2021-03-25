package Company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Kuaishou {
    /**
     * 获取队中从前到后每个人与前方身高高于自己的人的最短距离
     *
     * @param height int整型一维数组 队中从前到后每个人与前方身高高于自己的人的最短距离
     * @return int整型一维数组
     */
    public static int[] DistanceToHigher(int[] height) {
        // 暴力：O(n*n)
        int len = height.length;
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            // 初始化
            res[i] = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > height[i]) {
                    res[i] = i - j;
                    break;
                }
            }
        }
        return res;
    }

    @Test
    public void runProb1() {
        int[] testCase = {175, 173, 174, 163, 182, 177};
        // 0,1,2,1,0,1
        int[] res = DistanceToHigher(testCase);
        for (int re : res) {
            System.out.print(re + "\t");
        }
    }

    public static ArrayList<Integer> problem2(ArrayList<Integer> inArray) {
        /**
         * 从左往右扫描，始终记录当前最大的两个数，和当前数字比较
         * */
        ArrayList<Integer> res = new ArrayList<>();
        int largest = -1, larger = -2;
        for (int i = 0; i < inArray.size(); i++) {
            int item = inArray.get(i);
            if (largest > item && larger <= item) {
                res.add(i);
            }
            // 更新标记
            if (item > largest) {
                larger = largest;
                largest = item;
            } else if (item > larger) {
                larger = item;
            }
        }
        return res;
    }

    @Test
    public void runProb2() {
        // 1 22 22 33 22 12 45 44 5
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> inArray = new ArrayList<>();
        String line = sc.nextLine();
        String[] list = line.split(" ");
        for (String str : list) {
            inArray.add(Integer.parseInt(str));
        }
        // 0表示障碍
        ArrayList<Integer> res = problem2(inArray);
        if (res.size() > 0) {
            boolean first = true;
            for (Integer i : res) {
                if (!first) {
                    System.out.print(" ");
                } else {
                    first = false;
                }
                System.out.print(i);
            }
        } else {
            System.out.println(-1);
        }
    }


    static class Num{
        String num;
        int val;
        Num(String n, int v){
            num = n;
            val = v;
        }
    }

    public static int valOfNum(String num){
        if (num==null||num.length()!=11){
            return 0;
        }
        // 计算一个号码的价值, 不是靓号就为0，否则>0;
        int val = 0;
        // 1 表示连号，2表示重复
        int valType = 0;
        int valLen = 1;
        int bestType = 0;
        int bestValLen = 1;
        // 前一个数
        int lastNum = -9;
        for (int i = 3; i < num.length(); i++) {
            int n = num.charAt(i)-'0';
            if (lastNum == n-1){// 和前一个数字连号
                if (valType==1) {
                    valLen++;
                }else {
                    if (valLen>bestValLen){
                        bestValLen = valLen;
                        bestType = valType;
                    }
                    valLen=2;
                }
                valType = 1;
            }else if (lastNum==n){// 和前一个数字重复
                if (valType==2){
                    valLen ++;
                }
                else {
                    if (valLen>bestValLen){
                        bestValLen = valLen;
                        bestType = valType;
                    }
                    valLen=2;
                }
                valType =2;
            }else {// 当前数字中断
                if (valLen>bestValLen){
                    bestValLen = valLen;
                    bestType = valType;
                }
                // 重置
                valLen = 1;
                valType = 0;
            }
            // 更新lastNum
            lastNum = n;
        }
        if (bestValLen>=3){
            val = bestValLen*10;
            if (bestType==2){// 重复优于连号
                val+=5;
            }
        }
        return val;
    }
    public static LinkedList<Num> findBeautyNums(String[] numbers) {

        // 找出靓号，并降序输出(稳定排序)
        LinkedList<Num> beauty = new LinkedList<>();
        // 计算所有号码的价值，不是靓号就为0
        for(String str:numbers){
            int val = valOfNum(str);
            if (val>0){
                boolean insert = false;
                for (int i = 0; i < beauty.size(); i++) {
                    if (val>beauty.get(i).val){
                        beauty.add(i, new Num(str, val));
                        insert = true;
                        break;
                    }
                }
                if (!insert){
                    // 未插入，则加到末尾
                    beauty.addLast(new Num(str, val));
                }
            }
        }
        // 将靓号稳定排序

        return beauty;
    }

    @Test
    public void runProb3() {
//        Scanner sc = new Scanner(System.in);
//        String line = sc.nextLine();
//        String line = "14612244221,14612544221,14612644221";
//        String line = "15166667234,15188887234";
        String line = "15112347234,15176313245,15176313346,15176313325,15166667243,15188847243";
        String[] numbers = line.split(",");
        LinkedList<Num> beautyNumbers = findBeautyNums(numbers);
        if (beautyNumbers.size() > 0) {
            boolean start = true;
            for (Num number : beautyNumbers) {
                if (!start) {
                    System.out.print(",");
                } else {
                    start = false;
                }
                System.out.print(number.num);
            }
        } else {
            System.out.println("null");
        }
    }

}
