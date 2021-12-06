package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/6 9:17
 */
public class _6_1816 {
    /**
     * 每日一题：2021/12/6
     * 1816. 截断句子
     * 难度：easy
     * <p>
     * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
     *
     * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
     * 给你一个句子 s 和一个整数 k ，请你将 s 截断，使截断后的句子仅含 前 k 个单词。返回 截断 s 后得到的句子。
     * <p>
     * 示例1
     * <p>
     * 输入：s = "Hello how are you Contestant", k = 4
     *
     * 输出："Hello how are you"
     *
     * 解释：
     *
     * s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
     * 前 4 个单词为 ["Hello", "how", "are", "you"]
     * 因此，应当返回 "Hello how are you"
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 500
     * k 的取值范围是 [1, s 中单词的数目]
     * s 仅由大小写英文字母和空格组成
     * s 中的单词之间由单个空格隔开
     * 不存在前导或尾随空格
     */

    /*
    思路：字符串 简单模拟
     */
    public String truncateSentence(String s, int k) {
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                k--;
                if (k == 0) {
                    end = i;
                    break;
                }
            }
        }
        end = k > 0 ? s.length() : end;
        return s.substring(0, end);
    }
}
