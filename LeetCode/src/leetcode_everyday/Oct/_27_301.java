package leetcode_everyday.Oct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/10/27 8:36
 */
public class _27_301 {
    /**
     * 每日一题：2021/10/27
     * <p>
     * 301. 删除无效的括号
     * <p>
     * 难度：hard
     * <p>
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * <p>
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     *
     * <p>
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * <p>
     * nums1 中数字 x 的下一个更大元素是指 x 在nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * <p>
     * 示例1：
     * <p>
     * 输入：s = "()())()"
     * <p>
     * 输出：["(())()","()()()"]
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 25
     * s 由小写英文字母以及括号 '(' 和 ')' 组成
     * s 中至多含 20 个括号
     */

    /*
    将括号的「是否合法」转化为「数学判定」

     */

    Set<String> set = new HashSet<>();
    int n,max, len;
    String s;
    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.n = s.length();
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
        }
        this.max = Math.min(left, right);
        dfs(0, "", 0);
        return new ArrayList<>(this.set);

    }

    public void dfs(int u, String cur, int score) {
        if (score < 0 || score > this.max) {
            return;
        }
        if (u == this.n) {
            if (score == 0 && cur.length() >= this.len) {
                if (cur.length() > this.len) {
                    this.set.clear();
                }
                this.len = cur.length();
                this.set.add(cur);
            }
            return;
        }
        char c = this.s.charAt(u);
        if (c == '(') {
            dfs(u + 1, cur + String.valueOf(c), score + 1);
            dfs(u + 1, cur, score);
        } else if (c == ')') {
            dfs(u + 1, cur + String.valueOf(c), score - 1);
            dfs(u + 1, cur, score);
        } else {
            dfs(u + 1, cur + String.valueOf(c), score);
        }
    }
}
