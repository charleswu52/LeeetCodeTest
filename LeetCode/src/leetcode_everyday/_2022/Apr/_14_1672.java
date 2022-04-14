package leetcode_everyday._2022.Apr;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/4/14 9:06
 */
public class _14_1672 {
    /**
     * 每日一题：2022/4/14
     * <p>
     * 1672. 最富有客户的资产总量
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
     * <p>
     * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
     * <p>
     * 示例
     * <p>
     * 输入：accounts = [[1,2,3],[3,2,1]]
     * <p>
     * 输出：6
     * <p>
     * 解释：
     * 第 1 位客户的资产总量 = 1 + 2 + 3 = 6
     * 第 2 位客户的资产总量 = 3 + 2 + 1 = 6
     * 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
     * <p>
     * 范围
     * <p>
     * m == accounts.length
     * n == accounts[i].length
     * 1 <= m, n <= 50
     * 1 <= accounts[i][j] <= 100
     */

    /*
    思路：简单模拟题
     */
    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int[] account : accounts) {
            int sum = Arrays.stream(account).sum();
            max = Math.max(max, sum);
        }
        return max;

    }
}
