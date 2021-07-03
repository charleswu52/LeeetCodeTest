package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/19 上午8:05
 */
public class _19 {
    /**
     * 每日一题：2021/4/19
     * 27. 移除元素
     * 难度: easy
     * <p>
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     *
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * 你可以想象内部操作如下:
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *       print(nums[i]);
     * }
     *
     *
     * <p>
     * 示例：
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     *
     *
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        if (len == 0) {
            return len;
        }
        if (len == 1) {
            return nums[0] == val ? 0 : 1;
        }
        int left = 0, right = len - 1;
        while (right > 0) {
            if (nums[right] != val) {
                break;

            } else {
                right--;
            }
        }
        while (left < right) {
            if (nums[left] == val) {
                while (nums[right] == val && right > 0) {
                    right--;
                }
                if (left < right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                } else {
                    return left;
                }
            }
            left++;
        }
        return nums[left] == val ? left : left + 1;

    }
}
