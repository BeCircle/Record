package basic;

import java.util.LinkedList;
import java.util.List;

public class ExeOrder extends Base {
    static {//2
        System.out.println("test static");
    }
    public ExeOrder() {//5
        System.out.println("test constructor");
    }
    public int lastRemaining(int n, int m) {
        // 用链表模拟循环删除
        List<Integer> datas = new LinkedList<>();
        for(int i=0; i<n;i++){
            datas.add(i);
        }
        int idx = 0;
        while(datas.size()>1){
            idx = idx-1+m%datas.size();
            datas.remove(idx);
        }
        return datas.get(0);
    }
    public static void main(String[] args) {
        //3
        System.out.println("main method");
        new ExeOrder();
    }
}

class Base {
    static {//1
        System.out.println("base static");
    }
    public Base() {//4
        System.out.println("base constructor");
    }
}