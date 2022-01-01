package leetcode_everyday._2021.Feb;

public class _19 {
    /**
     * 每日一题：2021/2/19
     * 1004. 最大连续1的个数 III
     * 难度: medium
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * 返回仅包含 1 的最长（连续）子数组的长度。
     * <p>
     * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：
     * [1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * <p>
     * 数据范围：
     * 1 <= A.length <= 20000
     * 0 <= K <= A.length
     * A[i] 为 0 或 1
     */

    public int longestOnes(int[] A, int K) {
        int res = 0, left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                if (K == 0) {
                    while (A[left] == 1) {
                        left++;
                    }
                    left++;
                } else {
                    K--;
                }
            }
            res = Math.max(res, ++right - left);
        }
        return res;
    }

    /*
    题目解析：
    把「最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度」转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」。
    思路： 滑动窗口
    将题目转化为求最大连续子区间，滑动窗口的限制条件是最多有K个0。
    实现思路：
    1.使用 left 和 right 两个指针，分别指向滑动窗口的左右边界。
    2.right 主动右移：right 指针每次移动一步。当 A[right] 为 0，说明滑动窗口内增加了一个 0；
    3.left 被动右移：判断此时窗口内 0 的个数，如果超过了 K，则 left 指针被迫右移，直至窗口内的 0 的个数小于等于 K 为止。
    4.滑动窗口长度的最大值就是所求。
     */

    public int longestOnes2(int[] A, int K) {
        int n = A.length;
        int res = 0, left = 0, right = 0;
        int zeros = 0;
        while (right < n) {
            if (A[right] == 0) {
                zeros++;
            }
            while (zeros > K) {
                if (A[left] == 0) {
                    zeros--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }


    public void _21_2_19() {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int K = 2;
        System.out.println(longestOnes(A, K));
        System.out.println(longestOnes2(A, K));

    }


    /**
     * ！！！重要--滑动窗口问题解题模板--！！！
     * def findSubArray(nums):
     *     N = len(nums) # 数组/字符串长度
     *     left, right = 0, 0 # 双指针，表示当前遍历的区间[left, right]，闭区间
     *     sums = 0 # 用于统计 子数组/子区间 是否有效，根据题目可能会改成求和/计数
     *     res = 0 # 保存最大的满足题目要求的 子数组/子串 长度
     *     while right < N: # 当右边的指针没有搜索到 数组/字符串 的结尾
     *         sums += nums[right] # 增加当前右边指针的数字/字符的求和/计数
     *         while 区间[left, right]不符合题意：# 此时需要一直移动左指针，直至找到一个符合题意的区间
     *             sums -= nums[left] # 移动左指针前需要从counter中减少left位置字符的求和/计数
     *             left += 1 # 真正的移动左指针，注意不能跟上面一行代码写反
     *         # 到 while 结束时，我们找到了一个符合题意要求的 子数组/子串
     *         res = max(res, right - left + 1) # 需要更新结果
     *         right += 1 # 移动右指针，去探索新的区间
     *     return res
     */
}
