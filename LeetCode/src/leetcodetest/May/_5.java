package leetcodetest.May;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author WuChao
 * @since 2021/5/5 下午4:20
 */
public class _5 {
    /**
     * 每日一题：2021/5/5
     * 740. 删除并获得点数
     * 难度: medium
     * 给你一个整数数组 nums ，你可以对它进行一些操作。
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
     * 之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * <p>
     * 示例：
     * 输入：nums = [3,4,2]
     * 输出：6
     * 解释：
     * 删除 4 获得 4 个点数，因此 3 也被删除。
     * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 2 * 104
     * 1 <= nums[i] <= 104
     */


    /*
    题目解析：每删除一个元素可以获得他相同元素的所有值的和，同时会删除所有比该数大一或小一的所有元素的和

    因此，先建立一个sum数组统计对应数字的所有次数的和，然后遍历这个数组，
    若选择了 x，则可以获取 sum[x] 的点数，且无法再选择 x−1 和 x+1。
    接下来再考虑怎么选择哪个删除元素的问题与 打家劫舍的问题一致了，接着就考虑使用动态规划的思路求解
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] sum = new int[maxVal + 1];
        for (int num : nums) {
            sum[num] +=num;
        }
        return rob(sum);

    }

    /**
     * 直接使用 打家劫舍 问题的求解思路和方法
     * 动态规划的解题思路
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int size = nums.length;
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    @Test
    public void test() {
        int[] nums = {2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarn(nums));

    }
}

