package leetcode_everyday._2022.Jan;

/**
 * @author WuChao
 * @create 2022/1/31 9:05
 */
public class _31_1342 {
    /**
     * 每日一题：2022/1/31
     * <p>
     * 1342. 将数字变成 0 的操作次数
     * <p>
     * 难度：easy
     * <p>
     * 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
     * <p>
     * 输入：num = 14
     *
     * 输出：6
     *
     * 解释：
     * 步骤 1) 14 是偶数，除以 2 得到 7 。
     * 步骤 2） 7 是奇数，减 1 得到 6 。
     * 步骤 3） 6 是偶数，除以 2 得到 3 。
     * 步骤 4） 3 是奇数，减 1 得到 2 。
     * 步骤 5） 2 是偶数，除以 2 得到 1 。
     * 步骤 6） 1 是奇数，减 1 得到 0 。
     * <p>
     * 范围
     * <p>
     * 0 <= num <= 10^6
     */

    public int numberOfSteps(int num) {
        int res = 0;
        while (num != 0) {
            res++;
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
        }
        return res;

    }
}
