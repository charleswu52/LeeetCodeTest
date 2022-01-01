package leetcode_everyday._2021.Mar;

import org.junit.Test;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/20 上午7:57
 */
public class _20 {
    /**
     * 每日一题：2021/3/20
     * 150. 逆波兰表达式求值
     * 难度: medium
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 说明：
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * <p>
     * 示例:
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * <p>
     * 数据范围：
     * 1 <= tokens.length <= 104
     * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
     */

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.length() > 1) {
                stack.push(Integer.parseInt(s));
            } else {
                char ch = s.charAt(0);
                int left, right;
                switch (ch) {
                    case '+':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left + right);
                        break;
                    case '-':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left - right);
                        break;
                    case '*':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left * right);
                        break;
                    case '/':
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(left / right);
                        break;
                    default:
                        stack.push(ch - '0');
                }
            }
        }
        return stack.peek();

    }
    @Test
    public void test(){
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));

    }

}
