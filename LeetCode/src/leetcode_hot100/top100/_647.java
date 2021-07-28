package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/28 18:20
 */
public class _647 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 647. 回文子串
     * 难度：medium
     * <p>
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     * <p>
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     *
     *
     * <p>
     * 提示：
     * 输入的字符串长度不会超过 1000 。
     */

    /*
    思路1：中心扩展法
    枚举每个可能的回文中心，用两个指针分别向左右两边扩展，当两个指针分别指向相同的时候就扩展，否则停止扩展

    选取中心点：单个字符 和 两个字符的，这两种情况，对字符串 s, 单个字符有 s.length种情况，两个字符有 s.len-1种情况
    所以中心点共有 2* s。len-1 种情况

     */
    public int countSubstrings(String s) {
        int res = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // left和right指针和中心点的关系是？
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                res++;
                left--;
                right++;
            }

        }
        return res;

    }

    /*
    思路2：动态规划
    定义 dp[i][j] 表示字符串 s 在[i,j]区间的子串是否是一个回文串
    状态转移方程：当 s[i]==s[j] && (j-i<2 || dp[i+1][j-1]) 时 dp[i][j] 为TRUE
     */

    public int countSubstrings2(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }

}
