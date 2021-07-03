package leetcode_everyday.May;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/5/20 上午9:08
 */
public class _20 {
    /**
     * 每日一题：2021/5/20
     * 692. 前K个高频单词
     * 难度: medium
     * <p>
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     *
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     * <p>
     * 示例：
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     * <p>
     * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * 输出: ["the", "is", "sunny", "day"]
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     *     出现次数依次为 4, 3, 2 和 1 次。
     * <p>
     *
     * <p>
     * 数据范围：
     * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
     * 输入的单词均由小写字母组成。
     * 进阶：
     * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
     */

    /*
    使用HashMap存储每个单词对应的频次，然后
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> store = new HashMap<>();
        for (String word : words) {
            store.put(word, store.getOrDefault(word, 0) + 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(store.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue() - o1.getValue();
                }
            }
        });
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).getKey());
        }
//        System.out.println(list);

        return res;
    }

    @Test
    public void test() {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequent(words, k));
    }
}
