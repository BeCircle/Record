package basic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class CloneDemo implements Cloneable {
    @Test
    public  void testClone() {
        Student c1 = new Student("student1", 24);
        Student c2 = (Student) c1.clone();
        Assert.assertEquals(c1,c2);
    }
}

class Student implements Cloneable{
    String name;
    int age;
    public Student(String n, int a){
        name = n;
        age  = a;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // 不覆盖时，调用父类的clone，也就是object的clone
    public Object clone(){
        Object o = null;
        try {
            o = super.clone();
        }catch (CloneNotSupportedException  e){
            System.out.println(e.toString());
        }
        return o;
    }
}