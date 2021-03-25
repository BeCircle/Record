package Company;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/26
 */
public class MySet {
    // 初始容量
    private int capacity = 1000;
    private int size = 0;
    private byte[] store = new byte[capacity+1];
    // 结果码
    public static final int INDEX_OUT_OF_RANGE = -1;
    public static final int OK = 0;
    // 未初始化
    public static final byte UN_ININT = 0;
    // 初始化
    public static final byte ININT = 1;

    MySet(int capacity) {
        this.capacity = capacity;
        // 初始化数组
        for (int i = 0; i < this.capacity; i++) {
            this.store[i] = UN_ININT;
        }
    }

    MySet() {
        // 初始化数组
        for (int i = 0; i < this.capacity; i++) {
            this.store[i] = UN_ININT;
        }
    }

    // 插入
// return: -1 index out of range, 0 success;
    public int put(int val) {
        if (val < 0 || val > capacity) {
            return INDEX_OUT_OF_RANGE;
        }
        if (this.store[val] == UN_ININT) {
            // 没有在集合里
            this.store[val] = ININT;
            this.size++;
        }
        return OK;
    }

    // 删除
// return: -1 index out of range, 0 success
    public int delete(int val) {
        if (val < 0 || val > capacity) {
            return INDEX_OUT_OF_RANGE;
        }
// 在集合里
        if (this.store[val] == ININT) {
            this.store[val] = UN_ININT;
            this.size--;
        }
        return OK;
    }

    /* 查找
     * return: index out of range, -1；in set, 1; not in set 0;
     */
    public int contains(int val) {
        if (val < 0 || val > capacity) {
            return INDEX_OUT_OF_RANGE;
        }
        return this.store[val];
    }

    // 返回容量
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
//         MySet set = new MySet();
//         int a = set.put(10);
//         int b = set.put(1001);
//         int c = set.put(-1);
//         int d = set.delete(10);
//         int f = set.delete(12);

        int a = 2;
        int b = 1;
        int c = 3;
        System.out.println(a^b);
        System.out.println((a ^ b << c));
        System.out.println(b << c);


    }
}
