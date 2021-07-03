package leetcode_everyday.May;

/**
 * @author WuChao
 * @since 2021/5/21 上午8:11
 */
public class _21 {
    /**
     * 每日一题：2021/5/21
     * 1035. 不相交的线
     * 难度: medium
     * <p>
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     *
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
     *
     *  nums1[i] == nums2[j]
     * 且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     *
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     *
     *
     * <p>
     * 示例：
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     * 解释：可以画出两条不交叉的线，如上图所示。
     * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
     * <p>
     * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * 输出：3
     * <p>
     *
     * <p>
     * 数据范围：
     * 1 <= nums1.length <= 500
     * 1 <= nums2.length <= 500
     * 1 <= nums1[i], nums2[i] <= 2000
     */

    /*
    题目解析：
    题目描述的很复杂，感觉是在考察阅读理解，但是仔细考虑不相交的线的规律就会发现本质其实是，求两个数组的最长公共子序列。
    求最长公共子序列的问题，解决方案就是使用动态规划.

    因此该题目的难点就是发现考察的本质是求最长公共子序列问题
     */

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp =new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];


    }
}
