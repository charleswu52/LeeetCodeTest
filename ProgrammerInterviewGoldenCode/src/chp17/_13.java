package chp17;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/7 下午12:09
 */
public class _13 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.13. 恢复空格
     * 难度: medium
     * <p>
     * <p>
     * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
     * 像句子"I reset the computer. It still didn’t boot!"已经变成了
     * "iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。
     * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
     * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
     * <p>
     * 注意：本题相对原题稍作改动
     * <p>
     * 示例:
     * 输入：
     * dictionary = ["looked","just","like","her","brother"]
     * sentence = "jesslookedjustliketimherbrother"
     * 输出： 7
     * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
     * <p>
     * 数据范围：
     * 0 <= len(sentence) <= 1000
     * dictionary中总字符数不超过 150000。
     * 你可以认为dictionary和sentence中只包含小写字母。
     */

    /*
    题目解析 & 思路：
    题目的意思实际是 给定字符串，尽可能多地匹配字典内的单词，即求最少的未匹配数

    分析：拿到题目，先想贪心能不能做，显然不行。比如给定字符串：abcdef，字典：["abcd", "ab", "def"]，贪心匹配是 abcd ef，有2个字符未匹配。
    显然有更优匹配策略： ab c def ，只有 1 个字符未匹配。所以这题需要用动态规划来解决
    （怎么思考一个题目可不可以用 dp 来解决，先假设 dp 表达含义，再想转移方程，很多时候自然而然就出来了）。

    思路1：动态规划
    dp[i] 表示字符串前i个字符的最少未匹配数
    状态转移方程：
    假设当前我们已经考虑完了前 i 个字符了，对于前 i + 1 个字符对应的最少未匹配数：
        第 i + 1 个字符未匹配，则 dp[i + 1] = dp[i] + 1，即不匹配数加 1;
        遍历前 i 个字符，若以其中某一个下标 idx 为开头、以第 i + 1 个字符为结尾的字符串正好在词典里，
           则 dp[i] = min(dp[i], dp[idx]) 更新 dp[i]。
     */
    public int respace2(String[] dictionary, String sentence) {
        Set<String> store = new HashSet<String>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx = 0; idx < i; idx++) {
                if (store.contains(sentence.substring(idx, i))) {
                    dp[i] = Math.min(dp[i], dp[idx]);
                }
            }
        }
        return dp[n];
    }

    /*
    改进思路2： Trie树 + dp
    上述有个过程求dp[i+1] 的时候 会 遍历前i个字符，逐个判断以idx 为开头，i+1 结尾的字符是否在字典里，这有很多重复操作。
    这一步可以利用字典树来加速，通过字典树我们可以查询以第 i + 1 个字符为结尾的单词有哪些（构建字典树时将单词逆序插入即可）。
     */
    public int respace3(String[] dictionary, String sentence) {
        // 先构建字典树
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx : trie.search(sentence, i-1)) {
                dp[i] = Math.min(dp[i], dp[idx]);
            }
        }
        return dp[n];

    }

    /**
     * Trie 树的定义
     */
    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }


        /**
         * 将单词逆序插入到字典树
         *
         * @param word
         */
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = word.length() - 1; i > -1; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.isEndWord = true;
        }

        /**
         * 查找字符串中以endPos结尾的单词，返回这些单词的开头下标
         *
         * @param sentence
         * @param endPos
         * @return
         */
        public List<Integer> search(String sentence, int endPos) {
            List<Integer> integers = new ArrayList<>();
            TrieNode cur = root;
            for (int i = endPos; i > -1; i--) {
                int c = sentence.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    break;
                }
                cur = cur.children[c];
                if (cur.isEndWord) {
                    integers.add(i);
                }
            }
            return integers;
        }
    }

    class TrieNode {
        boolean isEndWord;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }
    }

    public int respace(String[] dictionary, String sentence) {

        int n = sentence.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            for (String word : dictionary) {
                if (word.length() <= i) {
                    if (word.equals(sentence.substring(i - word.length(), i))) {
                        dp[i + 1] = Math.min(dp[i], dp[i - word.length()]);
                    }
                }
            }
        }
        return dp[n];
    }
}
