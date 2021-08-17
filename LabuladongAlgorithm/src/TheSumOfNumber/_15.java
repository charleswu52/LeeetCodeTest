package TheSumOfNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/1 21:06
 */
public class _15 {
    /**
     * 数字之和系列
     * 15. 三数之和
     * 难度：medium
     *
     * 描述
     *
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 输入：nums = []
     * 输出：[]
     *
     * 数据范围
     *
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */


    /*
    思路： 排序+双指针
    先将原数组排序
    固定3个指针中最左(小)数字的指针k，双指针i，j分别设在数组索引(k,len(nums))两端，通过双指针交替向中间移动，记录对于每个固定指针k的所有满足
    nums[k] + nums[i] + nums[j] = 0 的i,j,k的组合：
        1. 当 nums[k] > 0 时直接 break 跳出
        2. 当 nums[k] == nums[k-1]时 跳过该元素，因为已经将 nums[k-1]的所有组合加入到结果中
        3. i, j 分别设在 ( k,len(nums) ) 两端，当 i < j 时循环计算 s = nums[k] + nums[i] + nums[j]
            当 s < 0 时， i += 1 并跳过所有重复的nums[i]
            当 s > 0 时， j -= 1 并跳过所有重复的nums[j]
            当 s = 0 时， 记录组合 [k,i,j] 至结果集合中 ，同时执行  i += 1 和 j -= 1 并跳过所有重复的 nums[i] 和 nums[j]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }

}
