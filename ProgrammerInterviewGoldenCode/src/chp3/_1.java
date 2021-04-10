package chp3;

/**
 * @author WuChao
 * @since 2021/4/10 下午1:00
 */
public class _1 {
    /**
     * 程序员面试金典(version 6) - 面试题 03.01. 三合一
     * 难度: easy
     * <p>
     * 三合一。描述如何只用一个数组来实现三个栈。
     * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
     * 构造函数会传入一个stackSize参数，代表每个栈的大小。
     * <p>
     * 例如：
     *  输入：
     * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
     * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
     *  输出：
     * [null, null, null, 1, -1, -1, true]
     * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
     * <p>
     * 数据范围：
     */

    /*
    用数组实现三个栈的实现思路
     */

    // 三栈合一的实现
    class TripleInOne {
        int[] store; //  存储栈的数组
        int size;    // 每个栈的大小
        int[] position;   // 存储每个栈的指针



        // 初始化
        public TripleInOne(int stackSize) {
            this.size = stackSize;
            this.store = new int[3 * size];
            this.position = new int[3];
            for (int i = 0; i < 3; i++) {
                position[i] = i * size;
            }
        }

        public void push(int stackNum, int value) {
            int index = position[stackNum];// 获取要操作的栈的栈顶指针
            if (index < (stackNum + 1) * size) {    // 当前栈没满
                store[index] = value;
                position[stackNum]++;   // 栈顶指针移动
            }

        }

        public int pop(int stackNum) {
            int index = position[stackNum];// 获取要操作的栈的栈顶指针
            if (index > stackNum * size) {
                position[stackNum]--;
                return store[index - 1];
            } else {    // 栈空
                return -1;
            }



        }

        public int peek(int stackNum) {
            int index = position[stackNum];// 获取要操作的栈的栈顶指针
            if (index > stackNum * size) {
                return store[index - 1];
            } else {    // 栈空
                return -1;
            }

        }

        public boolean isEmpty(int stackNum) {
            return position[stackNum] == stackNum * size;
        }
    }

}
