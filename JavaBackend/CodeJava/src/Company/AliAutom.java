package Company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AliAutom {
    /**
     * 判断S的子串是T的子序列的个数
     * 子序列表示在T中相对位置固定，中间可以有其他字符间隔
     * 这种做法理论是正确的，只是阿里笔试时Integer loc : locs没有从左往右判断
     * */
    public static int similarity(int lenS, String S, int lenT, String T) {
        // 将T拆成hash,记录位置
        Map<Character, List<Integer>> charMap = new HashMap<>();
        for (int i = 0; i < lenT; i++) {
            char c = T.charAt(i);
            if (!charMap.containsKey(c)){
                List<Integer> temp = new LinkedList<>();
                temp.add(i);
                charMap.put(c, temp);
            }else {
                charMap.get(c).add(i);
            }
        }
        // 记录前缀和前缀位置
        int [] preLoc = new int[lenS];
        for (int i = 0; i < lenS; i++) {
            preLoc[i] = -1;
        }
        int total = 0;
        for (int i = 0; i < lenS; i++) {
            char c = S.charAt(i);
            // i==前缀个数
            int j = 0;
            for (; j < i; j++) {
                int pLoc = preLoc[j];
                // 前缀是子序列
                if (pLoc>=0 && charMap.containsKey(c)){
                    List<Integer> locs = charMap.get(c);
                    for (Integer loc : locs) {
                        if (loc > pLoc) {
                            preLoc[j] = loc;
                            total += 1;
                            break;
                        }
                    }
                }
            }
            // 自身
            if (charMap.containsKey(c)){
                preLoc[j] = charMap.get(c).get(0);
                total += 1;
            }
        }
        return total;
    }

    public static void run1() {
//        Scanner sc = new Scanner(System.in);
//        // length of S
//        int lenS = sc.nextInt();
//        // length of T
//        int lenT = sc.nextInt();
//        sc.nextLine();
//        String S = sc.nextLine();
//        String T = sc.nextLine();
//        sc.close();
//        System.out.println(similarity(lenS, S, lenT, T));
        System.out.println(similarity(2, "ac", 1, "a"));
    }
    public static void main(String[] args) {
        run1();
    }
}
