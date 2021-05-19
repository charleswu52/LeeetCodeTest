package chp8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/19 上午10:36
 */
public class _9 {
    /**
     * 程序员面试金典(version 6) - 面试题  08.09. 括号
     * 难度: medium
     * <p>
     * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 例如，给出 n = 3，生成结果为：
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     */

    int n;
    List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        this.n = n;
        dfs("", 0);
        return res;

    }

    //回溯中的选择，左括号还是右括号。
    char[] pairs = new char[]{'(', ')'};

    public void dfs(String path, int value) {
        if (path.length() == n * 2) {
            if (!res.contains(path) && value == 0) {
                res.add(path);
            }
            return;
        }
        for (int i = 0; i < pairs.length; i++) {
            int newValue = i == 0 ? value + 1 : value - 1;
            if (newValue < 0) {
                break;
            }
            dfs(path + pairs[i], newValue);
        }

    }

    @Test
    public void test() {
        int n = 3;
        System.out.println(generateParenthesis(n));
    }
}
