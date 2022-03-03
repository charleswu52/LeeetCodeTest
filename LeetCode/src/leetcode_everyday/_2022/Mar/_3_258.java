package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/3 8:55
 */
public class _3_258 {
    /**
     * 每日一题：2022/3/3
     * <p>
     * 258. 各位相加
     * <p>
     * 难度：easy
     * <p>
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
     * <p>
     * 示例
     * <p>
     * 输入: num = 38
     * <p>
     * 输出: 2
     * <p>
     * 解释: 各位相加的过程为：
     * <p>
     * 38 --> 3 + 8 --> 11
     * 11 --> 1 + 1 --> 2
     * 由于 2 是一位数，所以返回 2。
     * <p>
     * 范围
     * <p>
     * 0 <= num <= 2^31 - 1
     */

    /*
    思路1： 循环或者递归
    思路2：数学推导  O(1) 时间复杂度
    了解 数根 的概念以及公式推导
     */
    public int addDigits(int num) {
        return num < 10 ? num : (1 + (num - 1) % 9);
    }


    public int addDigits2(int num) {
        if (num < 10) {
            return num;
        }
        int ans = getSum(num);
        while (ans > 9) {
            ans = getSum(ans);
        }
        return ans;
    }

    public int getSum(int num) {
        int ans = 0;
        while (num / 10 > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
}
