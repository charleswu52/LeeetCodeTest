import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/3/24 上午11:08
 */
public class _48 {
    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * 难度: medium
     * <p>
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * <p>
     * 示例：
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     *
     * <p>
     * 数据范围：
     * s.length <= 40000
     */

    /*
    思路分析：
    使用滑动窗口的模板框架
    参考--labuladong算法小抄 滑动窗口框架
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;// 记录结果
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            while (window.get(ch) > 1) {
                char d = s.charAt(left);
                left ++;
                // 窗口内数据更新
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            // 更新答案
            res = Math.max(res, right - left);

        }
        return res;


    }

    @Test
    public void test() {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));

    }


}
