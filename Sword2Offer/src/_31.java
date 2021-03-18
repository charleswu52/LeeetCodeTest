import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/18 上午9:02
 */
public class _31 {
    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     * 难度: medium
     * <p>
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
     * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * <p>
     * 示例：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     *
     * <p>
     * 数据范围：
     * 0 <= pushed.length == popped.length <= 1000
     * 0 <= pushed[i], popped[i] < 1000
     * pushed 是 popped 的排列。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        if (len == 0) {
            return true;
        }
        int i = 0, j = 0;
        Stack<Integer> store = new Stack<>();
        while (i < len) {
            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                if (store.empty()) {
                    store.push(pushed[i++]);
                } else {
                    if (store.peek() == popped[j]) {
                        store.pop();
                        j++;
                    } else {
                        store.push(pushed[i++]);
                    }
                }
            }
        }
        while (j < len) {
            if (store.empty()) {
                return false;
            }
            if (store.peek() == popped[j]) {
                store.pop();
                j++;
            } else {
                return false;
            }
        }
        return store.empty();
    }

    /**
     * 更加直观的模拟方式，同样的也使用一个辅助栈来完成
     * 入栈操作：按照压栈顺序执行
     * 出栈操作：每次入栈后，循环判断“栈顶元素 == 弹出序列的当前元素” 是否成立，将符合弹出序列顺序的栈顶元素全部弹出。
     */
    public boolean validateStackSequences2(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);// 入栈
            while (!stack.empty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.empty();
    }


    public void sword2Offer_31() {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(pushed, popped));
        System.out.println(validateStackSequences2(pushed, popped));
    }

}
