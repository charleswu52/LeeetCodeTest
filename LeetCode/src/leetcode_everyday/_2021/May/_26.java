package leetcode_everyday._2021.May;

import org.junit.Test;

import java.util.Stack;

/**
 * @author WuChao
 * @since 2021/5/26 上午8:10
 */
public class _26 {
    /**
     * 每日一题：2021/5/26
     * 1190. 反转每对括号间的子串
     * 难度: medium
     * <p>
     * 给出一个字符串 s（仅含有小写英文字母和括号）。
     *
     * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
     *
     * 注意，您的结果中 不应 包含任何括号。
     * <p>
     * 示例：
     * 输入：s = "(ed(et(oc))el)"
     * 输出："leetcode"
     *
     * 输入：s = "a(bcdefghijkl(mno)p)q"
     * 输出："apmnolkjihgfedcbq"
     * <p>
     * 数据范围：
     * 0 <= s.length <= 2000
     * s 中只有小写英文字母和括号
     * 我们确保所有括号都是成对出现的
     */

    public String reverseParentheses(String s) {
        Stack<Character> store = new Stack<>();
        StringBuilder res = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '(' && ch != ')' && store.empty()) {
                res.append(ch);
            } else if (ch == '('||(ch!=')'&&!store.empty())) {
                store.push(ch);
            } else {
                temp.setLength(0);
                while (!store.empty() && store.peek() != '(') {
                    temp.append(store.pop());
                }
                store.pop(); // '(' 出栈
                if (store.empty()) {
                    res.append(temp.toString());
                } else {
                    String ss = temp.toString();
                    for (int j = 0; j < ss.length(); j++) {
                        store.push(ss.charAt(j));
                    }

                }
            }
        }
        return res.toString();


    }

    @Test
    public void test() {
        String s = "a(u(love)i)b";
        System.out.println(reverseParentheses(s));
    }
}
