package leetcode_everyday._2022.Jun;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/6/16 21:16
 */
public class _15_719 {
    /**
     * 每日一题：2022/6/15
     * <p>
     * 719. 找出第 K 小的数对距离
     * <p>
     * 难度：hard
     * <p>
     * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
     *
     * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
     * 返回 所有数对距离中 第 k 小的数对距离。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [1,3,1], k = 1
     * 输出：0
     * 解释：数对和对应的距离如下：
     * (1,3) -> 2
     * (1,1) -> 0
     * (3,1) -> 2
     * 距离第 1 小的数对是 (1,1) ，距离为 0 。
     * <p>
     * 范围
     * <p>
     * n == nums.length
     * 2 <= n <= 10^4
     * 0 <= nums[i] <= 10^6
     * 1 <= k <= n * (n - 1) / 2
     */


    /*
    思路：
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 1, j = 0; i < n; i++) {
                if (nums[i] - nums[j] > mid) {
                    while (nums[i] - nums[j] > mid) {
                        j++;
                    }
                }
                cnt += (i - j);
            }
            if (cnt >= k) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return right;
    }
}
