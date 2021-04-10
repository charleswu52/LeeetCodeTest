package chp3;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/4/10 下午2:18
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.02. 栈的最小值
     * 难度: easy
     * <p>
     * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
     * 执行push、pop和min操作的时间复杂度必须为O(1)。
     * <p>
     * 例如：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     * <p>
     * 数据范围：
     */

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            this.minStack = new Stack<>();
            this.stack = new Stack<>();

        }

        public void push(int x) {
            this.stack.push(x);
            if (!minStack.empty()) {
                if (minStack.peek() < x) {
                    minStack.push(minStack.peek());
                } else {
                    minStack.push(x);
                }
            }else{
                minStack.push(x);
            }

        }

        public void pop() {
            stack.pop();
            minStack.pop();

        }

        public int top() {
            return stack.peek();

        }

        public int getMin(){
            if (!minStack.empty()) {
                return minStack.peek();
            }
            return -1;

        }
    }
}
