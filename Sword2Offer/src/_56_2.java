import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/28 上午10:47
 */
public class _56_2 {
    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * 难度: medium
     * <p>
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * <p>
     * 例如：
     * 输入：nums = [9,1,7,9,7,9,7]
     * 输出：1
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 10000
     * 1 <= nums[i] < 2^31
     */


    /**
     * 方法1：遍历统计
     * 使用 与运算 ，可获取二进制数字 num 的最右一位 n_1： n1= num & i
     * 再配合 无符号右移操作 ，可获取 num 所有位的值（即 n_1~ n_32 ）：
     *              num=num>>>1
     * 因此建立一个长度为 32 的数组 counts ，通过以上方法可记录所有数字的各二进制位的 1 的出现次数；
     * 遍历统计完成后将 counts 各元素对 3 求余，则结果为 “只出现一次的数字” 的各二进制位。
     * 最后利用 左移操作 和 或运算 ，可将 counts 数组中各二进位的值恢复到数字 res 上（循环区间是 i∈[0,31] ）。
     * 最终返回 res 即可。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        // 统计每一位上的二进制数的和
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        // 利用左移和或运算将求和后的结果恢复到原来的数字
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }

    // 还有一种使用状态自动机的方法来解决 但先对状态机解决问题的思路不是很了解，到了解这种解法的思路时再回来复习这种解法

    @Test
    public void test() {
        int[] nums = {9, 1, 7, 9, 7, 9, 7};
        System.out.println(singleNumber(nums));
    }

}
