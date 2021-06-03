package chp16;

import org.junit.Test;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/6/3 下午2:16
 */
public class _26 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.26. 计算器
     * 难度: medium
     * <p>
     * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
     *
     * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * <p>
     * 示例：
     * 输入: "3+2*2"
     * 输出: 7
     *
     * 输入: " 3/2 "
     * 输出: 1
     *
     * 输入: " 3+5 / 2 "
     * 输出: 5
     * <p>
     * 数据范围：
     */

    /*
    思路：单栈实现
    类型：简单面试题
     */
    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        char op = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                op = c;
            }
        }
        int res = 0;
        for (Integer integer : stack) {
            res += integer;
        }
        return res;

    }

    @Test
    public void test() {
        String s = "3+5/2";
        System.out.println(calculate(s));
    }
}
