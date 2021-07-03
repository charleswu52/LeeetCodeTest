package leetcode_everyday.Mar;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/8 上午8:26
 */
public class _8 {
    /**
     * 每日一题：2021/3/8
     * 132. 分割回文串 II
     * 难度: hard
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
     * 返回符合要求的 最少分割次数 。
     *
     * <p>
     * 示例:
     * 输入：s = "aab"
     * 输出：1
     * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     *
     * <p>
     * 数据范围：
     * 1 <= s.length <= 2000
     * s 仅由小写英文字母组成
     *
     */

    /*
    官方题解：
    设 f[i] 表示字符串的前缀 s[0..i] 的最少分割次数。要想得出 f[i] 的值，我们可以考虑枚举 s[0..i] 分割出的最后一个回文串，
    这样我们就可以写出状态转移方程：
    f[i] = min_{0 <=j < i} { f[j] } + 1,  其中 s[j+1..i] 是一个回文串
    即我们枚举最后一个回文串的起始位置 j+1，保证 s[j+1..i] 是一个回文串，那么 f[i] 就可以从 f[j] 转移而来，附加 1 次额外的分割次数。
    注意到上面的状态转移方程中，我们还少考虑了一种情况，即 s[0..i] 本身就是一个回文串。此时其不需要进行任何分割，即：f[i]=0

    那么我们如何知道 s[j+1..i] 或者 s[0..i] 是否为回文串呢？我们可以使用与「131. 分割回文串的官方题解」中相同的预处理方法，
    将字符串 s 的每个子串是否为回文串预先计算出来，即：
    设 g(i,j) 表示 s[i..j] 是否为回文串，那么有状态转移方程：
        当i>=j时，g(i,j)是回文串，这时候是一个空串或者长度为1（i=j）
        其他情况下，当其首尾字符相同且 s[i+1..j−1] 为回文串时，g(i,j)是一个回文串
    这样一来，我们只需要 O(1) 的时间就可以判断任意 s[i..j] 是否为回文串了。

    通过动态规划计算出所有的 f 值之后，最终的答案即为 f[n−1]，其中 n 是字符串 s 的长度。
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n]; // 判断字符串s的子串是否是一个回文串
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], true);
        }

        // 判断s的子串是否是回文串,用到了题解中的状态转移方程
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];// 动态规划数组
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);    //动态规划方程
                    }
                }
            }
        }
        return f[n - 1];

    }
    public void _21_3_8(){
        String s = "aab";
        System.out.println(minCut(s));

    }
}
