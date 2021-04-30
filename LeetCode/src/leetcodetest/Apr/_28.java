package leetcodetest.Apr;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/4/28 上午8:07
 */
public class _28 {
    /**
     * 每日一题：2021/4/28
     * 633. 平方数之和
     * 难度: medium
     * <p>
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
     *
     * <p>
     * 示例：
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * <p>
     * 数据范围：
     * 0 <= c <= 2^31 - 1
     */

    /*
    思路1：使用sqrt函数
    要找到满足 a^2+b^2 = c，可以枚举其中一个a,在a^2<c 的范围内，使用sqrt函数计算b = sqrt（c-a^2）如果c是一个完全平方数，则结果为true否则为false
     */
    public boolean judgeSquareSum(int c) {
        double t = Math.sqrt((double) c);
//        System.out.println(t);
        if (t == (int) t) {
            return true;
        }
        for (long a = 1; a <= t; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }


    /*
    思路2：使用双指针
    假设a <= b,初始a=0,b=sqrt(c):
        如果 a^2 +b^2 = c,则找到了一个解，返回true;
        如果 a^2+b^2 <c，此时需要将a+1,继续查找
        如果 a^2+b^2 >c，此时需要将b-1,继续查找
        当 a=b的时候，查找结束，如果没有找到a b 满足a^2+b^2=c,则说明不存在题目要求的解，返回false
     */

    public boolean judgeSquareSum2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }


    @Test
    public void test() {
        int c = 4;
        System.out.println(judgeSquareSum(2147482647));

    }

}
