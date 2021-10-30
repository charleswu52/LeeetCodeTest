package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/10/30 上午10:56
 */
public class _30_260 {
    /**
     * 每日一题：2021/10/30
     * <p>
     * 3260. 只出现一次的数字 III
     * <p>
     * 难度：medium
     * <p>
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * <p>
     * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     * <p>
     * 示例1：
     * <p>
     * 输入：nums = [1,2,1,3,2,5]
     * <p>
     * 输出：[3,5]
     * <p>
     * 解释：[5, 3] 也是有效的答案。
     * <p>
     * 范围
     * <p>
     * 2 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
     */

    /*
    思路：位运算 异或
    利用除答案以外的元素均出现两次，我们可以先对 nums 中的所有元素执行异或操作，得到 sum，sum 为两答案的异或值（ sum 必然不为 0）。
    然后取 sum 二进制表示中为 1 的任意一位 k，sum 中的第 k 位为 1 意味着两答案的第 k 位二进制表示不同。

    对 nums 进行遍历，对第 k 位分别为 0 和 1 的元素分别求异或和（两答案必然会被分到不同的组），即为答案。
     */
    public int[] singleNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int k = -1;
        for (int i = 31; i >= 0 && k == -1; i--) {
            if (((sum >> i) & 1) == 1) {
                k = i;
            }
        }
        int[] ans = new int[2];
        // 再次利用异或的性质 进行异或分组
        for (int num : nums) {
            if ((((num >> k) & 1) == 1)) {
                ans[1] ^= num;
            } else {
                ans[0] ^= num;
            }
        }
        return ans;

    }
}
