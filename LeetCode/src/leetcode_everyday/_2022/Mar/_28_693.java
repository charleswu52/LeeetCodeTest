package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/28 9:04
 */
public class _28_693 {
    /**
     * 每日一题：2022/3/28
     * <p>
     * 693. 交替位二进制数
     * <p>
     * 难度：easy
     * <p>
     * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
     * <p>
     * 示例1
     * <p>
     * 输入：n = 5
     * <p>
     * 输出：true
     * <p>
     * 解释：5 的二进制表示是：101
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 2^31 - 1
     */

    /*
    思路：简单二进制转换模拟
     */
    public boolean hasAlternatingBits(int n) {
        String s = Integer.toBinaryString(n);
        int length = s.length();
        if (length < 2) {
            return true;
        }
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /*
    思路： 位运算
     */
    public boolean hasAlternatingBits2(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;

    }
}