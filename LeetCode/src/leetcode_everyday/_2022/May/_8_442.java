package leetcode_everyday._2022.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/5/8 8:50
 */
public class _8_442 {
    /**
     * 每日一题：2022/5/8
     * <p>
     * 442. 数组中重复的数据
     * <p>
     * 难度：medium
     * <p>
     * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
     * 请你找出所有出现 两次 的整数，并以数组形式返回。
     * <p>
     * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * <p>
     * 输出：[2,3]
     * <p>
     * 范围
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 105
     * 1 <= nums[i] <= n
     * nums 中的每个元素出现 一次 或 两次
     */

    /*
    思路：利用数值的范围 交换
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
