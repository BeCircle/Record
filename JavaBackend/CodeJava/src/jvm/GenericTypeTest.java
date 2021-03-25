package jvm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericTypeTest {
    public static void method(List<String> a){
        System.out.println(a.toString());
    }
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "你好");
        map.put("how are you", "你好吗");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you"));
    }
}
