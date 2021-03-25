package algorithm;

import java.lang.reflect.Array;
import java.util.*;

public class GroupAnagrams {
    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     * 有且只有相同的字符<--长度都相同切字符都包括
     * 建立(字符，长度)到字符串的映射
     * S1. 直接使用字符ascii之后相加作为key存在问题：duh=321，ill=321；
     * S2. 每个key都是26长度的字符串，包含哪个字母，对应位置变为1，然后作为时间复杂度：key O(N)* O(K)，空间复杂度O(N*K)；
     * S3. 排序字符串的字母，排序后一致的就是一组 O(N)* O(K logK)，空间复杂度O(N*K) N表示字符串个数，K表示字符串长度；
     * */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groupMap = new HashMap<>();
        for (String str:strs)
        {
            // 类似one-hot步骤
            int[] key = new int[26];
            Arrays.fill(key, 0);
            for (int i = 0; i< str.length(); i++){
                key[str.charAt(i)-'a'] += 1;
            }
            StringBuilder keyStrB = new StringBuilder();
            for (int i: key){
                keyStrB.append(i);
            }
            String keyStr = keyStrB.toString();

            if (groupMap.containsKey(keyStr))
            {
                groupMap.get(keyStr).add(str);
            }else {
                List<String> store = new  ArrayList<>();
                store.add(str);
                groupMap.put(keyStr, store);
            }
        }
        return new ArrayList<>(groupMap.values());
    }

    public static void main(String[] args) {
        String [] input = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        List<List<String>>  res = groupAnagrams(input);
        for (List<String> line :res){
            for (String item:line){
                System.out.print(item+ " ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
