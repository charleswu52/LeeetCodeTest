package leetcode_topInterview;

/**
 * @author WuChao
 * @create 2021/7/31 9:15
 */
public class _69 {
    /**
     * codeTop 热题 面试常考
     * <p>
     * 69. x 的平方根
     * <p>
     * 难度：easy
     * <p>
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     */

    /*
    思路： 二分查找(面试考察点)
    由于 x 平方根的整数部分 ans 是满足 k^2  ≤x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。

    二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与 x 的大小关系，
    并通过比较的结果调整上下界的范围。由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ans 后，
    也就不需要再去尝试 ans+1 了。
     */
    public int mySqrt(int x) {
        int left = 0, right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;

    }
}
