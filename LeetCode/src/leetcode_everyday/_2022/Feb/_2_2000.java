package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/2 14:05
 */
public class _2_2000 {
    /**
     * 每日一题：2022/2/2
     * <p>
     * 2000. 反转单词前缀
     * <p>
     * 难度：easy
     * <p>
     * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，
     * 反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
     * <p>
     * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。
     * 结果字符串将会是 "dcbaefd" 。
     * 返回 结果字符串 。
     * <p>
     * 输入：s = "YazaAay"
     * <p>
     * 输入：word = "abcdefd", ch = "d"
     * <p>
     * 输出："dcbaefd"
     * <p>
     * 解释："d" 第一次出现在下标 3 。
     * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
     * <p>
     * 范围
     * <p>
     * 1 <= word.length <= 250
     * word 由小写英文字母组成
     * ch 是一个小写英文字母
     */

    /*
    思路：简单字符串模拟
     */
    public String reversePrefix(String word, char ch) {
        int pos = -1;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            if (word.charAt(i) == ch) {
                pos = i;
                break;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = pos; i >= 0; i--) {
            stringBuffer.append(word.charAt(i));
        }
        for (int i = pos + 1; i < len; i++) {
            stringBuffer.append(word.charAt(i));
        }
        return stringBuffer.toString();

    }
}
