package leetcode_everyday._2022.May;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/5/31 8:44
 */
public class _31_114 {
    /**
     * 每日一题：2022/5/31
     * <p>
     * 剑指 Offer II 114. 外星文字典
     * <p>
     * 难度：hard
     * <p>
     * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
     * <p>
     * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
     * <p>
     * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
     * <p>
     * 字符串 s 字典顺序小于 字符串 t 有两种情况：
     * <p>
     * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
     * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
     * <p>
     * 示例
     * <p>
     * 输入：words = ["wrt","wrf","er","ett","rftt"]
     * <p>
     * 输出："wertf"
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] 仅由小写英文字母组成
     */

    /*
    思路：拓扑排序 + 深度优先搜索
    https://leetcode.cn/problems/Jf1JuT/solution/wai-xing-wen-zi-dian-by-leetcode-solutio-to66/
     */
    // 定义两个状态
    static final int VISITING = 1, VISITED = 2;
    Map<Character, List<Character>> edges = new HashMap<>();
    Map<Character, Integer> states = new HashMap<>();
    boolean valid = true;
    char[] order;
    int idx;

    public String alienOrder(String[] words) {
        int len = words.length;
        for (String word : words) {
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                char ch = word.charAt(j);
                edges.putIfAbsent(ch, new ArrayList<>());
            }
        }
        for (int i = 1; i < len && valid; i++) {
            addEdge(words[i - 1], words[i]);
        }
        order = new char[edges.size()];
        idx = edges.size() - 1;
        Set<Character> letterSet = edges.keySet();
        for (char u : letterSet) {
            if (!states.containsKey(u)) {
                dfs(u);
            }
        }
        if (!valid) {
            return "";
        }
        return new String(order);
    }

    public void addEdge(String before, String after) {
        int len1 = before.length(), len2 = after.length();
        int length = Math.min(len1, len2);
        int index = 0;
        while (index < length) {
            char c1 = before.charAt(index), c2 = after.charAt(index);
            if (c1 != c2) {
                edges.get(c1).add(c2);
                break;
            }
            index++;
        }
        if (index == length && len1 > len2) {
            valid = false;
        }
    }

    public void dfs(char u) {
        states.put(u, VISITING);
        List<Character> adjacent = edges.get(u);
        for (char v : adjacent) {
            if (!states.containsKey(v)) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (states.get(v) == VISITING) {
                valid = false;
                return;
            }
        }
        states.put(u, VISITED);
        order[idx] = u;
        idx--;
    }
}
