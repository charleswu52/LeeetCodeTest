package leetcode_everyday._2021.Dec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/12/28 下午1:31
 */
public class _28_472 {
    /**
     * 每日一题：2021/12/28
     * <p>
     * 472. 连接词
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
     * <p>
     * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
     * <p>
     * 输出：["girl","student"]
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 10^4
     * 0 <= words[i].length <= 1000
     * words[i] 仅由小写字母组成
     * 0 <= sum(words[i].length) <= 10^5
     **/


    /*
    思路：前缀树 + 深度优先遍历
    将所有单词按照单词的长度进行排序，然后遍历每个单词，搜索前缀树判断其是否是一个连接词，如果是就将其加入到结果集中，
    如果不是连接词就将该单词加入字典树.

    判断一个单词是不是连接词的做法是在字典树中执行深度优先搜索：从该单词的第一个字符开始，在字典树中依次搜索每个字符对应的结点：
        如果一个字符对应的结点是单词的结尾，则找到了一个更短的单词，从该字符的后一个字符开始搜索下一个更短的单词；
        如果一个字符对应的结点在字典树中不存在，则当前的搜索结果失败，回到上一个单词的结尾继续搜索
     */
    Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;


    }

    public boolean dfs(String word, int start) {
        if (word.length() == start) {
            return true;
        }
        Trie node = trie;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
            if (node.isWord) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }

    class Trie {
        Trie[] children;
        boolean isWord;

        public Trie() {
            this.children = new Trie[26];
            this.isWord = false;
        }
    }
}
