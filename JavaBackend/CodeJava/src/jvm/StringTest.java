package jvm;

public class StringTest {
    public static void test1(){
        // 创建两个对象，一个在常量池，一个是堆区，堆区中的给a
        String a=new String("1");
        System.out.println("a: "+System.identityHashCode(a));
        // intern时常量池已经存在，返回之前创建的给b，b!=a
        String b=a.intern();
        System.out.println("b: "+System.identityHashCode(b));
        // 从常量池取
        String c="1";
        System.out.println("c: "+System.identityHashCode(c));
    }
    public static void test2() {
        // 创建常量池1和堆区1两个对象
        String s = new String("1");
        System.out.println("s: "+System.identityHashCode(s));
        // 没有实际作用
        s.intern();
        // 从常量池取，s2!=s1
        String s2 = "1";
        System.out.println("s2: "+System.identityHashCode(s2));

        // 常量池已有1，堆区创建两个1对象，一个11对象
        String s3 = new String("1") + new String("1");
        System.out.println("s3: "+System.identityHashCode(s3));
        // 将堆区11对象放入常量池，1.7+直接放引用
        s3.intern();
        // 从常量池取，s4==s3。
        String s4 = "11";
        System.out.println("s4: "+System.identityHashCode(s4));
    }
    public static void main(String[] args) {
        test2();
    }
}
