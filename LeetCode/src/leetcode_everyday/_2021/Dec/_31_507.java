package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/31 上午9:11
 */
public class _31_507 {
    /**
     * 每日一题：2021/12/31
     * <p>
     * 507. 完美数
     * <p>
     * 难度：easy
     * <p>
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     *
     * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 28
     *
     * 输出：true
     *
     * 解释：28 = 1 + 2 + 4 + 7 + 14
     * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
     * <p>
     * 范围
     * <p>
     * 1 <= num <= 10^8
     **/

    /*
    思路1： 枚举 - 遍历因数
     */
    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    /*
    思路2： 优化枚举
     */
    public boolean checkPerfectNumber2(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i < num) {
                    sum += num / i;
                }
            }
        }
        return sum == num;
    }



}
