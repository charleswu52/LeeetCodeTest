package chp1;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/4/4 上午10:56
 */
public class _2 {
    /**
     * 程序员面试金典(version 6) - 面试题 01.02. 判定是否互为字符重排
     * 难度: easy
     * <p>
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     * <p>
     * 例如：
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     *
     * <p>
     * 数据范围：
     * 0 <= len(s1) <= 100
     * 0 <= len(s2) <= 100
     */

    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }
}
