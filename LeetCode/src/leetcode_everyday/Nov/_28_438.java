package leetcode_everyday.Nov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/11/28 10:27
 */
public class _28_438 {
    /**
     * 每日一题：2021/11/28
     * 438. 找到字符串中所有字母异位词
     * 难度：medium
     * <p>
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * 示例 1：
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     * <p>
     * 范围
     * 1 <= s.length, p.length <= 3 * 104
     * s 和 p 仅包含小写字母
     */
    /*
    思路：
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int left = 0, right = -1;
        int[] freq_s = new int[26];
        int[] freq_p = new int[26];
        for (int i = 0; i < p.length(); i++) {
            freq_p[p.charAt(i) - 'a']++;
            freq_s[s.charAt(++right)- 'a']++;
        }
        if (Arrays.equals(freq_s, freq_p)) {
            res.add(left);
        }
        while (right < s.length() - 1) {
            freq_s[s.charAt(++right) - 'a']++;
            freq_s[s.charAt(left++) - 'a']--;
            if (Arrays.equals(freq_s, freq_p)) {
                res.add(left);
            }
        }
        return res;
    }
}
