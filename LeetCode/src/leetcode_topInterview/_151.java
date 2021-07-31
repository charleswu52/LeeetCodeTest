package leetcode_topInterview;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2021/7/31 10:50
 */
public class _151 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 151. 翻转字符串里的单词
     * <p>
     * 难度：medium
     * <p>
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     *
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     *
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     *
     * 说明：
     *
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     *
     *
     * 示例 1：
     *
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     *
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     *
     */

    /*
    因为是面试，不使用现有的API
    思路：双端队列
     */
    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;

        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char ch = s.charAt(left);
            if ((word.length() != 0) && (ch == ' ')) {
                deque.offerFirst(word.toString());
                word.setLength(0);

            } else if (ch != ' ') {
                word.append(ch);
            }
            left++;
        }
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }
}
