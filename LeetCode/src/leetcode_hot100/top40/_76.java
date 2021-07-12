package leetcode_hot100.top40;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/11 17:07
 */
public class _76 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 76. 最小覆盖子串
     * 难度：hard
     * <p>
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * <p>
     * 示例
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     *
     * 输入：s = "a", t = "a"
     * 输出："a"
     * <p>
     * 范围
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     */

    /*
    思路：滑动窗口 + 哈希表
    使用滑动窗口移动字符s，得到s的子串，对模式串t，和过程中的子串都使用哈希表来存储其中的每个字符及出现的次数，
    当子串中的字符和次数不满足的时候就移动右指针滑动,满足的时候就移动左指针
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (Character c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int n = s.length();
        int left = 0, right = 0;
        int vaild = 0;
        //记录最小覆盖子串的起始索引以及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < n) {
            char ch = s.charAt(right);
            right++;
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).equals(need.get(ch))) {
                    vaild++;
                }
            }
            // 注意！！！是while不是if
            while (vaild == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 移动左端窗口位置
                char d = s.charAt(left);
                left++;
                // 进行窗口内一系列数据的更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        vaild--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
