package leetcode_everyday._2021.Jan;

public class _24 {
    /**
     * 每日一题：2021/1/24
     * 674. 最长连续递增序列
     * 难度： easy
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     *
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
     * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 示例：
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     * 数据范围：
     * 0 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     */

    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        if (nums.length < 2) {
            return nums.length;
        }
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                temp++;
            }else {
                res = Math.max(res, temp);
                temp = 1;
            }
        }
        res = Math.max(res, temp);


        return res;
    }
    public void _21_1_24(){
        int[] nums = {2,2,2,2,2};
        System.out.println(findLengthOfLCIS(nums));

    }

}
