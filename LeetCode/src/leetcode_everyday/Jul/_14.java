package leetcode_everyday.Jul;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/14 7:51
 */
public class _14 {
    /**
     * 每日一题：2021/7/14
     * 1818. 绝对差值和
     * 难度: medium
     * <p>
     * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
     * <p>
     * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
     * <p>
     * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
     * <p>
     * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
     * <p>
     * |x| 定义为：
     * <p>
     * 如果 x >= 0 ，值为 x ，或者
     * 如果 x <= 0 ，值为 -x
     *
     * <p>
     * 示例
     * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
     * 输出：3
     * 解释：有两种可能的最优方案：
     * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
     * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
     * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
     * <p>
     * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
     * 输出：20
     * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
     * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
     *
     * <p>
     * 限制：
     * n == nums1.length
     * n == nums2.length
     * 1 <= n <= 105
     * 1 <= nums1[i], nums2[i] <= 105
     */

    /*
    思路：排序 + 二分查找
    首先每个位置上的差的绝对值和是一个base，然后要使得这个base变最小就要在遍历计算的时候，对确定的num2[i]在nums1中找到最接近该数的值
    在查找的时候为了方便查找，需要开辟辅助数组对nums1中的元素进行排序。
    这样找到最接近num2[i]的值后需要记录变化前后的值 |nums1[i]-nums2[i]| - |nums1[j] - nums2[i]|
    对每个值都计算一个这个差值，为了使结果最小那么这个差值就要最大 （maxn）
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int n = nums1.length;
        int res = 0;

        int[] helper = new int[n]; // 辅助数组
        System.arraycopy(nums1, 0, helper, 0, n);
        Arrays.sort(helper);

        int sum = 0, maxn = 0; // 记录绝对值差值和以及最大差值
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % mod;
            int j = binarySearch(helper, nums2[i]);
            // 有两种可能的情况都要考虑 比当前nums2[i]大或者小
            if (j < n) {
                maxn = Math.max(maxn, diff - Math.abs(helper[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - Math.abs(helper[j - 1] - nums2[i]));
            }
        }

        return (sum - maxn + mod) % mod;


    }

    /**
     * 有序数组中找最接近target值的下标
     *
     * @param array
     * @param target
     * @return
     */
    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        if (array[right] < target) {
            return right + 1;
        }
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
