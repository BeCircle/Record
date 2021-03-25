package jvm.ClassInit;


public class ClassInitTest {
    public static void main(String[] args){
        // 通过子类引用父类静态字段只会触发父类初始化，可能会触发子类的加载和验证（取决于虚拟机实现）。
//        System.out.println(SubClass.value);
        // 没有触发SuperClass的初始化
//        SuperClass[] sca = new SuperClass[10];

        System.out.println(ConstClass.HELLOWORLD);
    }
}