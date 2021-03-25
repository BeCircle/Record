package Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/4
 */
public class HuaWei {
    public static void prob1(int[][] matrix, int m) {
        /**
         * 顺时针旋转矩阵
         * */
        // 旋转4次和原来一样
        m %= 4;
        while (m > 0) {
            // 沿左上到右下对角线翻转
            int len = matrix.length;
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            // 再每行镜像
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len / 2; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][len - 1 - j];
                    matrix[i][len - 1 - j] = temp;
                }
            }
            m--;
        }

    }

    public static void runProb1() {
        // TODO Read
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] input = new int[n][n];
        int num = 0;
        while (sc.hasNextInt() && num < n * n) {
            input[num / n][num % n] = sc.nextInt();
            num++;
        }
        int m = sc.nextInt();
        sc.close();
        prob1(input, m);

        // 输出
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }
                System.out.print(input[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void recursive(int len, int gifts, int sep, int total, String path, List<String> result) {
        // 递归处理
        if (len == total) {
            result.add(path);
        } else {
            // 一个礼物加入排列
            if (gifts > 0) {
                recursive(len + 1, gifts - 1, sep, total, path + "*", result);
            }
            // 一个分隔加入排列
            if (sep > 0) {
                recursive(len + 1, gifts, sep - 1, total, path + "|", result);
            }
        }
    }

    public static void prob2() {
        // input
        Scanner sc = new Scanner(System.in);
        int gifts = sc.nextInt();
        int child = sc.nextInt();
        sc.close();
        // 主逻辑
        int total = gifts + child - 1;
        List<String> res = new ArrayList<>();
        // 将问题看做gift和child-1（分隔符）的排列
        recursive(0, gifts, child - 1, total, "", res);
        // 输出
        System.out.println(res.size());
        for (String str : res) {
            System.out.println(str);
        }
    }

    public static int editDistance(String strOrigin, String strEdited) {
        // 校验编辑前后文章，计算最少需要编辑次数
        // 编辑距离
        int lenO = strOrigin.length();
        int lenE = strEdited.length();
        // 其中一个无字符
        if (lenO == 0) return lenE;
        if (lenE == 0) return lenO;

        int[][] res = new int[lenO + 1][lenE + 1];
        for (int i = 0; i < lenO; i++) {
            res[i][0] = i;
        }
        for (int i = 1; i < lenE; i++) {
            res[0][i] = i;
        }
        for (int oI = 1; oI <= lenO; oI++) {
            char oriChar = strOrigin.charAt(oI - 1);
            for (int eI = 1; eI <= lenE; eI++) {
                char editChar = strEdited.charAt(eI - 1);
                if (oriChar == editChar) {// 当前字符相等
                    res[oI][eI] = res[oI - 1][eI - 1];
                } else {// res[oI - 1][eI - 1] 替换、res[oI - 1][eI]增加、res[oI][eI - 1]删除
                    res[oI][eI] = Math.min(res[oI - 1][eI - 1], Math.min(res[oI - 1][eI], res[oI][eI - 1])) + 1;
                }
            }
        }
        return res[lenO][lenE];
    }

    public static void runProb3() {
        Scanner sc = new Scanner(System.in);
        int lineNum = sc.nextInt();
        List<String> origin = new ArrayList<>();
        List<String> edited = new ArrayList<>();
        // 读入原始文本
        int line = 0;
        sc.nextLine(); // 上一行的换行符
        while (line < lineNum && sc.hasNextLine()) {
            origin.add(sc.nextLine());
            line++;
        }
        // 编辑后的文本
        line = 0;
        while (line < lineNum && sc.hasNextLine()) {
            edited.add(sc.nextLine());
            line++;
        }
        // 及计算编辑距离
        int sum = 0;
        for (int i = 0; i < origin.size(); i++) {
            sum += editDistance(origin.get(i), edited.get(i));
        }
        System.out.print(sum);
    }


    public static void main(String[] args) {
//        runProb1();
//        prob2();
        runProb3();

    }
}
