package leetcode_everyday.Apr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/23 上午8:12
 */
public class _23 {
    /**
     * 每日一题：2021/4/23
     * 368. 最大整除子集
     * 难度: medium
     * <p>
     * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 ，或
     * answer[j] % answer[i] == 0
     * 如果存在多个有效解子集，返回其中任何一个均可。
     *
     * <p>
     * 示例：
     * 输入：nums = [1,2,3]
     * 输出：[1,2]
     * 解释：[1,3] 也会被视为正确答案。
     *
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 2 * 109
     * nums 中的所有整数 互不相同
     */

    /**
     * 使用动态规划的思路
     * 预处理：对数组进行排序
     * dp[i] 表示以nums[i] 为结尾的整除子数组的最后一个数
     * 计算初始状态每个dp[i] = 1
     * 计算dp[i] 的时候需要遍历 0 - i-1 的所有结果 判断是否可以被当前数整除，继而更新dp[i] = max(dp[i],dp[j]+1)
     * 同时过程中还要记录最大的dp[i] 记为 maxSize
     * 构造完成dp[i]后，再从最大的maxSize 逆向遍历dp 数组，满足大小等于maxSize的dp[i] 添加到结果中，同时maxSize -- 直到添加完所有结果
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];  //dp[i] 表示以dp[i] 结尾的整除数 的个数
        Arrays.fill(dp, 1);
        int maxSize = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxSize = Math.max(maxSize, dp[i]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length-1; i >=0 && maxSize>0 ; i--) {
            if (dp[i] == maxSize) {
                if (res.size() == 0) {
                    res.add(nums[i]);
                    maxSize--;
                } else {
                    if (res.get(res.size() - 1) % nums[i] == 0) {
                        res.add(nums[i]);
                        maxSize--;
                    }
                }
            }
        }
        return res;
    }




}
