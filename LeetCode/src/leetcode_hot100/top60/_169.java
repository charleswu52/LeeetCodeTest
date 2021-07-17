package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/17 8:37
 */
public class _169 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 169. 多数元素
     * 难度：easy
     * <p>
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * <p>
     * 示例：
     * 输入：[3,2,3]
     * 输出：3
     * <p>
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     * <p>
     * 进阶：
     * <p>
     * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */

    /*
    题目解析：

    思路：计数


     */
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int count = 1, maxNum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == maxNum) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                maxNum = nums[i];
                count = 1;
            }
        }
        // 这时候可以直接 return maxNum; 因为假设数组是非空的，并且给定的数组总是存在多数元素。
        // return maxNum;

        // 到上面这样提交是没有问题的

        // 为了健壮性， 上面代码只是找出了数组中出现次数最多的元素但并未记录其实际出现的次数
        // 因此可以再遍历一遍数组 统计该数字在数组中出现的次数
        int realCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxNum) {
                realCount++;
            }
        }
        return -1;

    }
}
