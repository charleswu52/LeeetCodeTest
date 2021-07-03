package leetcode_hot100.top20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/7/3 10:17
 */
public class _3 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 3. 无重复字符的最长子串
     * 难度：medium
     * <p>
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * <p>
     * 示例
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * <p>
     * 范围
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */

    /*
    思路1： 滑动窗口（慢速版）
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int res = 0;
        while (right < len) {
            right++;
            if (isUnique(s.substring(left, right))) {
                res = Math.max(res, right - left);
            } else {
                while (!isUnique(s.substring(left, right))) {
                    left++;
                }
            }
        }
        return res;

    }

    public boolean isUnique(String str) {
        Set<Character> set = new HashSet<>();
        for (Character ch : str.toCharArray()) {
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /*
    思路2：滑动窗口（快速版） + HashMap

     */
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int len = s.length();
        int res = 0;
        while (right < len) {
            char cur = s.charAt(right++);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            while (map.get(cur) > 1) {
                char pre = s.charAt(left);
                left++;
                map.put(pre, map.getOrDefault(pre,0) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
