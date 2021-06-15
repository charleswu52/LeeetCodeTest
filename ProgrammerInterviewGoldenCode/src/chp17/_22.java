package chp17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/15 上午11:15
 */
public class _22 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.22. 单词转换
     * 难度: medium
     * <p>
     * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
     * <p>
     * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
     * <p>
     * 示例:
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * 输出:
     * ["hit","hot","dot","lot","log","cog"]
     * <p>
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * <p>
     * 输出: []
     * <p>
     * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
     *
     * <p>
     * 数据范围：
     */

    /*
    思路：DFS搜索
     */
    List<String> wordList;
    List<String> ans = new ArrayList<>();
    Set<String> visit = new HashSet<>();
    String endWord;
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.wordList = wordList;
        this.endWord = endWord;
        dfs(beginWord);
        return ans;


    }

    /**
     * DFS 搜索，这里找到一条路径即可返回
     * @param s
     * @return
     */
    public boolean dfs(String s) {
        ans.add(s);
        visit.add(s);

        if (s.equals(endWord)) {
            return true;
        }
        for (String word : wordList) {
            if (difCount(s, word) && !visit.contains(word)) {
                if (dfs(word)) {
                    return true;    // 找到一条路径，立即返回
                }
            }
        }
        ans.remove(s);
        return false;
    }

    /**
     * 判断两个字符串是否不同的字符数 是1
     * @param s1
     * @param s2
     * @return
     */
    public boolean difCount(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return cnt == 1;
    }
}
