package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/10/19 19:06
 */
public class _19_211 {
    /**
     * 每日一题：2021/10/19
     * <p>
     * 211. 添加与搜索单词 - 数据结构设计
     * <p>
     * 难度：medium
     * <p>
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * <p>
     * 实现词典类 WordDictionary ：
     *
     * WordDictionary() 初始化词典对象
     *
     * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
     *
     * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。
     *
     * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     * <p>
     * 示例：
     * <p>
     * 1 <= word.length <= 500
     * addWord 中的 word 由小写英文字母组成
     * search 中的 word 由 '.' 或小写英文字母组成
     * 最多调用 50000 次 addWord 和 search
     */

    /*
    思路：使用前缀树（字典树）

     */


    class WordDictionary {
        private Trie root;

        public WordDictionary() {
            root = new Trie();

        }

        public void addWord(String word) {
            root.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, Trie node) {
            if (index == word.length()) {
                return node.isEnd();
            }
            char ch = word.charAt(index);
            if (Character.isLetter(ch)) {
                int childIndex = ch - 'a';
                Trie child = node.getChildren()[childIndex];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            } else {
                for (int i = 0; i < 26; i++) {
                    Trie child = node.getChildren()[i];
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Trie{
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
