package chp3;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/4/10 下午3:07
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.04. 化栈为队
     * 难度: easy
     * <p>
     * 实现一个MyQueue类，该类用两个栈来实现一个队列。
     * <p>
     * 例如：
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // 返回 1
     * queue.pop();   // 返回 1
     * queue.empty(); // 返回 false
     *
     * <p>
     * 说明：
     * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
     *
     */


    /*
    经典的问题：使用两个栈来模拟一个队列
    其中一个为入栈存储push的元素，一个为出栈的元素存储
    入栈时直接向入栈里push即可，出栈时先检查出栈是否为空，是的话先一次性把入栈的所有元素转移到出栈，然后再将出栈的栈顶出栈即可
    出栈不为空直接出栈
     */

    class MyQueue {


        Stack<Integer> stackIn;
        Stack<Integer> stackOut;
        /** Initialize your data structure here. */
        public MyQueue() {
            stackIn = new Stack<>();
            stackOut= new Stack<>();

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stackIn.push(x);


        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int ans = -1;
            if (stackOut.empty()) {
                if (stackIn.empty()) {
                    return ans;
                } else {
                    while (!stackIn.empty()) {
                        stackOut.push(stackIn.pop());
                    }
                    return stackOut.pop();
                }

            } else {
                ans =stackOut.pop();
            }
            return ans;

        }

        /** Get the front element. */
        public int peek() {
            int ans = -1;
            if (stackOut.empty()) {
                if (stackIn.empty()) {
                    return ans;
                } else {
                    while (!stackIn.empty()) {
                        stackOut.push(stackIn.pop());
                    }
                    return stackOut.peek();
                }

            } else {
                ans =stackOut.peek();
            }
            return ans;

        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stackIn.empty() && stackOut.empty();

        }
    }
}
