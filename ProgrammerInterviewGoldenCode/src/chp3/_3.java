package chp3;

import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/4/10 下午2:30
 */
public class _3 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.03. 堆盘子
     * 难度: easy
     * <p>
     * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
     * 请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
     * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。
     * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
     *
     * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
     * <p>
     * 例如：
     *  输入：
     * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
     * [[1], [1], [2], [1], [], []]
     *  输出：
     * [null, null, null, 2, 1, -1]
     * <p>
     * 数据范围：
     */

    // 二维链表实现
    class StackOfPlates {
        LinkedList<LinkedList<Integer>> stacks;
        int cap;

        public StackOfPlates(int cap) {
            this.cap = cap;
            this.stacks = new LinkedList<>();
        }

        public void push(int val) {
            if (cap <= 0) {
                return;
            }
            if (stacks.isEmpty() || stacks.getLast().size()>=cap) {
                stacks.addLast(new LinkedList<>());

            }
            stacks.getLast().add(val);

        }

        public int pop() {
            int ans = -1;
            if (stacks.isEmpty()) {
                return ans;
            }
            ans = stacks.getLast().removeLast();
            if (stacks.getLast().isEmpty()) {
                stacks.removeLast();
            }
            return ans;

        }

        public int popAt(int index) {
            int ans = -1;
            if (stacks.isEmpty() || stacks.size() - 1 < index) {
                return ans;
            }
            ans = stacks.get(index).removeLast();
            if (stacks.get(index).isEmpty()) {
                stacks.remove(index);
            }
            return ans;

        }
    }

}
