package leetcode_hot100.top60;

import java.util.Stack;

/**
 * @author WuChao
 * @create 2021/7/16 15:57
 */
public class _155 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 155. 最小栈
     * 难度：easy
     * <p>
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     * <p>
     * 进阶：
     * 不适用辅助栈
     *
     *
     * <p>
     * 示例：
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     *
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     *
     * <p>
     * 数据范围:
     * pop、top 和 getMin 操作总是在 非空栈 上调用。
     */

    /*
    思路：常见的思路是使用辅助栈，但是空间复杂度是O(n)
    改进思路:用一个空间实现 O(1)

   还是使用一个栈记录，以及一个min记录栈中的最小值
   当需要更新栈最小值的时候，需要把之前的最小值先入一次栈，然后再入新加的栈，
   出栈的时候当当前栈顶等于最小值的时候需要出两次栈,然后把最小值更新
     */

    class MinStack {

        /**
         * initialize your data structure here.
         */
        int min;
        Stack<Integer> minStack;
        public MinStack() {
            this.min = Integer.MAX_VALUE;
            this.minStack = new Stack<>();
        }

        public void push(int val) {
            if (val <= min) {
                minStack.push(min);
                min = val;
            }
            minStack.push(val);

        }

        public void pop() {
            if (minStack.pop() == min) {
                min = minStack.pop();
            }

        }

        public int top() {
            return minStack.peek();

        }

        public int getMin() {
            return min;
        }
    }
}
