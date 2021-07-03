package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/5 下午3:41
 */
public class _5 {
    /**
     * 每日一题：2021/4/5
     * 88. 合并两个有序数组
     * 难度: easy
     *
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     * <p>
     * 示例：
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     *
     * <p>
     * 数据范围：
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[i] <= 109
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else {
                for (int k = m; k > i; k--) {
                    nums1[k] = nums1[k - 1];
                }
                nums1[i] = nums2[j];
                i++;
                j++;
                m++;
            }
        }
        while (j < n) {
            nums1[i++] = nums2[j++];
        }

    }
}
