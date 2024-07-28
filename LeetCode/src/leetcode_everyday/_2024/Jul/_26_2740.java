
package leetcode_everyday._2024.Jul;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author charles
 * @create 2024/7/28
 * @description 每日一题
 */
public class _26_2740 {
    /**
     * 每日一题：2024/7/26
     * 2740. 找出分区值
     * https://leetcode.cn/problems/find-the-value-of-the-partition/description/?envType=daily-question&envId=2024-07-24
     */

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i] - nums[i - 1]);
        }
        return min;
    }

    @Test
    public void  test() {
        System.out.println(findValueOfPartition(new int[]{1, 2, 3}));

    }

}