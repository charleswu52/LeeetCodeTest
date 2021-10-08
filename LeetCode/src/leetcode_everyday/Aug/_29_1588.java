package leetcode_everyday.Aug;

/**
 * @author WuChao
 * @create 2021/8/29 9:12
 */
public class _29_1588 {
    /**
     * 每日一题：2021/8/29
     * 1588. 所有奇数长度子数组的和
     * 难度：easy
     * <p>
     * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     *
     * 子数组 定义为原数组中的一个连续子序列。
     *
     * 请你返回 arr 中 所有奇数长度子数组的和 。
     *
     * <p>
     * 示例 1：
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     * <p>
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 1000
     */

    /*
    思路: 最优解法，一次遍历
    计算一个数字在多少个奇数长度的数组中出现过
    对于一个数字，它所在的数组，可以在它前面再选择 0, 1, 2, ... 个数字，一共有 left = i + 1 个选择；
    可以在它后面再选择 0, 1, 2, ... 个数字，一共有 right = n - i 个选择。

    如果在前面选择了偶数个数字，那么在后面，也必须选择偶数个数字，这样加上它自身，才构成奇数长度的数组；
    如果在前面选择了奇数个数字，那么在后面，也必须选择奇数个数字，这样加上它自身，才构成奇数长度的数组；

    数字前面共有 left 个选择，其中偶数个数字的选择方案有 left_even = (left + 1) / 2 个，
                               奇数个数字的选择方案有 left_odd = left / 2 个；
    数字后面共有 right 个选择，其中偶数个数字的选择方案有 right_even = (right + 1) / 2 个，
                                奇数个数字的选择方案有 right_odd = right / 2 个；

    所以，每个数字一共在 left_even * right_even + left_odd * right_odd 个奇数长度的数组中出现过。

    其他
    * odd奇数，even偶数
    * 对于每个元素i(数组中下标为i)来说，要构成奇数长度的子数组
      即 i左边的元素个数left+i本身自己一个+右边元素的个数right=奇数
      即 left+right=偶数
    * 满足a+b=偶数就只有两种情况
      1. 奇数+奇数=偶数
      2. 偶数+偶数=偶数
    * 1. 所以只需要求得i左边可以选择奇数长度的可能有多少种，即left_odd,同样求右边奇数right_odd
         就可以求出策略1有多少种可能
      2. 所以只需要求得i左边可以选择偶数长度的可能有多少种，即left_odd,同样求右边偶数right_odd
         就可以求出策略1有多少种可能，注意0也算选择的一种可能
    * 即元素i在所有奇数长度子数组出现的次数总和是
      left_odd*right_odd+left_even*right_even
    * 元素i左边元素共有i个，右边元素共有siz-i-1个
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int res = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - i;
            int left_even = (left + 1) / 2, left_odd = left / 2;
            int right_even = (right + 1) / 2, right_odd = right / 2;
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }
}
