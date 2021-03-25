package Offer;

public class NumOf1 {
    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 10: 00001010
     * 负数：原码->非符号位取反->加1->补码
     * -10:
     * 11111111111111111111111111110110
     * 减一得到反码
     * 11111111111111111111111111110101
     * 非符号位取反
     * 10000000000000000000000000001010
     *
     * 00001010^0=本身
     *
     * */
    public static int NumberOf1(int n) {
        int i = 1;
        int numOf1 = 0;
        int epoch = 1;
        // 非符号位的1的个数
        while (epoch++ <32){
            // 二进制中只有一位为1的数，这个位不断左移，&之后检测每一位的值
            if ((n&i)>0){
                numOf1++;
            }
            i<<=1;
        }
        // 负数符号位的二进制
        if (n<0) numOf1+=1;
        return numOf1;
    }

    public static int NumberOf1Better(int n) {
        int numOf1 = 0;
        int epoch = 1;
        // 正数或者负数左边填充为0时可提前终止循环
        while (n!=0 && epoch++ <=32){
            // 二进制中只有一位为1的数，这个位不断左移，&之后检测每一位的值
            if ((n&1)>0){
                numOf1++;
            }
            n>>=1;
        }
        return numOf1;
    }

    public static void main(String[] args) {
        int inInt = 10;
        System.out.println(Integer.toBinaryString(inInt));
        System.out.println(NumberOf1Better(inInt));
    }
}
