import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/11 上午9:09
 */
public class _09 {
    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * 难度: easy
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
     * <p>
     * 示例：
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     * <p>
     * 数据范围：
     * 1 <= values <= 10000
     * 最多会对 appendTail、deleteHead 进行 10000 次调用
     */

    class CQueue {
        private List<Integer> queue;

        public CQueue() {
            this.queue = new ArrayList<>();
        }

        public void appendTail(int value) {
            queue.add(value);
        }

        public int deleteHead() {
            if (queue.size() == 0) {
                return -1;
            } else {
                int res = queue.get(0);
                queue.remove(0);
                return res;
            }
        }
    }

    // 其他的，典型使用两个栈来处理，一个栈用于插入元素，另一个用于删除元素
    class CQueue2 {
        private Stack<Integer> inStack;
        private Stack<Integer> outStack;


        public CQueue2() {
            this.inStack = new Stack<>();
            this.outStack = new Stack<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.empty()) {
                while (!inStack.empty()) {
                    outStack.push(inStack.pop());
                }
            }
            if (outStack.empty()) {
                return -1;
            } else {
                return outStack.pop();
            }
        }

    }

    public void sword2Offer_09() {
        CQueue2 obj = new CQueue2();
        obj.appendTail(2);
        System.out.println(obj.deleteHead());


    }
}
