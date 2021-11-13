package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/13 12:47
 */
public class _13_520 {
    /**
     * 每日一题：2021/11/13
     * <p>
     * 520. 检测大写字母
     * <p>
     * 难度：easy
     * <p>
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     *
     * 全部字母都是大写，比如 "USA" 。
     * 单词中所有字母都不是大写，比如 "leetcode" 。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入: n = 3, k = 0
     * <p>
     * 输出: 1
     * <p>
     * 解释:
     * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
     * <p>
     * 范围
     * <p>
     * 1 <= word.length <= 100
     * word 由小写和大写英文字母组成
     */

    /*
    思路：模拟判断
     */
    public boolean detectCapitalUse(String word) {
        int len = word.length();
        if (len < 2) {
            return true;
        }
        boolean first = (int) word.charAt(0) >= 97;
        boolean second = (int) word.charAt(1) >= 97;
        if (first&&!second)
            return false;
        for (int i = 2; i < len; i++) {
            boolean temp = (int) word.charAt(i) >= 97;
            if (second != temp) {
                return false;
            }
        }
        return true;

    }

}
