package leetcode_everyday._2021.Mar;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/10 上午8:37
 */
public class _10 {
    /**
     * 每日一题：2021/3/10
     * 224. 基本计算器
     * 难度: hard
     * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
     * <p>
     * 示例:
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     * <p>
     * 数据范围：
     * 1 <= s.length <= 3 * 10^5
     * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * s 表示一个有效的表达式
     */

    /*
    题目解析：
    由于字符串除了数字与括号外，只有加号和减号两种运算符。因此，如果展开表达式中所有的括号，则得到的新表达式中，数字本身不会发生变化，
    只是每个数字前面的符号会发生变化。
    考虑使用一个取值为 {−1,+1} 的整数 sign 代表「当前」的符号。根据括号表达式的性质，它的取值：
        与字符串中当前位置的运算符有关；
        如果当前位置处于一系列括号之内，则也与这些括号前面的运算符有关：每当遇到一个以 − 号开头的括号，则意味着此后的符号都要被「翻转」。
     */

    /**
     * 思路：使用栈
     * 由于只有 + - 两种符号，因此只需要维护一个运算栈即可，其中栈顶元素记录了当前位置所处的每个括号所「共同形成」的符号。
     * 例如，对于字符串 1+2+(3-(4+5))：
     *  扫描到 1+2 时，由于当前位置没有被任何括号所包含，则栈顶元素为初始值 +1；
     *  扫描到 1+2+(3 时，当前位置被一个括号所包含，该括号前面的符号为 + 号，因此栈顶元素依然 +1；
     *  扫描到 1+2+(3-(4 时，当前位置被两个括号所包含，分别对应着 + 号和 - 号，由于 + 号和 − 号合并的结果为 − 号，因此栈顶元素变为 −1。
     * 到栈 ops 之后， sign 的取值就能够确定了：如果当前遇到了 + 号，则更新  sign←ops.top()；
     *                                      如果遇到了遇到了 − 号，则更新 sign←−ops.top()。
     * 然后，每当遇到 ( 时，都要将当前的 sign 取值压入栈中；每当遇到 ) 时，都从栈中弹出一个元素。
     * 这样，我们能够在扫描字符串的时候，即时地更新 ops 中的元素。
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> operand = new Stack<>();
        operand.push(1);
        int sign = 1;

        int res = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = operand.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -operand.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                operand.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                operand.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * num;
            }
        }
        return res;


    }

    public void _21_3_10(){
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s));
    }

}
