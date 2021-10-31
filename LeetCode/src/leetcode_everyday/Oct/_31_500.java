package leetcode_everyday.Oct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/10/31 13:18
 */
public class _31_500 {
    /**
     * 每日一题：2021/10/31
     * <p>
     * 500. 键盘行
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
     * <p>
     * 美式键盘 中：
     * <p>
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入：words = ["Hello","Alaska","Dad","Peace"]
     * <p>
     * 输出：["Alaska","Dad"]
     * <p>
     * 范围
     * <p>
     * 1 <= words.length <= 20
     * 1 <= words[i].length <= 100
     * words[i] 由英文字母（小写和大写字母）组成
     */


    public String[]  findWords(String[] words) {
        List<String> res = new ArrayList<>();
        String rowIdx = "12210111011122000010020202"; // 'a' - 'z' 对应的键盘上的行
        for (String word : words) {
            boolean isValid = true;
            char idx = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a'); // 记录下第一个字符对应的键盘上的行
            for (int i = 1; i < word.length(); i++) {
                if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != idx) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                res.add(word);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;


    }
}
