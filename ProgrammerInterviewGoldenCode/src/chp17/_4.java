package chp17;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/6/4 上午9:43
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.04. 消失的数字
     * 难度: easy
     * <p>
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 注意：本题相对书上原题稍作改动
     *
     * <p>
     * 示例:
     * 输入：[3,0,1]
     * 输出：2
     *
     * 输入：[9,6,4,2,3,5,7,0,1]
     * 输出：8
     * <p>
     * 数据范围：
     */

    /*
    题目要求在 O(n) 的时间复杂度内完成
     */

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int[] save = new int[n + 1];
        Arrays.fill(save, -1);
        for (int num : nums) {
            save[num] = num;
        }
        int i;
        for (i= 0; i < n + 1; i++) {
            if (save[i] == -1) {
                break;
            }
        }
        return i;
    }

    public int missingNumber2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        int total = (n + 1) * n / 2;
        return total - sum;

    }
}
