package Offer;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * */

public class ISNumber {
    private static int digitalSeq(int index, String s) {
        int len  = s.length();
        while (index <len &&s.charAt(index)>='0'&&s.charAt(index)<='9'){
            index ++;
        }
        return index;
    }
    public static boolean isNumber(String s) {
        // 去除空格
        s = s.replace(" ", "");

        int len  = s.length();
        int index = 0;
        if (len>0){
            // 一个正负号
            if (s.charAt(index)=='-' || s.charAt(index)=='+'){
                index ++;
            }
            int newIdx = digitalSeq(index, s);
            // TODO
            if (newIdx == index){
                return false;
            }else {
                index = newIdx;
            }
            if (index > len-1){
                return true;
            }
            if (s.charAt(index)=='.'){
                index ++;
                if (index<len){
                    newIdx = digitalSeq(index, s);
                    if (newIdx == index){
                        return false;
                    }else {
                        index = newIdx;
                    }
                    return index > len - 1;
                }
            }else if (s.charAt(index) == 'e' || s.charAt(index) == 'E'){
                index ++;
                if (index<len) {
                    if (s.charAt(index) == '-') {
                        index++;
                    }
                    newIdx = digitalSeq(index, s);
                    if (newIdx == index){
                        return false;
                    }else {
                        index = newIdx;
                    }
                    return index > len - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String [] cases = {"+100","5e2","-123","3.1416","-1E-16","0123","1 "};
        String [] fCases = {".1","1", "e9", "12e","1a3.14","1.2.3","+-5","12e+5.4"};
//        for (String item: cases){
//            System.out.println(item +":" + isNumber(item));
//        }
        for (String item: fCases){
            System.out.println(item +":" + isNumber(item));
        }
    }
}
