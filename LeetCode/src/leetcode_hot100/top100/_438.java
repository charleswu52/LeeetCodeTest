package leetcode_hot100.top100;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/27 10:29
 */
public class _438 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 438. 找到字符串中所有字母异位词
     * 难度：medium
     * <p>
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指字母相同，但排列不同的字符串。
     *
     * <p>
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     *
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词
     * <p>
     * 1 <= s.length, p.length <= 3 * 104
     * s 和 p 仅包含小写字母
     */

    /*
    思路1：暴力做法
    滑动窗口 + 字符串排序
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        List<Integer> res = new ArrayList<>();
        if (pLen > sLen) {
            return res;
        }
        char[] chars = p.toCharArray();
        Arrays.sort(chars);
        String s1 = new String(chars);
        int left = 0, right = left + pLen;
        while (right <= sLen) {
            String substring = s.substring(left, right);
            char[] chars1 = substring.toCharArray();
            Arrays.sort(chars1);
            String sub = new String(chars1);
            if (s1.equals(sub)) {
                res.add(left);
            }
            right++;
            left++;
        }
        return res;
    }

    /*
    思路2：滑动窗口 + 哈希表
     */
    public List<Integer> findAnagrams2(String s, String p) {
        // 使用 哈希表 记录窗口中的字符和需要凑齐的字符
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            return res;
        }

        for (char ch : p.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char charRight = s.charAt(right);
            right++;
            if (need.containsKey(charRight)) {
                window.put(charRight, window.getOrDefault(charRight, 0) + 1);
                if (window.get(charRight).equals(need.get(charRight))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否需要收缩
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char charLeft = s.charAt(left);
                left++;
                if (need.containsKey(charLeft)) {
                    if (window.get(charLeft).equals(need.get(charLeft))) {
                        valid--;
                    }
                    window.put(charLeft, window.get(charLeft) - 1);
                }
            }
        }
        return res;

    }


    }
