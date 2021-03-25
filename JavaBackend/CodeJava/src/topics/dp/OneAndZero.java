package topics.dp;

public class OneAndZero {
    /**
     * 支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     * 使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     *
     * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
     * */

    /**
     * 状态转移方程：
     * f(i, m,n) = max{f(i-1, m-one[i], n-zero[i])+1, f(i-1, m, n)}
     * */
    public static int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        // 计算每个字符串中的0/1
        int [] zero= new int[len];
        int [] one = new int[len];
        for (int i=0; i<len; i++){
            int z=0, o=0;
            for (char c: strs[i].toCharArray()){
                if (c=='1'){
                    o+=1;
                }else if (c == '0'){
                    z+=1;
                }
            }
            zero[i] = z;
            one[i] = o;
        }
        int [][][] mem = new int[m+1][n+1][len];
        // 计算
        return findMaxFrom(mem, one, zero, m, n, len-1);
    }
    private static int findMaxFrom(int [][][] mem,int[] one, int[] zero, int m, int n, int i){
        if (i<0){
            return 0;
        }
        if (mem[m][n][i]>0){
            return mem[m][n][i];
        }
        int res = findMaxFrom(mem, one, zero, m, n, i-1);
        if (m>=zero[i] && n>=one[i]){
            int chooseThis = findMaxFrom(mem, one, zero, m-zero[i],n-one[i], i-1)+1;
            if (chooseThis>res){
                res = chooseThis;
            }
        }
        mem[m][n][i]=res;
        return res;
    }

    public static void main(String[] args) {
//        String []arr = {"10", "0001", "111001", "1", "0"};
//        int m = 5, n = 3;
//        String []arr =  {"11","11","0","0","10","1","1","0","11","1","0","111","11111000","0","11","000","1","1","0","00","1","101","001","000","0","00","0011","0","10000"};
//        int m = 90, n = 66;
//        String [] arr = {"10","0","1"};
//        int m = 1, n =1;
        String []arr = {"10","0001","111001","1","0"};
        int m = 4, n=3;
        System.out.println(findMaxForm(arr, m, n));

    }
}
