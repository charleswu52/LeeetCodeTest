package leetcode_everyday._2021.Oct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/10/8 9:26
 */
public class _8_187 {
    /**
     * 每日一题：2021/10/8
     * <p>
     * 187. 重复的DNA序列
     * <p>
     * 难度：medium
     * <p>
     * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
     * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     * <p>
     * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * <p>
     * 输出：["AAAAACCCCC","CCCCCAAAAA"]
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "AAAAAAAAAAAAA"
     * <p>
     * 输出：["AAAAAAAAAA"]
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 10^5
     * s[i] 为 'A'、'C'、'G' 或 'T'
     */


    /**
     * 思路： 滑动哈希
     *
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        int len = s.length();
        if (len < 10) {
            return res;
        }
        for (int i = 0; i <= len - 10; i++) {
            String substring = s.substring(i, i + 10);
            countMap.put(substring, countMap.getOrDefault(substring, 0) + 1);
            if (countMap.get(substring) == 2) {
                res.add(substring);
            }
        }
        return res;

    }
}
