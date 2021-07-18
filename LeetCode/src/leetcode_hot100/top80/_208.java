package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/18 9:41
 */
public class _208 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 208. 实现 Trie (前缀树)
     * 难度：medium
     * <p>
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，
     * 例如自动补完和拼写检查。
     *
     * 请你实现 Trie 类：
     *
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     *
     * <p>
     * 示例：
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     *
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     *
     * <p>
     * 数据范围:
     * 1 <= word.length, prefix.length <= 2000
     * word 和 prefix 仅由小写英文字母组成
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
     */

    class Trie {

        /**
         * Initialize your data structure here.
         */
        private Trie[] children;
        private boolean isWord;

        public Trie() {
            this.children = new Trie[26];
            this.isWord = false;

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie trie = this;
            for (char ch : word.toCharArray()) {
                if (trie.children[ch - 'a'] == null) {
                    trie.children[ch - 'a'] = new Trie();
                }
                trie = trie.children[ch - 'a'];
            }
            trie.isWord = true;

        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie trie = this;
            for (char ch : word.toCharArray()) {
                trie = trie.children[ch - 'a'];
                if (trie == null) {
                    return false;
                }
            }
            return trie.isWord;

        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie trie = this;
            for (char ch : prefix.toCharArray()) {
                trie = trie.children[ch - 'a'];
                if (trie == null) {
                    return false;
                }
            }
            return true;

        }
    }
}
