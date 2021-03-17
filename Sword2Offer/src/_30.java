import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/17 上午10:29
 */
public class _30 {
    /**
     * 剑指 Offer 30. 包含min函数的栈
     * 难度: easy
     * <p>
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     * <p>
     * 示例：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.min();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.min();   --> 返回 -2.
     *
     * <p>
     * 数据范围：
     * 各函数的调用总次数不超过 20000 次
     */

    class MinStack {

        /**
         * initialize your data structure here.
         */
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int x) {
            if (stack.empty()) {
                stack.push(x);
                minStack.push(x);
            } else {
                if (x < minStack.peek()) {
                    minStack.push(x);
                } else {
                    minStack.push(minStack.peek());
                }
                stack.push(x);
            }
        }

        public void pop() {
            if (!stack.empty()) {
                stack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
}
