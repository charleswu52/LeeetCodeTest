package leetcode_hot100.top20;

/**
 * @author WuChao
 * @create 2021/7/5 13:22
 */
public class _31 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 31. 下一个排列
     * 难度：medium
     * <p>
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * <p>
     * 示例
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * <p>
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * <p>
     * 输入：nums = [1]
     * 输出：[1]
     * <p>
     * 范围
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     */

    /*
    题目解析：
    本题要求我们实现一个算法，将给定数字序列重新排列成字典序中下一个更大的排列。
    以数字序列 [1,2,3] 为例，其排列按照字典序依次为：
        [1,2,3]
        [1,3,2]
        [2,1,3]
        [2,3,1]
        [3,1,2]
        [3,2,1]
    这样，排列 [2,3,1] 的下一个排列即为 [3,1,2]。特别的，最大的排列 [3,2,1] 的下一个排列为最小的排列 [1,2,3]。
     */
    /*
    题目搞懂后，想一下解决方案。
    思路：两遍扫描
    注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：
        我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
        同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。
            这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
    算法描述：
    对于长度为 n 的排列 a：
        首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
        如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i] < a[j]。这样「较大数」即为 a[j]。
        交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序。
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
