package leetcode_hot100.top20;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/3 11:41
 */
public class _5 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 5. 最长回文子串
     * 难度：medium
     * <p>
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * <p>
     * 示例
     * <p>
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 输入：s = "a"
     * 输出："a"
     *
     * 输入：s = "ac"
     * 输出："a"
     * <p>
     * 范围
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     */

    /*
    思路1：双重循环遍历 + 多次回文串判断
     */

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int maxLength = 0;
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (j - i > maxLength && isHuiwen(s.substring(i, j))) {
                    maxLength = j - i;
                    res = s.substring(i, j);
                }
            }
        }
        return res;


    }

    public boolean isHuiwen(String str) {
        if (str.length() < 2) {
            return true;
        }
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /*
    思路2：动态规划
    动态规划方程 dp[i][j] 表示 从i到j的子串是否是回文串
    特殊情况 dp[i][i] = true
    dp[i][j] = false 当 i > j 时
    dp[i][j] =
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        // 枚举子串长度
        for (int l = 2; l <= len; l++) {
            // 子串左边界
            for (int i = 0; i < len; i++) {
                // 右边界
                int j = i + l - 1;
                if (j >= len) {
                    break;
                }

                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


        @Test
    public void test() {
        String s = "bb";
        System.out.println(longestPalindrome(s));
    }
}
