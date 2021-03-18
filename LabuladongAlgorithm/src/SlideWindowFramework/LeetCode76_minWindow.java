package SlideWindowFramework;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/3/18 下午1:13
 */
public class LeetCode76_minWindow {
    /**
     * 学习滑动窗口的例子
     * 76. 最小覆盖子串
     * 难度: hard
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * <p>
     * 数据范围：
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     */

    /**
     * 套经典滑动窗口模板
     * @param s 主字符串
     * @param t 查找的字符串
     * @return
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        //记录最小覆盖子串的起始索引以及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // ch 是将移入窗口的字符
            char ch = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch,0) + 1);
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }

            // 判断窗口是否需要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0)-1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, len+start);
    }

    @Test
    public void test() {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));

    }
}
