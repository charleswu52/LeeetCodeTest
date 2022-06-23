package leetcode_everyday._2022.Jun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/6/23 11:17
 */
public class _23_30 {
    /**
     * 每日一题：2022/6/23
     * <p>
     * 30. 串联所有单词的子串
     * <p>
     * 难度：hard
     * <p>
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。
     * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     * <p>
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
     * <p>
     * 示例
     * <p>
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * <p>
     * 输出：[0,9]
     * <p>
     * 解释：
     * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * words[i] 由小写英文字母组成
     */

    /*
    思路：滑动窗口 + 哈希表
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int lenS = s.length(), n = words.length, lenW = words[0].length();
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        out:
        for (int i = 0; i + n * lenW <= lenS; i++) {
            Map<String, Integer> cur = new HashMap<>();
            String sub = s.substring(i, i + n * lenW);
            for (int j = 0; j < sub.length(); j+=lenW) {
                String item = sub.substring(j, j + lenW);
                if (!map.containsKey(item)) {
                    continue out;
                }
                cur.put(item, cur.getOrDefault(item, 0) + 1);
            }
            if (cur.equals(map)) {
                list.add(i);
            }
        }
        return list;


    }
}
