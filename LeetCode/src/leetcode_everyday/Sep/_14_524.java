package leetcode_everyday.Sep;

import java.util.List;

/**
 * @author WuChao
 * @create 2021/9/14 19:45
 */
public class _14_524 {
    /**
     * 每日一题：2021/9/14
     * 524. 通过删除字母匹配到字典里最长单词
     * 难度：medium
     * <p>
     * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
     *
     * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
     * <p>
     * 示例 1：
     * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
     * 输出："apple"
     *
     * 示例 2：
     * 输入：s = "abpcplea", dictionary = ["a","b","c"]
     * 输出："a"
     * <p>
     * 1 <= s.length <= 1000
     * 1 <= dictionary.length <= 1000
     * 1 <= dictionary[i].length <= 1000
     * s 和 dictionary[i] 仅由小写英文字母组成
     */

    /*
    思路：双指针
    初始化两个指针 i 和 j，分别指向 t（字典中的字符串） 和 s 的初始位置。每次贪心地匹配，匹配成功则 i 和 j 同时右移，匹配 t 的
    下一个位置，匹配失败则 j 右移，i 不变，尝试用 s 的下一个字符匹配 t。

    最终如果 i 移动到 t 的末尾，则说明 t 是 s 的子序列。
     */

    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == t.length()) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
}
