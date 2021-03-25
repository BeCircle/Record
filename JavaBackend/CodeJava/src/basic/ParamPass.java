package basic;

import org.junit.Assert;
import org.junit.Test;

public class ParamPass {
    class Person{
        String name;
        int age;

        public void setAge(int age) {
            this.age = age;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    public String change(String s, int i, StringBuffer sb, Person p){
        /**
         * 对象传递引用的副本，修改引用位置的值，能影响调用函数；
         * new创建了新的空间，新的引用，不影响调用函数
         * string是不可变对象，修改就和new相同，创建了新的空间，得到新的引用
         * */
        s="123"; // 不影响父函数
        i=3; // 不影响父函数
        sb.append("woshi");// 影响父函数
        p.setAge(100); // 不影响父函数
        sb = new StringBuffer("sbsb"); // 不影响父函数
        p = new Person("bb",44); // 不影响父函数
        return s;
    }

    @Test
    public void testChange(){
        StringBuffer sb = new StringBuffer("buff");
        String s = "aaa";
        int i = 1;
        Person p = new Person("aa",12);
        i=2;
        change(s,i,sb,p);
        System.out.println(s);
        System.out.println(i);
        System.out.println(sb.toString());
        System.out.println(p);
    }

    @Test
    public void testDoubleReturn(){
        double r1 = divide(3,2);
        double r2 = divide2(3,2);
        System.out.println(r1);
        System.out.println(r2);
    }
    public double divide(int a, int b){
        return a/b;
    }

    public double divide2(int a, int b){
        return (double) a/b;
    }
}
