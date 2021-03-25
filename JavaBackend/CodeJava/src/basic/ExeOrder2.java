package basic;

public class ExeOrder2 {
    // 3
    Person person = new Person("Test");
    static{//1
        System.out.println("test static");
    }
    public ExeOrder2() {//4
        System.out.println("test constructor");
    }
    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static{//3-1
        System.out.println("person static");
    }
    public Person(String str) {//3-2, 5-1
        System.out.println("person "+str);
    }
}

class MyClass extends ExeOrder2 {
    // 5
    Person person = new Person("MyClass");
    static{//2
        System.out.println("myclass static");
    }
    public MyClass() {//6
        System.out.println("myclass constructor");
    }
}

