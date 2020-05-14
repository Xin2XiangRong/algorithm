package algorithm.cs.test;

import java.util.Stack;

/**
 * @Copyright: www.hikvision.com Inc. All rights reserved.
 * @ClassName: MinStack
 * @Description: TODO
 * @author: chaishuai
 * @date: 2020/4/21 14:41
 * @version: V1.0.0
 */
public class MinStack {

    //记录全部数据
    private Stack<Integer> dataStack;
    //记录每放进一个数据后的最小值
    private Stack<Integer> helperStack;
    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        helperStack = new Stack<>();
    }

    public void push(int x) {
        //向数据栈中放入数据
        dataStack.push(x);
        //向辅助栈中放入数据
        if (helperStack.isEmpty()) {
            helperStack.add(x);
        } else {
            if (helperStack.peek() >= x) {
                helperStack.add(x);
            }
        }
    }

    public void pop() {
        Integer pop = dataStack.pop();
        if (pop.equals( helperStack.peek()) ) {
            helperStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return helperStack.peek();
    }
}

