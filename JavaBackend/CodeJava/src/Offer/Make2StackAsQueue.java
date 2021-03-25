package Offer;

import java.util.Stack;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/16
 */
public class Make2StackAsQueue {
    // 利用两个栈模拟队列
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int val){
        // stack1 用于push数据
        stack1.push(val);
    }

    public int pop(){
        // 将stack1 中的数据倒入stack2，使先进的数据到栈顶
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int res = stack1.pop();
        // 将剩余的数据倒回，使之与新加入的节点保持顺序
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return res;
    }

    public int pop2(){
        // 倒入到stack2的数据不再倒回stack1，逐渐从stack2消耗
        // stack2空时再从stack1倒入
        if (stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
