package Offer;

public class Fibonacci {
    /**
     * 输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     * 0,1,1,2,3,5,8,13,21
     * 0,1,2,3,4,5,6,07,08
     * T(n) = T(n-1)+T(n-2)
     * */
    public static int Fibonacci(int n) {
        int[] cache = {0,1};
        if (n<0){
            return -1;
        }
        else if (n>=0&&n<2){
            return cache[n];
        }
        while (n>=2){
            int temp = cache[0];
            cache[0] = cache[1];
            cache[1] = cache[0]+temp;
            n--;
        }
        return cache[1];
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(1));
    }
}
