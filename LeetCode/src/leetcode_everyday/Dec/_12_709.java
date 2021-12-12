package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/12 10:32
 */
public class _12_709 {
    /**
     * 每日一题：2021/12/12
     * <p>
     * 709. 转换成小写字母
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "Hello"
     * <p>
     * 输出："hello"
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "here"
     * <p>
     * 输出："here"
     *
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 100
     * s 由 ASCII 字符集中的可打印字符组成
     */

    /*
    思路：简单字符串转换
     */
    public String toLowerCase(String s) {
        int len = s.length();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ('A' <= c && c <= 'Z') {
                char t = (char) (c + 32);
                stringBuilder.append(t);
            } else {
                stringBuilder.append(c);
            }

        }
        return stringBuilder.toString();

    }
}
