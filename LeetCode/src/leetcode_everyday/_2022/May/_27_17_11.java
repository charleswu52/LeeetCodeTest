package leetcode_everyday._2022.May;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/5/27 8:39
 */
public class _27_17_11 {
    /**
     * 每日一题：2022/5/27
     * <p>
     * 面试题 17.11. 单词距离
     * <p>
     * 难度：medium
     * <p>
     * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     * <p>
     * 示例
     * <p>
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * <p>
     * 输出：1
     */

    /*
    思路1：使用Map记录下每个单词出现的位置
     */
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }
        List<Integer> list1 = map.getOrDefault(word1, null);
        List<Integer> list2 = map.getOrDefault(word2, null);
        if (list1 == null || list2 == null) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0, size1 = list1.size(), size2 = list2.size();
        while (i < size1 && j < size2) {
            res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }

    /*
    思路2：一次遍历words
     */
    public int findClosest2(String[] words, String word1, String word2) {
        boolean word1Find = false, word2Find = false;
        int res = Integer.MAX_VALUE;
        int n = words.length;
        int i = 0, j = 0;
        for (int idx = 0; idx < n; i++) {
            if (words[idx].equals(word1)) {
                i = idx;
                word1Find = true;
            } else if (words[idx].equals(word2)) {
                j = idx;
                word2Find = true;
            }
            if (word1Find && word2Find) {
                res = Math.min(res, Math.abs(i - j));
            }
        }
        return res;
    }

}
