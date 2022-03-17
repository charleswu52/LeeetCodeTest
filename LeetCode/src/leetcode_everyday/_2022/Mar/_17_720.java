package leetcode_everyday._2022.Mar;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/3/17 9:09
 */
public class _17_720 {
    /**
     * 每日一题：2022/3/17
     * <p>
     * 720. 词典中最长的单词
     * <p>
     * 难度：easy
     * <p>
     * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
     * <p>
     * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
     *
     * <p>
     * 示例
     * <p>
     * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * <p>
     * 输出："apple"
     * <p>
     * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 30
     * 所有输入的字符串 words[i] 都只包含小写字母。
     */

    /*
    思路：字符串哈希集合
     */
    public String longestWord(String[] words) {
        int n = words.length;
        Arrays.sort(words,(o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }else {
                return o2.compareTo(o1);
            }
        });
        String ans = "";
        Set<String> set = new HashSet<>();
        set.add("");
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                ans = word;
            }
        }
        return ans;

    }

    @Test
    public void test() {
        String[] strings = {"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

    }


}
