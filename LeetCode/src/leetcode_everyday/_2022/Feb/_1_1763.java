package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/1 8:43
 */
public class _1_1763 {
    /**
     * 每日一题：2022/2/1
     * <p>
     * 1763. 最长的美好子字符串
     * <p>
     * 难度：easy
     * <p>
     * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，
     * 因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
     * <p>
     * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。
     * 如果不存在美好子字符串，请你返回一个空字符串。
     *
     * <p>
     * 输入：s = "YazaAay"
     * <p>
     * 输出："aAa"
     * <p>
     * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
     * "aAa" 是最长的美好子字符串。
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 100
     * s 只包含大写和小写英文字母。
     */

    /*
    思路：滑动窗口
    1.枚举窗口内只含有 m <= 26 种字符，对原串做滑动窗口。
    2.维护窗口 [left, right] 只含有 m 种字符，同时统计各类字符大小写是否出现。若都出现则表明区间 [left, right] 即为满足题意子串。
     */
    public String longestNiceSubstring(String s) {
        int[] freq = new int[26];
        int types = 0;
        for (char ch : s.toCharArray()) {
            freq[Character.toLowerCase(ch) - 'a']++;
            types += 1 == freq[Character.toLowerCase(ch) - 'a'] ? 1 : 0;
        }
        int start = 0, end = -1;
        for (int m = 0; m <= types; m++) {
            int[] ints = helper(s, m);
            if (ints[1] - ints[0] > end - start) {
                start = ints[0];
                end = ints[1];
            }
        }
        return s.substring(start, end+1);
    }

    public int[] helper(String s, int m) {
        int len = s.length();
        int[] lowerCase = new int[26];
        int[] upperCase = new int[26];
        int start = 0, end = -1;
        for (int left = 0, right = 0, types = 0; right < len; right++) {
            if (s.charAt(right) >= 'a' && s.charAt(right) <= 'z') {
                lowerCase[s.charAt(right) - 'a']++;
                types += (lowerCase[s.charAt(right) - 'a'] + upperCase[s.charAt(right) - 'a']) == 1 ? 1 : 0;
            } else {
                upperCase[s.charAt(right) - 'A']++;
                types += (lowerCase[s.charAt(right) - 'A'] + upperCase[s.charAt(right) - 'A']) == 1 ? 1 : 0;
            }
            while (m < types) {
                if (s.charAt(left) >= 'a' && s.charAt(left) <= 'z') {
                    types -= (lowerCase[s.charAt(left) - 'a'] + upperCase[s.charAt(left) - 'a']) == 1 ? 1 : 0;
                    lowerCase[s.charAt(left++) - 'a']--;
                } else {
                    types -= (lowerCase[s.charAt(left) - 'A'] + upperCase[s.charAt(left) - 'A']) == 1 ? 1 : 0;
                    upperCase[s.charAt(left++) - 'A']--;
                }
            }
            int cnt = 0;
            for (int i = 0; i < 26; i++) {
                cnt += (lowerCase[i] != 0 && upperCase[i] != 0) ? 1 : 0;
                if (cnt == types && right - left > end - start) {
                    start = left;
                    end = right;
                }
            }
        }
        return new int[]{start, end};
    }
}
