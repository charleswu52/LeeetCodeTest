package leetcode_everyday._2022.Jan;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author WuChao
 * @create 2022/1/14 17:39
 */
public class _14_373 {
    /**
     * 每日一题：2022/1/14
     * <p>
     * 373. 查找和最小的 K 对数字
     * <p>
     * 难度：medium
     * <p>
     * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     *
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     *
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2) ...  (uk,vk)。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,6,1,0]
     *
     * 输出：1
     *
     * 解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：0
     * 解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
     * <p>
     * 范围
     * <p>
     * 1 <= nums1.length, nums2.length <= 10^5
     * -10^9 <= nums1[i], nums2[i] <= 10^9
     * nums1 和 nums2 均为升序排列
     * 1 <= k <= 1000
     **/

    /*
    思路：优先队列
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k,(o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> res = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            queue.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int[] index = queue.poll();
            list.add(nums1[index[0]]);
            list.add(nums2[index[1]]);
            res.add(list);
            if (index[1] + 1 < n) {
                queue.offer(new int[]{index[0], index[1] + 1});
            }
        }
        return res;

    }
}
