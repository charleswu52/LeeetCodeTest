package leetcode_everyday._2021.Apr;

/**
 * @author WuChao
 * @since 2021/4/23 上午8:12
 */
public class _21 {
    /**
     * 每日一题：2021/4/21
     * 91. 解码方法
     * 难度: medium
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     *
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     *
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     *
     * <p>
     * 示例：
     * 输入：s = "0"
     * 输出：0
     * 解释：没有字符映射到以 0 开头的数字。
     * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
     * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
     *
     * <p>
     * 数据范围：
     * 1 <= s.length <= 100
     * s 只包含数字，并且可能包含前导零。
     */

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];

            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];

    }
}
