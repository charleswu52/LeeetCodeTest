package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/11 8:44
 */
public class _11_357 {
    /**
     * 每日一题：2022/4/11
     * <p>
     * 357. 统计各位数字都不同的数字个数
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。
     * <p>
     * 示例
     * <p>
     * 输入：n = 2
     * <p>
     * 输出：91
     * <p>
     * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
     * <p>
     * 范围
     * <p>
     * 0 <= n <= 8
     */

    /*
    思路：数学 排列组合
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }
}
