package algorithm;

import java.util.Arrays;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/1/15
 */
public class ValidSudoku {
    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * 链接：https://dev.lingkou.xyz/problems/valid-sudoku
     */
    public static boolean isValidSudoku(char[][] board) {
        int [][] rowCount = new int[9][9];
        int [][] columnCount = new int[9][9];
        int [][] boxCount = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int item = board[i][j] - '0';
                if (item<=9&&item>=1){
                    // 计算数字属于那一块, C/3*3+R/3
                    int boxId = (i/3)*3+j/3;

                    if (rowCount[j][item-1]>0 || columnCount[i][item-1]>0||boxCount[boxId][item-1]>0){
                        // 违反行规则、列规则、方块规则
                        return false;
                    }
                    rowCount[j][item-1]=1;
                    columnCount[i][item-1]=1;
                    boxCount[boxId][item-1] = 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] sudo1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] sudo2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(sudo1));
        System.out.println(isValidSudoku(sudo2));
    }

}
