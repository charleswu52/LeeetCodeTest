/**
 * @author WuChao
 * @since 2021/3/27 上午10:47
 */
public class _56 {
    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * 难度: medium
     * <p>
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     * <p>
     * 例如：
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     *
     * <p>
     * 数据范围：
     * 2 <= nums.length <= 10000
     */

    /*
    题目解析: 本问题的难度在于要满足规定的时间复杂度O(n)与空间复杂度O(1)

     */

    /**
     * 思路:使用异或分组
     * 详细待补充
     * @param nums
     * @return
     */

    public int[] singleNumbers(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }
}
