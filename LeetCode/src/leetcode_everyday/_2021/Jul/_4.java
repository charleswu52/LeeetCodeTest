package leetcode_everyday._2021.Jul;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/4 8:37
 */
public class _4 {
    /**
     * 每日一题：2021/7/4
     * 645. 错误的集合
     * 难度: medium
     * <p>
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
     * 导致集合 丢失了一个数字 并且 有一个数字重复 。
     * <p>
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * <p>
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     * 示例 2：
     * <p>
     * 输入：nums = [1,1]
     * 输出：[1,2]
     *
     * <p>
     * 限制：
     * 2 <= nums.length <= 104
     * 1 <= nums[i] <= 104
     */

    /*

     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int lost = -1, repeat = -1;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                repeat = nums[i];
                nums[i] = 0;
                break;
            }
        }
        int total = n * (n + 1) / 2;
        int sum = Arrays.stream(nums).sum();

        lost = total - sum;

        return new int[]{repeat, lost};
    }

    @Test
    public void test() {
        int[] nums = {2,2};
        System.out.println(Arrays.toString(findErrorNums(nums)));

    }
}
