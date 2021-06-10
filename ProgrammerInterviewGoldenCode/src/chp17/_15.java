package chp17;

import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/6/8 下午1:30
 */
public class _15 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.15. 最长单词
     * 难度: medium
     * <p>
     * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
     * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
     * <p>
     * 示例:
     * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
     * 输出： "dogwalker"
     * 解释： "dogwalker"可由"dog"和"walker"组成。
     * <p>
     * 数据范围：
     * 0 <= len(arr) <= 100000
     * 0 <= k <= min(100000, len(arr))
     */

    /*
    思路：使用字典树 + 回溯
     */
    Trie trie = new Trie();
    TrieNode root = trie.getRoot();
    public String longestWord(String[] words) {
        String res = "";
        List<String> wordList = Arrays.asList(words);
        wordList.sort((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o2.length() - o1.length());

        // 构造字典树
        for (String word : wordList) {
            trie.insert(word);
        }
        for (String word : wordList) {
            TrieNode cur = trie.getRoot();
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                // "排除掉自己组成自己，当前遍历到的字符是个单词，且剩余部分可以再次被切分
                if (i < n - 1 && cur.children[c - 'a'].isEnd && canSplitToWord(word.substring(i + 1))) {
                    return word;
                }
                cur = cur.children[c - 'a'];
            }
        }
        return res;


    }

    /**
     * 当前单词可以被切分，在wordList中找到
     * @param remain
     * @return
     */
    public boolean canSplitToWord(String remain) {
        if ("".equals(remain)) {
            return true;
        }
        TrieNode cur = root;
        for (int i = 0; i < remain.length(); i++) {
            char c = remain.charAt(i);  // 拿到当前字符
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            if (cur.children[c - 'a'].isEnd && canSplitToWord(remain.substring(i + 1))) {
                return true;
            }
            cur = cur.children[c - 'a'];
        }
        return false;
    }

    // 字典树
    class Trie{
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public TrieNode getRoot() {
            return root;
        }

        /**
         * 向字典树中插入一个单词
         * @param word
         */
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }
    }
    class TrieNode{
        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }

}
