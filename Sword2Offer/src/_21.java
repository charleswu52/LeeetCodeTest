import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/15 上午10:56
 */
public class _21 {
    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 难度: easy
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * <p>
     * 示例：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 50000
     * 1 <= nums[i] <= 10000
     */

    /**
     * 首尾双指针的方式
     */
    public int[] exchange(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 0 && nums[right] % 2 == 1) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            } else if (nums[left] % 2 == 1) {
                left++;
            } else if (nums[right] % 2 == 0) {
                right--;
            }
        }
        return nums;
    }

    /**
     * 快慢指针的方式
     */
    public int[] exchange2(int[] nums) {
     if (nums.length < 2) {
            return nums;
        }
        int low = 0, fast = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                int temp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = temp;
                low++;
            }
            fast++;
        }
        return nums;

    }


    public void sword2Offer_21() {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange(nums)));
        System.out.println(Arrays.toString(exchange2(nums)));
    }

}
