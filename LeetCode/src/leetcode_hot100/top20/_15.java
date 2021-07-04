package leetcode_hot100.top20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/4 11:21
 */
public class _15 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 15. 三数之和
     * 难度：medium
     * <p>
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 输入：nums = []
     * 输出：[]
     * <p>
     * 范围
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */

    /**
     * 两数之和 三数之和 四数之和 已经总结到一个专题中，也是面试常考察的题型
     *
     * @see TheSumOfNumber 包下的 三道题目
     */
    /*
    三数之和思路： 排序 + 双指针
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return res;
        }
        Arrays.sort(nums);
        // 外层循环 遍历 先确定一个数
        for (int k = 0; k < n - 2; k++) {
            if (nums[k] > 0) { // 已经排序，第一个数大于0 了后续的肯定都大于0
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            // 另外两个元素 使用双指针确定
            int i = k + 1, j = n - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) { // 移动左边指针 同时 排除相同元素
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }
        return res;
    }

}
