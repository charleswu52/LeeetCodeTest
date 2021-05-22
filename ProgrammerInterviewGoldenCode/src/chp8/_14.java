package chp8;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/22 上午11:21
 */
public class _14 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.14. 布尔运算
     * 难度: medium
     * <p>
     * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
     * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     *
     * <p>
     * 测试数据：
     * 输入: s = "1^0|0|1", result = 0
     * 输出: 2
     * 解释: 两种可能的括号方法是
     * 1^(0|(0|1))
     * 1^((0|0)|1)
     *
     * <p>
     * 输入: s = "0&0&0&1^1|0", result = 1
     *
     * 输出: 10
     * <p>
     * 数据范围：
     * 运算符的数量不超过 19 个
     */

    /*
    思路：动态规划
    设dp[s][e][r]为从索引s到索引e值为r的方案数。那么，我们可以拿一个指针k(从s遍历到e - 1)，将区间[s, e]分成两个部分，
    [s, k] 和 [k + 2, e]。其中k+1的位置是运算符。同时，由于是布尔运算，因此左右两部分的结果页要么是0，要么是1。
    组合也就是四种情况，{00, 01, 10, 11}。
    然后判断这四种情况通过k+1位置的运算符算出来的值是不是能够等于r(dp[s][e][r]中的r)。能等的话，就将左右两部分的方案数相乘即可。
     */

    private char[] arr;
    private int[][][] dp;

    /**
     * 计算两个值的bool结果
     * @param val1
     * @param val2
     * @param operator
     * @return
     */
    private int getBoolAns(int val1, int val2, char operator) {
        switch (operator) {
            case '&':
                return val1 & val2;
            case '|':
                return val1 | val2;
            case '^':
                return val1 ^ val2;
        }
        return val1 & val2;
    }

    /**
     * 递归求方案数
     * @param start
     * @param end
     * @param result
     * @return
     */
    private int rec(int start, int end, int result) {
        if (start == end) {
            return arr[start] - '0' == result ? 1 : 0;
        }
        if (dp[start][end][result] != -1) {
            return dp[start][end][result];
        }
        int ansCount = 0;
        for (int k = start; k < end; k+=2) {
            char operator = arr[k + 1];
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (getBoolAns(i, j, operator) == result) {
                        ansCount += rec(start, k, i) * rec(k + 2, end, j);
                    }
                }
            }
        }
        dp[start][end][result] = ansCount;
        return ansCount;
    }

    public int countEval(String s, int result) {
        arr = s.toCharArray();
        int len = s.length();
        dp = new int[len][len][2];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return rec(0, len - 1, result);

    }
}
