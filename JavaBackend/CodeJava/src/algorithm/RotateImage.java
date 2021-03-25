package algorithm;

public class RotateImage {
    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * 示例 1:
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * 链接：https://leetcode-cn.com/problems/rotate-image
     * 每一环：左列的n-1项，最后一行覆盖第一列，右列n-1填最后一行，顶行n-2填右列，事先存储的n-1填顶行
     */
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        if (len < 2) return;
        // 由外向内逐层旋转
        int inLen = len;
        for (int c = 0; c < len / 2; c++) {
            // 复制出左列，开启旋转
            int[] leftColumn = new int[inLen - 1];
            for (int i = 0; i < leftColumn.length; i++) {
                leftColumn[i] = matrix[i+c][c];
            }
            // 复制最后一行到左列
            for (int i = c; i < inLen+c; i++) {
                matrix[i][c] = matrix[inLen - 1+c][i];
            }
            // 复制右列到最后一行（逆序）
            for (int i = c + 1; i < inLen+c; i++) {
                matrix[inLen - 1+c][i] = matrix[inLen - 1 - i+2*c][inLen - 1+c];
            }
            // 复制顶行到右列（逆序）
            for (int i = inLen - 2+c; i > c; i--) {
                matrix[i][inLen - 1+c] = matrix[c][i];
            }
            // 预先存储的左列填到顶行（逆序）
            for (int i = c + 1; i < inLen+c; i++) {
                matrix[c][i] = leftColumn[inLen +c- 1 - i];
            }
            printMatrix(matrix);
            // 向内，长度缩减
            inLen -= 2;
        }

    }

    public static void rotate2(int[][] matrix) {
        /**
         * S2. 先转置矩阵，在翻转每一行
         * */
        int len = matrix.length;
        if (len < 2) return;
        // 转置矩阵
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        printMatrix(matrix);
        // 行翻转
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len-1-j];
                matrix[i][len-1-j] = temp;
            }
        }
    }

    public static void rotate3(int[][] matrix) {
        /**
         * S3. 每次四个对应位置的元素之间旋转；
         * 关键在于确定4个点的坐标，最好画出矩阵，先确定对角的点；
         * */
        int len = matrix.length;
        if (len < 2) return;
        // 由外向内逐层旋转
        for (int c = 0; c < len / 2; c++) {
            for (int i = c; i < len-c-1; i++) {
                int temp = matrix[c][i];
                matrix[c][i] = matrix[len -1-i][c];
                matrix[len -1-i][c] = matrix[len -1-c][len -1-i];
                matrix[len -1-c][len -1-i]=matrix[i][len -1-c];
                matrix[i][len -1-c] = temp;
            }
            printMatrix(matrix);
        }

    }
    public static void   printMatrix(int [][] matrix){
        for (int[] line : matrix) {
            for (int item : line) {
                System.out.print(item + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}};
        rotate3(matrix);
        printMatrix(matrix);
    }
}
