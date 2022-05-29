package leetcode_everyday._2022.May;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2022/5/29 9:20
 */
public class _28_1021 {
    /**
     * 每日一题：2022/5/28
     * <p>
     * 1021. 删除最外层的括号
     * <p>
     * 难度：easy
     * <p>
     * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
     * <p>
     * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
     * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
     * <p>
     * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
     * <p>
     * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
     * <p>
     * 示例
     * <p>
     * 输入：s = "(()())(())"
     * <p>
     * 输出："()()()"
     * <p>
     * 解释：
     * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
     */

    /*
    思路： 栈
     */
    public String removeOuterParentheses(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')') {
                deque.pop();
            }
            if (!deque.isEmpty()) {
                res.append(s.charAt(i));
            }
            if (s.charAt(i) == '(') {
                deque.offer('(');
            }
        }
        return res.toString();

    }
}
