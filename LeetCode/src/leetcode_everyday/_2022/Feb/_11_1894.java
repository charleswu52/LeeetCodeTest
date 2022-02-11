package leetcode_everyday._2022.Feb;

import java.util.Arrays;
import java.util.Map;

/**
 * @author WuChao
 * @create 2022/2/11 9:33
 */
public class _11_1894 {
    /**
     * 每日一题：2022/2/11
     * <p>
     * 1984. 学生分数的最小差值
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     * <p>
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     * <p>
     * 返回可能的 最小差值 。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [9,4,1,7], k = 2
     * <p>
     * 输出：2
     * <p>
     * 解释：选出 2 名学生的分数，有 6 种方法：
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
     * - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
     * 可能的最小差值是 2
     * <p>
     * 范围
     * <p>
     * 1 <= k <= nums.length <= 1000
     * 0 <= nums[i] <= 10^5
     */

    /*
    思路： 排序 + 滑动窗口
     */
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= len - k; i++) {
            res = Math.min(nums[i + k - 1] - nums[i], res);
        }
        return res;

    }
}
