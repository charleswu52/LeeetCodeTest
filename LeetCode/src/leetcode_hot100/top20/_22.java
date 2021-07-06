package leetcode_hot100.top20;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/5 11:04
 */
public class _22 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 22. 括号生成
     * 难度：medium
     * <p>
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 示例
     * <p>
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * <p>
     * 输入：n = 1
     * 输出：["()"]
     * <p>
     * 范围
     * 1 <= n <= 8
     *
     */

    /*
    思路： 使用深度有限遍历 或者 广度有限遍历
     */

    /*
    思路1：深度优先遍历
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        // 使用 DFS 遍历
        dfs("", n, n, res);
        return res;



    }

    public void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 递归终止的时候将它加入到结果集中
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }


}
