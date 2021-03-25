package collections;

import java.util.HashSet;
import java.util.Objects;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Person> hs = new HashSet<>();
        //创建Person对象
        Person p1 = new Person("张三","10");
        Person p2 = new Person("李四","11");
        Person p3 = new Person("李四","11");
        //添加集合元素
        hs.add(p1);
        hs.add(p2);
        hs.add(p3);
        //遍历集合
        for (Person person : hs) {
            System.out.println(person);
        }
    }
}

class Person{
    private String name;
    private String ID;

    public Person(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    /**
     * equal 和hashcode都必须重写
     * 不写equal则重复元素hash相同，不equal，存储于同一个bin的链表
     * 不写hash则hash值不同，直接存储在不同的bin。
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                ID.equals(person.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ID);
    }
}