package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/14 上午8:10
 */
public class _14 {
    /**
     * 每日一题：2021/4/14
     * 208. 实现 Trie (前缀树)
     * 难度: medium
     * <p>
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
     * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
     *
     * 请你实现 Trie 类：
     *
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
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
     *
     * <p>
     * 数据范围：
     *
     * 1 <= word.length, prefix.length <= 2000
     * word 和 prefix 仅由小写英文字母组成
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
     *
     */

    /*
    前缀树的实现
     */
    class Trie {
        // next 数组中保存了对当前节点而言下一个可能出现的所有字符的链接。因此可以通过一个父节点来预知它所有子结点的值
        private boolean isEnd; // 表示当前节点是不是最后一个节点
        private Trie[] next;

        /** Initialize your data structure here. */
        public Trie() {
            isEnd = false;
            next = new Trie[26];

        }

        /** Inserts a word into the trie.
         * 从根节点的子结点开始与Word的第一个字符进行匹配，一直匹配到前缀链上没有对应的字符，
         * 然后开始不断的字符这时开始不断开辟新的节点，直到插入完Word的最后一个字符
         * */
        public void insert(String word) {
            Trie trie = this;
            for (char c : word.toCharArray()) {
                if (trie.next[c - 'a'] == null) { // 铅坠链没有对应的字符了
                    trie.next[c - 'a'] = new Trie();
                }
                trie = trie.next[c - 'a'];
            }
            trie.isEnd = true;

        }

        /** Returns if the word is in the trie.
         * 从根结点的子结点开始，一直向下匹配即可，如果出现结点值为空就返回 false，
         * 如果匹配到了最后一个字符，那只需判断 node->isEnd即可。
         * */
        public boolean search(String word) {
            Trie trie = this;
            for (char c : word.toCharArray()) {
                trie = trie.next[c - 'a'];
                if (trie == null) {
                    return false;
                }
            }
            return trie.isEnd;


        }

        /** Returns if there is any word in the trie that starts with the given prefix.
         * 和 search 操作类似，只是不需要判断最后一个字符结点的isEnd，因为既然能匹配到最后一个字符，那后面一定有单词是以它为前缀的。
         * */
        public boolean startsWith(String prefix) {
            Trie trie = this;
            for (char c : prefix.toCharArray()) {
                trie = trie.next[c - 'a'];
                if (trie == null) {
                    return false;
                }
            }
            return true;
        }
    }
}
