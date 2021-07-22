package leetcode_hot100.top80;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/22 上午11:11
 */
public class _301 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 301. 删除无效的括号
     * 难度：hard
     * <p>
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * <p>
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * <p>
     * 示例 1：
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * <p>
     * 示例 2：
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * <p>
     * 示例 3：
     * 输入：s = ")("
     * 输出：[""]
     * <p>
     * 提示：
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     * <p>
     * 进阶：
     * 1 <= s.length <= 25
     * s 由小写英文字母以及括号 '(' 和 ')' 组成
     * s 中至多含 20 个括号
     */

    /*
    思路 ：回溯 （dfs）


     */

    int len;
    char[] charArray;
    Set<String> validExpressions = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        this.len = s.length();
        this.charArray = s.toCharArray();
        // 第一步：遍历一次，计算多余的左右括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // 遇到右括号的时候，须要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }

        // 第二步，回溯算法，尝试每一种可能的删除操作
        StringBuilder stringBuilder = new StringBuilder();

        dfs(0, 0, 0, leftRemove, rightRemove, stringBuilder);
        return new ArrayList<>(this.validExpressions);

    }


    /**
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    public void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char character = charArray[index];
        // 可能的操作1：删除当前遍历到的字符
        if (character == '(' && leftRemove > 0) {
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        // 可能的操作 2：保留当前遍历到的字符
        path.append(character);
        if (character != '(' && character != ')') {
            // 如果不是括号，继续深度优先遍历
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);


    }
}
