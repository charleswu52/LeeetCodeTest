package leetcode_hot100.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/27 11:04
 */
public class _448 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 448. 找到所有数组中消失的数字
     * 难度：easy
     * <p>
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
     * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     *
     *
     * <p>
     * 示例 1:
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[5,6]
     *
     * 输入：nums = [1,1]
     * 输出：[2]
     * <p>
     * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
     */


    /*
    思路：原地修改
    不使用额外的存储空间

     */

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;



    }
}
