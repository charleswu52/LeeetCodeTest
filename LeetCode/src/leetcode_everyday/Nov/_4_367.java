package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/4 14:36
 */
public class _4_367 {
    /**
     * 每日一题：2021/11/4
     * <p>
     * 367. 有效的完全平方数
     * <p>
     * 难度：easy
     * <p>
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * <p>
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     * <p>
     * 示例1：
     * <p>
     * 输入：num = 16
     * <p>
     * 输出：true
     * <p>
     * 范围
     * <p>
     * 1 <= num <= 2^31 - 1
     */

    /*
    思路：二分查找

     */

    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long temp = (long) mid * mid;
            if (temp < num) {
                left = mid + 1;
            } else if (temp > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;

    }
}
