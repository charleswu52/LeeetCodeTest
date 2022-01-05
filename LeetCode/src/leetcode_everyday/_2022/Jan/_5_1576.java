package leetcode_everyday._2022.Jan;

/**
 * @author WuChao
 * @create 2022/1/5 23:10
 */
public class _5_1576 {
    /**
     * 每日一题：2022/1/5
     * <p>
     * 1576. 替换所有的问号
     * <p>
     * 难度：easy
     * <p>
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     * <p>
     * 注意：你 不能 修改非 '?' 字符。
     * <p>
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     * <p>
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "?zs"
     * <p>
     * 输出："azs"
     * <p>
     * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：s = "??yw?ipkj?"
     * <p>
     * 输出："acywaipkja"
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 100
     * <p>
     * s 仅包含小写英文字母和 '?' 字符
     **/
    /*
    思路：简单字符串模拟，但是注意考虑各种边界情况
     */
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '?') {
                char replace;
                if (i > 0 && i < len - 1) {
                    char pre = chars[i - 1];
                    char next = chars[i + 1];
                    replace = pre == 'a' ? 'b' : 'a';
                    if (replace=='a' && next=='a') {
                        replace = pre == 'b' ? 'c' : 'b';
                    } else if (replace == 'b' && next == 'b') {
                        replace = 'c';
                    }
                } else if (i > 0) {
                    char pre = chars[i - 1];
                    replace = pre == 'a' ? 'b' : 'a';
                } else if (i < len - 1) {
                    char next = chars[i + 1];
                    replace = next == 'a' ? 'b' : 'a';
                } else {
                    replace = 'a';
                }
                chars[i] = replace;
            }
        }
        return new String(chars);
    }
}
