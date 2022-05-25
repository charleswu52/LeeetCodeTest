package leetcode_everyday._2022.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/5/25 9:15
 */
public class _25_467 {
    /**
     * 每日一题：2022/5/25
     * <p>
     * 467. 环绕字符串中唯一的子字符串
     * <p>
     * 难度：medium
     * <p>
     * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
     * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." .
     * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。
     * <p>
     * 示例
     * <p>
     * 输入: p = "zab"
     * <p>
     * 输出: 6
     * <p>
     * 解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
     * <p>
     * 范围
     * <p>
     * 1 <= p.length <= 105
     * p 由小写英文字母构成
     */

    /*
    思路：动态规划
    dp[α] 表示字符串p中以字符α结尾，且在s中的子串的最长长度
    知道了最长长度，也就知道了不同的子串的个数。
    如何计算 dp[α] 呢？我们可以在遍历 p 时，维护连续递增的子串长度 k。具体来说，遍历到 p[i] 时，如果 p[i] 是 p[i−1] 在字母表中的下一个字母，
    则将 k 加一，否则将 k 置为 1，表示重新开始计算连续递增的子串长度。
    然后，用 k 更新 dp[p[i]] 的最大值。

    遍历结束后，p 中以字符 c 结尾且在 s 中的子串有 dp[c] 个。例如 dp[‘d’]=3 表示子串 “bcd”、“cd” 和 “d”。
     */
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                k++;
            } else {
                k = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();

    }
}
