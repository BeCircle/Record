package basic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/5
 */
public class LRULHM<K, V> extends LinkedHashMap<K, V> {
    // 基于linkedHashMap实现LRU

    // cacheSize
    private int cacheSize;

    public LRULHM(int cacheSize) {
        // accessOrder=true表示linkedHashMap会将最近访问的节点
        // 利用afterAccess移动到链表的tail
        super(cacheSize + 2, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    // linkedHashMap 插入节点后，调用 removeEldestEntry 判断是否进行移除，
    // 默认是不移除（后面进行扩容），但是支持作为cache定制。
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > cacheSize;
    }

    public static void main(String[] args) {
        LRULHM<Integer, String> cache = new LRULHM<>(8);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");
        // 迭代不会影响顺序
        for (Map.Entry<Integer, String> entry:cache.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
//        cache.put(2, "two");
//        cache.put(3, "three");
        cache.get(3);
        cache.get(2);

        for (Map.Entry<Integer, String> entry:cache.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
