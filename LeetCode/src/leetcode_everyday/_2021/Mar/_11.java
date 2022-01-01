package leetcode_everyday._2021.Mar;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/11 上午8:07
 */
public class _11 {
    /**
     * 每日一题：2021/3/11
     * 227. 基本计算器II
     * 难度: medium
     * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
     * 整数除法仅保留整数部分。
     * <p>
     * 示例:
     * 输入：s = "3+2*2"
     * 输出：7
     * <p>
     * 数据范围：
     * 1 <= s.length <= 3 * 105
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * s 表示一个 有效表达式
     * 表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
     * 题目数据保证答案是一个 32-bit 整数
     */

    public int calculate(String s) {
        s = s.replace(" ", ""); // 先直接干掉表达式中的空格，这样只剩下数字与运算符
        Stack<Integer> operand = new Stack<>();
        int res = 0;
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = 10 * num + (c - '0');
            }
            if (!(c >= '0' && c <= '9') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        operand.push(num);
                        break;
                    case '-':
                        operand.push(-num);
                        break;
                    case '*':
                        operand.push(operand.pop() * num);
                        break;
                    case '/':
                        operand.push(operand.pop() / num);
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        // 遍历栈中的元素，累加求和
        while (!operand.empty()) {
            res += operand.peek();
            operand.pop();
        }
        return res;
    }

    public void _21_3_11() {
        String s = "1+3 * 3-3/2 ";
        System.out.println(calculate(s));
    }
}
