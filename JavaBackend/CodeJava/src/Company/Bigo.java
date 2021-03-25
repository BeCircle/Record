package Company;

import java.util.HashMap;
import java.util.Map;

public class Bigo {
    public static Character getSecondChar(String param) {
        if (param.length()<8){
            return null;
        }
        // 统计出现次数
        Map<Character, Integer> count = new HashMap<>();
        for (char c: param.toCharArray()){
            if (count.containsKey(c)){
                count.put(c, count.get(c)+1);
            }else{
                count.put(c, 1);
            }
        }
        // 输出第二次出现，并个数为4的字符
        int num=0;
        for (char c: param.toCharArray()){
            if (count.containsKey(c)&&count.get(c)==4){
                if (num==1){
                    return c;
                }
                num+=1;
            }
        }
        return null;
    }

    public static <T> void selectSortDesc(T[] arr){
        int inOrder = 0;
        int len = arr.length;
        while (inOrder<len){
            int nextLargest = inOrder;
            for (int i = inOrder+1; i < len; i++) {
//                if (arr[i]>arr[nextLargest]){
//                    nextLargest = i;
//                }
            } 
            // 原地交换
            T temp = arr[inOrder];
            arr[inOrder++] = arr[nextLargest];
            arr[nextLargest] = temp;
        }
    }
}
