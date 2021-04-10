package chp3;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/4/10 下午3:32
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.05. 栈排序
     * 难度: medium
     * <p>
     * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
     * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
     * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
     * <p>
     * 例如：
     *  输入：
     * ["SortedStack", "push", "push", "peek", "pop", "peek"]
     * [[], [1], [2], [], [], []]
     *  输出：
     * [null,null,null,1,null,2]
     *
     *
     * <p>
     * 说明：
     * 栈中的元素数目在[0, 5000]范围内。
     *
     */

    class SortedStack {

        Stack<Integer> stack;
        Stack<Integer> temp;

        public SortedStack() {
            stack = new Stack<>();
            temp = new Stack<>();
        }

        public void push(int val) {
            if (stack.empty()) {
                stack.push(val);
            } else {
                if (val <= stack.peek()) {
                    stack.push(val);
                } else {
                    while (!stack.empty() && stack.peek() <= val) {
                        temp.push(stack.pop());
                    }
                    stack.push(val);
                    while (!temp.empty()) {
                        stack.push(temp.pop());
                    }
                }
            }

        }

        public void pop() {
            if (!stack.empty()) {
                stack.pop();

            }

        }

        public int peek() {
            if (!stack.empty()) {

                return stack.peek();
            }
            return -1;
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
