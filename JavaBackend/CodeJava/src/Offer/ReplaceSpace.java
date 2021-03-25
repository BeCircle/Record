package Offer;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/27
 */
public class ReplaceSpace {
    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public static String replaceSpace(StringBuffer str) {
        /**
         * append时，会对array进行扩容，看似只有一次循环，实际性能可能不高
         * */
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 32) {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static String replaceSpace2(StringBuffer str) {
        if (str != null) {
            // 先进行一次循环计算空格个数，可以一次规划数组大小
            int spaceNum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 32) {
                    spaceNum++;
                }
            }
            // 一次新建空间,
            int resLen = str.length() + 2 * spaceNum;
            char[] res = new char[resLen];
            for (int i = str.length() - 1; i >= 0; i--) {
                char c = str.charAt(i);
                if (c == 32) {
                    res[resLen - 1] = '0';
                    res[resLen - 2] = '2';
                    res[resLen - 3] = '%';
                    resLen -= 3;
                } else {
                    res[resLen - 1] = c;
                    resLen--;
                }
            }
            return new String(res);
        }
        return null;
    }

    public static String replaceSpace3(StringBuffer str) {
        /**
         * 只用最少的额外空间
         * */
        if (str == null) {
            return null;
        }
        // 先进行一次循环计算空格个数，可以一次规划数组大小
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 32) {
                spaceNum++;
            }
        }
        // 只构造二倍的空格数作为额外空间
        char[] appendSpace = new char[2 * spaceNum];
        char[] rep = {'%', '2', '0'};
        int appLen = 2 * spaceNum - 1;
        int strLen = str.length() - 1;
        for (int i = strLen; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == 32) {
                for (int j = 2; j >= 0; j--) {
                    if (appLen >= 0) {
                        appendSpace[appLen] = rep[j];
                        appLen--;
                    } else {
                        str.setCharAt(strLen, rep[j]);
                        strLen--;
                    }
                }

            } else {
                if (appLen >= 0) {
                    appendSpace[appLen] = c;
                    appLen--;
                } else {
                    str.setCharAt(strLen, c);
                    strLen--;
                }
            }
        }
        return str.append(appendSpace).toString();
    }

    public static void main(String[] args) {
        StringBuffer testStr = new StringBuffer("We are Happy");
        System.out.println(replaceSpace3(testStr));
    }
}
