package Company;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class WeBank {
    /**
     * @param n 小朋友
     * @param m 礼物
     * @param a 每个红包花费
     * @param b 新买礼物单价
     */
    public static int costOfAvgGift(int n, int m, int a, int b) {
        int cost = 0;
        if (m % n == 0) {
            cost = 0;
        } else if (m < n) {
            // 每人不够一个
            cost = a < b ? (n - m) * a : (n - m) * b;
        } else {
            // 没人够一个，但是平均
            for (int i = 0; i <n ; i++) {
                // 余数
                int reserve = m%(n-i);
                int newGift = reserve==0?0:n-i-reserve;
                int tCost = newGift*b+i*a;
//                System.out.println(i+":"+newGift+":"+tCost);
                if (tCost<cost||cost==0){
                    cost = tCost;
                }
            }
        }
        return cost;
    }

    public static void runProb1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
//        int n = 7, m = 10, a = 10, b = 100;
        System.out.println(costOfAvgGift(n, m, a, b));
    }

    static class Node{
        int money;
        int cNum;
        public Node(int money, int cNum) {
            this.money = money;
            this.cNum = cNum;
        }
    }
    public static int prob3(ArrayList<Node> posCards, ArrayList<Node> otherCards){
        int money = 0;
        int cardNum =1;
        for (Node node:posCards){
            money+= node.money;
            // 每次会消耗一次机会
            cardNum += (node.cNum-1);
        }
        // 抽卡机会<1的卡片，按照钱降序
        otherCards.sort(Comparator.comparingInt(o->o.money));
        for (int i = otherCards.size()-1; i >=0&&cardNum>0 ; i--, cardNum--) {
            money += otherCards.get(i).money;
        }
        return money;
    }

    /**
     * 5
     * 0 2
     * 1 1
     * 1 0
     * 1 0
     * 2 0
     * */
    public static void runProb3(){
        Scanner sc = new Scanner(System.in);
        int cardNum = sc.nextInt();
        // 卡片数为正
        ArrayList<Node> posCards = new ArrayList<>();
        ArrayList<Node> otherCards = new ArrayList<>();
        for (int i = 0; i <cardNum ; i++) {
            int money = sc.nextInt();
            int cNum = sc.nextInt();
            if (cNum>0){
                posCards.add(new Node(money, cNum));
            }else {
                otherCards.add(new Node(money, cNum));
            }
        }
        System.out.println(prob3(posCards, otherCards));
    }

    /**
     * t个只包含小写字母的字符串
     * 重新排列此字符串，如果能组成会问串则Cassidy获胜，
     * 否则，删除字符串中一个字符
     * Cassidy先手，Eleanore后手，两人轮流采取最佳策略
     * 输出在谁手中这个字符串可以组成回文串。
     * */
    public static void prob2(){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        String [] S = new String[t];
        for (int i = 0; i < t; i++) {
            S[i] = scanner.next();
        }
        scanner.close();

        int [] charCount = new int[26];
        for (int i = 0; i < t; i++) {
            char [] strChar = S[i].toCharArray();
            // 统计每个字母的个数
            for (char c:strChar){
                charCount[c-'a']++;
            }
            // 奇数个的字母的数量
            int singleNum = 0;
            for (int value:charCount){
                if (value%2!=0){
                    singleNum ++;
                }
            }
            if (singleNum == 0 || singleNum % 2 != 0){
                // 数量为奇数的字母没有，可以直接构成回文
                // 或者为奇数，几轮删除之后也是Cassidy获胜
                System.out.println("Cassidy");
            }else {
                System.out.println("Eleanore");
            }
        }
    }

    /**
     * 判断字母大小写
     * @param c 字符
     * @return 0:小写, 1:大写，-1：其他
     * */
    public static int JudgeDig(char c){
        if (c>='a'&&c<='z'){
            return 0;
        }else if(c>='A'&&c<='Z'){
            return 1;
        }
        return -1;
    }
    /**
     * 大写转小写
     * @param c 输入字符
     * @return 如果输入大写字符，转为对应小写字符
     * */
    public static int BDig2SDig(char c){
        if (c>='A'&&c<='Z'){
            int diff = 'a'-'A';
            c+=diff;
        }
        return c;
    }
    @Test
    public void TestBigDig(){
        // 判断大小写
        Assert.assertEquals(0, JudgeDig('c'));
        Assert.assertEquals(1, JudgeDig('M'));
        Assert.assertEquals(-1, JudgeDig('|'));

        // 大小写转换
        Assert.assertEquals('a', BDig2SDig('A'));
        Assert.assertEquals('a', BDig2SDig('a'));
        Assert.assertEquals('/', BDig2SDig('/'));
    }
    public static void main(String[] args) {
        prob2();
//        runProb1();
//        runProb3();
    }
}
