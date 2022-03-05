package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/5 9:07
 */
public class _5_521 {
    /**
     * 每日一题：2022/3/5
     * <p>
     * 2104. 子数组范围和
     * <p>
     * 难度：medium
     * <p>
     * 给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  。如果不存在，则返回 -1 。
     *
     * 「最长特殊序列」定义如下：该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     *
     * 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
     *
     * 例如，“abc” 是 “aebdc” 的子序列，因为您可以删除 “aebdc” 中的下划线字符来得到 “abc” 。
     * “aebdc” 的子序列还包括 “aebdc” 、 “aeb” 和 “” (空字符串)。
     * <p>
     * 示例
     * <p>
     * 输入: a = "aba", b = "cdc"
     *
     * 输出: 3
     *
     * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
     * <p>
     * 范围
     * <p>
     * 1 <= a.length, b.length <= 100
     * a 和 b 由小写英文字母组成
     */

    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
