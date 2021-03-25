package topics.dp;

public class LTurnbulentSubarry {
    /**
     * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     * 返回 A 的最大湍流子数组的长度。
     * 示例 1：
     * 输入：[9,4,2,10,7,8,8,1,9]
     * 输出：5
     * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
     */
    public static int maxTurbulenceSize0(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int len = A.length;
        // s[i][0]表示以i结尾的湍流子数组长度，
        // s[i][1] 表示a[i]与a[i-1]的符号，0表示相等或者第0个
        int[][] states = new int[len][2];
        // 初始化state
        states[0][0] = 1;
        states[0][1] = 0;
        // dp
        int res = 1;
        for (int i = 1; i < len; i++) {
            int symbol = Integer.compare(A[i - 1], A[i]);
            if (symbol == 0) {
                states[i][0] = 1;
            } else if (symbol == -states[i - 1][1]) {
                states[i][0] = states[i - 1][0] + 1;
            } else {
                states[i][0] = 2;
            }
            states[i][1] = symbol;
            if (states[i][0] > res) {
                res = states[i][0];
            }
        }
        return res;
    }

    // 优化空间
    public static int maxTurbulenceSize(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int len = A.length;
        // 初始化state
        int state = 1, lastSymbol = 0;
        // dp
        int res = 1;
        for (int i = 1; i < len; i++) {
            int symbol = Integer.compare(A[i - 1], A[i]);
            if (symbol == 0) {
                state = 1;
            } else if (symbol == -lastSymbol) {
                state += 1;
            } else {
                state = 2;
            }
            lastSymbol = symbol;
            if (state > res) {
                res = state;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] arrs = {
                {9, 4, 2, 10, 7, 8, 8, 1, 9},
                {4, 8, 12, 16},
                {100}
        };
        for (int[] arr : arrs) {
            System.out.println(maxTurbulenceSize(arr));
        }
    }
}
