package leetcode_hot100.top20;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/7/3 11:03
 */
public class _4 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 4. 寻找两个正序数组的中位数
     * 难度：hard
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 示例
     * <p>
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * <p>
     * 范围
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     */

    /*
    思路： 归并排序
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count = nums1.length + nums2.length;
        int medium = count / 2; // 奇数个直接取中间元素
        double p = 0, m = 0;
        int i, j, k;
        for (i = 0, j = 0, k = 0; i < nums1.length && j < nums2.length && k <= medium; k++) {
            if (nums1[i] <= nums2[j]) {
                if (k == medium - 1) {
                    p = nums1[i];
                }
                if (k == medium) {
                    m = nums1[i];
                }
                i++;
            } else {
                if (k == medium - 1) {
                    p = nums2[j];
                }
                if (k == medium) {
                    m = nums2[j];
                }
                j++;
            }
        }
        while (i < nums1.length && k <= medium) {
            if (k == medium - 1) {
                p = nums1[i];
            }
            if (k == medium) {
                m = nums1[i];
            }
            i++;
            k++;
        }
        while (j < nums2.length && k <= medium) {
            if (k == medium - 1) {
                p = nums2[j];
            }
            if (k == medium) {
                m = nums2[j];
            }
            j++;
            k++;
        }


        if (count % 2 == 0) {
            return (p + m) / 2.0;
        }
        return m;
    }

    @Test
    public void test() {
        int[] nums1 = {2,3};
        int[] nums2 = {};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
