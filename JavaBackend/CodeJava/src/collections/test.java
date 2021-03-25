package collections;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>(16,0.75f,false);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");
        for (String name : map.values()) {
            System.out.print(name);
        }
        System.out.println("\n-----------------");
        //access 操作
//        map.get("2");
//        map.get("1");
        map.put("2", "b");
        for (String name : map.values()) {
            System.out.print(name);
        }
    }
}
