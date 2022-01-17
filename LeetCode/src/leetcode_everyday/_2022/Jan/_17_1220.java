package leetcode_everyday._2022.Jan;

/**
 * @author WuChao
 * @create 2022/1/17 11:20
 */
public class _17_1220 {
    /**
     * 每日一题：2022/1/17
     * <p>
     * 1220. 统计元音字母序列的数目
     * <p>
     * 难度：hard
     * <p>
     * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
     * <p>
     * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
     * 每个元音 'a' 后面都只能跟着 'e'
     * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
     * 每个元音 'i' 后面 不能 再跟着另一个 'i'
     * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
     * 每个元音 'u' 后面只能跟着 'a'
     * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * <p>
     * 输出：10
     * <p>
     * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
     * <p>
     * 范围
     */

    /*
    动态规划：设定 a 代表 以a开头的字符串数量（其他几个以此类推），然后每次滚动更新当前数量
     */
    public int countVowelPermutation(int n) {
        int mod = (int) 1e9 + 7;
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 2; j <= n; j++) {
            long aa = e % mod;
            long ee = (a + i) % mod;
            long ii = (a + e + o + u) % mod;
            long oo = (i + u) % mod;
            long uu = a % mod;
            a = aa;
            e = ee;
            i = ii;
            o = oo;
            u = uu;
        }
        return (int) ((a + e + i + o + u) % mod);
    }
}
