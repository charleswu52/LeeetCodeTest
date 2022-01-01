package leetcode_everyday._2021.Nov;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/11/17 10:59
 */
public class _17_318 {
    /**
     * 每日一题：2021/11/17
     * <p>
     * 318. 最大单词长度乘积
     * <p>
     * 难度：medium
     * <p>
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
     * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
     *
     * 输出: 16
     *
     * 解释: 这两个单词为 "abcw", "xtfn"。
     * <p>
     * 范围
     * <p>
     * 2 <= words.length <= 1000
     * 1 <= words[i].length <= 1000
     * words[i]仅包含小写字母
     */

    /*
    思路：位运算 模拟

     */
    public int maxProduct(String[] words) {
        // 将每个string转换成整数
        // 用哈希表 保存每个转换成的整数和对应的string的长度
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int ints = 0, len = word.length();
            for (int i = 0; i < len; i++) {
                int t = word.charAt(i) - 'a';
                ints |= (1 << t);
            }
            if (!map.containsKey(ints) || map.get(ints) < len) {
                map.put(ints, len);
            }

        }
        int res = 0;
        for (int a : map.keySet()) {
            for (int b : map.keySet()) {
                if ((a & b) == 0) {
                    res = Math.max(res, map.get(a) * map.get(b));
                }
            }
        }
        return res;

    }
}
