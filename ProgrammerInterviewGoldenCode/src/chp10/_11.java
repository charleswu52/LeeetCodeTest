package chp10;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/27 上午8:33
 */
public class _11 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.11. 峰与谷
     * 难度: medium
     * <p>
     * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 4, 2, 3, 4, 6}中，
     * {8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     * <p>
     * 示例:
     * 输入: [5, 3, 1, 2, 3]
     * 输出: [5, 1, 3, 2, 3]
     * <p>
     * 数据范围：
     * nums.length <= 10000
     */

    /*
    思路：一次遍历，根据奇偶数判断交换
    偶数个时, 峰peak的判断条件
    if i % 2 == 0:
        if nums[i] > nums[i + 1]:
            nums[i], nums[i + 1] = nums[i + 1], nums[i]
    奇数个时，谷valley的判断条件
    else:
        if nums[i] < nums[i + 1]:
            nums[i], nums[i + 1] = nums[i + 1], nums[i]

     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        int t = 0;
        for (int i = 0; i < n-1 ; i++) {
            if (i % 2 == 0) {

                if (nums[i] > nums[i + 1]) {
                    t = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = t;
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    t = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = t;
                }
            }
        }


    }

    @Test
    public void test() {
        int[] nums = {3,5,2,1,6,4};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
