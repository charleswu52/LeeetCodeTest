package leetcode_everyday.Apr;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @since 2021/4/1 上午8:05
 */
public class _1_1006 {
    /**
     * 每日一题：2021/4/1
     * 1006. 笨阶乘
     * 难度: medium
     * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
     * <p>
     * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：
     * 乘法(*)，除法(/)，加法(+)和减法(-)。
     * <p>
     * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：
     * 我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
     * <p>
     * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
     * <p>
     * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
     * <p>
     * 示例：
     * 输入：10
     * 输出：12
     * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     * <p>
     * 数据范围：
     * 1 <= N <= 10000
     * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
     */

    /*
    题意解析：
    更改阶乘运算，原本是连续相乘，现在是将乘法改为 * / + - 四个运算符的循环计算
    原先的阶乘运算：factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1
    更改之后的运算：clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     */

    /**
     * 使用栈模拟
     * 数字从N到1，运算依次是乘除加减，由于运算优先级是先算乘除后算加减，因此将使用栈在从N到1遍历过程中，乘除在计算后入栈，加减直接入栈
     * @param N
     * @return
     */
    public int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N);
        N--;
        int operator = 0;// 循环运算符
        while (N > 0) {
            if (operator % 4 == 0) {// 乘
                stack.push(stack.pop() * N);
            } else if (operator % 4 == 1) { // 除
                stack.push(stack.pop() / N);
            } else if (operator % 4 == 2) { // 加
                stack.push(N); // 不做运算直接入栈
            } else { // 减
                stack.push(-N); // 不做运算，取负号后入栈
            }
            operator++;
            N--;
        }
        // 将栈中所有数字弹出累加求和
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    @Test
    public void test(){
        int N = 5;
        System.out.println(clumsy(N));

    }

}
