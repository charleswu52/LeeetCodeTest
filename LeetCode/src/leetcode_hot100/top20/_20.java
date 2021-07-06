package leetcode_hot100.top20;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author WuChao
 * @create 2021/7/5 10:32
 */
public class _20 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 20. 有效的括号
     * 难度：easy
     * <p>
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * <p>
     * 示例
     * <p>
     * 输入：s = "()"
     * 输出：true
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * <p>
     * 范围
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     */

    public boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            switch (cur) {
                case '(':
                case '[':
                case '{':
                    stack.push(cur);
                    break;
                case ')':
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '(') {
                            stack.poll();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '[') {
                            stack.poll();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.isEmpty()) {
                        if (stack.peek() == '{') {
                            stack.poll();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();

    }
}
