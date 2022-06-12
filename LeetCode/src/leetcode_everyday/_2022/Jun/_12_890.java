package leetcode_everyday._2022.Jun;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/6/12 9:18
 */
public class _12_890 {
    /**
     * 每日一题：2022/6/12
     * <p>
     * 890. 查找和替换模式
     * <p>
     * 难度：medium
     * <p>
     * 你有一个单词列表 words 和一个模式 pattern，你想知道 words 中的哪些单词与模式匹配。
     * <p>
     * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
     * <p>
     * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
     * <p>
     * 返回 words 中与给定模式匹配的单词列表。
     * <p>
     * 你可以按任何顺序返回答案。
     *
     * <p>
     * 示例
     * <p>
     * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
     * <p>
     * 输出：["mee","aqq"]
     * <p>
     * 解释：
     * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
     * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
     * 因为 a 和 b 映射到同一个字母。
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 50
     * 1 <= pattern.length = words[i].length <= 20
     */

    /*
    思路：哈希表 + 字符串模拟
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        String p = word2Str(pattern);
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (word2Str(word).equals(p)) {
                res.add(word);
            }
        }
        return res;


    }

    private String word2Str(String word) {
        Map<Character, Integer> letter = new HashMap<>();
        int id = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (!letter.containsKey(word.charAt(i))) {
                letter.put(word.charAt(i), id++);
            }
            stringBuilder.append(letter.get(word.charAt(i)));

        }
        return stringBuilder.toString();
    }

}
