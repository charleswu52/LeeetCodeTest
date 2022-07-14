package leetcode_everyday._2022.Jul;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/7/14 11:28
 */
public class _14_745 {
    /**
     * 每日一题：2022/7/14
     * <p>
     * 745. 前缀和后缀搜索
     * <p>
     * 难度：hard
     * <p>
     * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
     * <p>
     * 实现 WordFilter 类：
     * <p>
     * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
     * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
     * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
     * <p>
     * 示例
     * <p>
     * 输入
     * ["WordFilter", "f"]
     * [[["apple"]], ["a", "e"]]
     * <p>
     * 输出
     * [null, 0]
     * <p>
     * 解释
     * WordFilter wordFilter = new WordFilter(["apple"]);
     * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 104
     * 1 <= words[i].length <= 7
     * 1 <= pref.length, suff.length <= 7
     * words[i]、pref 和 suff 仅由小写英文字母组成
     * 最多对函数 f 执行 104 次调用
     */

    class WordFilter {

        //  定义 Trie 的节点
        class TrieNode{
            TrieNode[] trieNodes = new TrieNode[26];
            List<Integer> indexes = new ArrayList<>();
        }
        // 定义两个trie树
        TrieNode tr1 = new TrieNode(), tr2 = new TrieNode();

        void add(TrieNode node, String s, int idx, boolean isTurn) {
            int n = s.length();
            node.indexes.add(idx);
            for (int i = isTurn ? n - 1 : 0; i >= 0 && i < n; i += isTurn ? -1 : 1) {
                int cur = s.charAt(i) - 'a';
                if (node.trieNodes[cur] == null) {
                    node.trieNodes[cur] = new TrieNode();
                }
                node = node.trieNodes[cur];
                node.indexes.add(idx);
            }
        }

        int query(String a, String b) {
            int n = a.length(), m = b.length();
            TrieNode node = tr1;
            for (int i = 0; i < n; i++) {
                int cur = a.charAt(i) - 'a';
                if (node.trieNodes[cur] == null) {
                    return -1;
                }
                node = node.trieNodes[cur];
            }
            List<Integer> l1 = node.indexes;
            node = tr2;
            for (int i = m - 1; i >= 0; i--) {
                int cur = b.charAt(i) - 'a';
                if (node.trieNodes[cur] == null) {
                    return -1;
                }
                node = node.trieNodes[cur];
            }
            List<Integer> l2 = node.indexes;
            n = l1.size();
            m = l2.size();
            for (int i = n - 1, j = m - 1; i >= 0 && j >= 0; ) {
                if (l1.get(i) > l2.get(j)) {
                    i--;
                } else if (l1.get(i) < l2.get(j)) {
                    j--;
                } else {
                    return l1.get(i);
                }

            }
            return -1;
        }

        public WordFilter(String[] words) {
            int n = words.length;
            for (int i = 0; i < n; i++) {
                add(tr1, words[i], i, false);
                add(tr2, words[i], i, true);
            }

        }

        public int f(String pref, String suff) {
            return query(pref, suff);

        }
    }
}
