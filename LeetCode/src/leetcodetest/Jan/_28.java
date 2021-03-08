package leetcodetest.Jan;

import java.util.Arrays;

public class _28 {
    /**
     * 每日一题：2021/1/28
     * 724. 寻找数组的中心索引
     * 难度: easy
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
     * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     * <p>
     * 示例：
     * 输入：
     * nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
     * 同时, 3 也是第一个符合要求的中心索引。
     * <p>
     * 数据范围：
     * nums 的长度范围为 [0, 10000]。
     * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
     */


    // 双指针解法的，但存在问题就是不能仅仅通过比较大小来移动指针
    public int pivotIndex2(int[] nums) {
        int res = -1;
        int n = nums.length;
        if (n < 3) {
            return -1;
        }
        int leftIndex = 0, rightIndex = n - 1, leftSum = 0, rightSum = 0;
        while (leftIndex < rightIndex) {
            if (leftSum < rightSum) {
                leftSum += nums[leftIndex++];
            } else if (leftSum > rightSum) {
                rightSum += nums[rightIndex--];
            } else {
                leftSum += nums[leftIndex++];
                rightSum += nums[rightIndex--];
            }
        }
        System.out.println(leftIndex + " " + rightIndex);
        if (leftSum == rightSum && leftIndex == rightIndex) {
            res = leftIndex;
        }
        return res;
    }

    // 更新思路：先算出总和total，只从一侧遍历
    // 设当前指针所在元素是num[i],
    // 左侧元素和为sum,右侧元素和为 total-sum-num[i],中心索引满足：sum=total-sum-num[i]  ==> 2*sum+num[i] = total
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return -1;
        }
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }


    public void _21_1_28() {
        int[] nums = {-1, -1, -1, -1, -1, 0};
        System.out.println(pivotIndex(nums));

    }
}
