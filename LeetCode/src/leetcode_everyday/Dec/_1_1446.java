package leetcode_everyday.Dec;

/**
 * @author WuChao
 * @create 2021/12/1 9:25
 */
public class _1_1446 {
    /**
     * 每日一题：2021/12/1
     * 1446. 连续字符
     * 难度: easy
     * <p>
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     *
     * 请你返回字符串的能量。
     *
     *
     * <p>
     * 输入：s = "leetcode"
     * 输出：2
     * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
     *
     *
     *
     * <p>
     * 数据范围：
     * 1 <= s.length <= 500
     * s 只包含小写英文字母。
     */

    /*
    思路：简单字符串模拟题
     */
    public int maxPower(String s) {
        int res = 1;
        int count = 1;
        int len = s.length();
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 1;
            }
        }
        res = Math.max(res, count);
        return res;

    }

}
