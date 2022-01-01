package leetcode_everyday._2021.Mar;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/3/17 上午7:39
 */
public class _17 {
    /**
     * 每日一题：2021/3/17
     * 115. 不同的子序列
     * 难度: medium
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * <p>
     * 示例:
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * <p>
     * 数据范围：
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     */

    public boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '}':
                    if (stack.empty()) {
                        return false;
                    } else {
                        if (stack.peek() != '{') {
                            return false;
                        } else {
                            stack.pop();
                        }
                    }
                    break;
                case ')':
                    if (stack.empty()) {
                        return false;
                    } else {
                        if (stack.peek() != '(') {
                            return false;
                        } else {
                            stack.pop();
                        }
                    }
                    break;
                case ']':
                    if (stack.empty()) {
                        return false;
                    } else {
                        if (stack.peek() != '[') {
                            return false;
                        } else {
                            stack.pop();
                        }
                    }
                    break;
                default:
                    stack.push(ch);
                    break;
            }
        }
        return stack.empty();
    }
    public void _21_3_17(){
        String s = "()[]{}";
        System.out.println(isValid(s));

    }
}
