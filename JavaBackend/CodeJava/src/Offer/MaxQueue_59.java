package Offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue_59 {
    Queue<Integer> queue;
    Deque<Integer> deque;
    public MaxQueue_59() {
        this.queue = new LinkedList<>();
        // 单调递减双端队列
        this.deque = new LinkedList<>();
    }


    public int max_value() {
        if (this.deque.size()<1){
            return -1;
        }
        return this.deque.peekFirst();
    }

    public void push_back(int value) {
        this.queue.add(value);
        // 小于当前值的都不影响max的结果
        // 不能移除等于的，存在大小大的情况
        while (this.deque.size()>0&&this.deque.getLast()<value){
            this.deque.pollLast();
        }
        this.deque.add(value);
    }

    public int pop_front() {
        if (this.queue.size()<1){
            return -1;
        }
        int res = this.queue.poll();
        while (this.deque.size()>0 &&this.deque.peek().equals(res)){
            this.deque.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        MaxQueue_59 obj = new MaxQueue_59();
        obj.push_back(2);
        obj.push_back(1);
        System.out.println(obj.max_value());
        System.out.println(obj.pop_front());
    }
}
