package basic;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

// 常量池与缓存类
public class CachePool {
    private static void StringTest(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        String s4 = "ab";
        System.out.println("s3==s4? " + (s3 == s4)); // true

        // 编译期优化成"ab", 可以从生成的class文件反编译得到证实
        String s5 = "a" + "b";
        System.out.println("s3==s5? " + (s3 == s5)); //true

        String s6 = s1 + s2;
        System.out.println("s3==s6? " + (s3 == s6)); //false

        String s7 = new String("ab");
        System.out.println("s3==s7? " + (s3 == s7)); //false

        final String s8 = "a";
        final String s9 = "b";
        String s10 = s8 + s9; // final 表示可以进行编译期优化 "ab"
        System.out.println("s3==s10? " + (s3 == s10));//true
        
    }
    private static  void IntTest(){
        // 缓存池范围内（-128~127），重用对象
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println("i1==i2 "+(i1==i2)); // true
        // 超过缓存池
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println("i3==i4? "+(i3==i4)); // false
        // 堆内新建
        Integer i5 = new Integer(100);
        Integer i6 = new Integer(100);
        System.out.println("i5==i6? "+(i5==i6)); // false
        int i7 = 128;
        int i8 = 128;
        System.out.println("i7==i8? "+(i7==i8)); // true

        Integer i9 = 12;
        Integer i10 = i9;
        i9= 3;
        System.out.println(i9);
        System.out.println(i10);

        Integer i11 = 1200;
        Integer i12 = i11;
        i11 = 1100;
        System.out.println(i11);
        System.out.println(i12);
    }
    public static void main(String[] args) {
        StringTest();
        IntTest();

        ConcurrentHashMap a;
        HashMap b;
        Hashtable c;
    }
}
