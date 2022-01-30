package leetcode_everyday._2022.Jan;

import com.sun.javafx.collections.MappingChange;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/1/30 9:35
 */
public class _30_884 {
    /**
     * 每日一题：2022/1/30
     * <p>
     * 884. 两句话中的不常见单词
     * <p>
     * 难度：easy
     * <p>
     * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
     * <p>
     * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
     * <p>
     * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
     * <p>
     * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
     * <p>
     * 输出：["sweet","sour"]
     * <p>
     * 范围
     * <p>
     * 1 <= s1.length, s2.length <= 200
     * s1 和 s2 由小写英文字母和空格组成
     * s1 和 s2 都不含前导或尾随空格
     * s1 和 s2 中的所有单词间均由单个空格分隔
     */

    /*
    思路
     */
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> strings = new HashMap<>();
        for (String s : s1.split(" ")) {
            strings.put(s, strings.getOrDefault(s, 0) + 1);
        }
        for (String s : s2.split(" ")) {
            strings.put(s, strings.getOrDefault(s, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : strings.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
