package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/12 8:36
 */
public class _12_806 {
    /**
     * 每日一题：2022/4/12
     * <p>
     * 806. 写字符串需要的行数
     * <p>
     * 难度：easy
     * <p>
     * 我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，
     * 那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位，
     * widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。
     * <p>
     * 现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
     * <p>
     * 示例
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * <p>
     * widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
     * S = "abcdefghijklmnopqrstuvwxyz"
     * <p>
     * 输出: [3, 60]
     * <p>
     * 解释:
     * 所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
     * 我们需要2个整行和占用60个单位的一行。
     * <p>
     * 范围
     * <p>
     * 字符串 S 的长度在 [1, 1000] 的范围。
     * S 只包含小写字母。
     * widths 是长度为 26的数组。
     * widths[i] 值的范围在 [2, 10]。
     */

    public int[] numberOfLines(int[] widths, String s) {
        int a = 0, b = 0;
        for (char ch : s.toCharArray()) {
            int temp = widths[ch - 'a'];
            if (b + temp > 100 && ++a >= 0) {
                b = temp;

            } else {
                b += temp;
            }
        }
        if (b != 0) {
            a++;
        }
        return new int[]{a, b};
    }
}
