package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/15 上午9:25
 */
public class _15 {
    /**
     * 每日一题：2021/4/15
     * 213. 打家劫舍 II
     * 难度: medium
     * <p>
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
     * <p>
     * 示例：
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */

    /*
    题目解析：与 打家劫舍I 不同的是，题目中的所有屋子是连在一起的
    就是说如果第一个房子被偷那么，最后一个房子就不能被偷了，因此 分两种情况dp （0，n-2） 和 （1，n-1）
    可以建立两个大小为n-1的dp数组，一个从0 开始考虑，另一个从1 开始考虑，然后比较两个dp数组最大值，这样时间复杂度与空间复杂度都是是O（n）
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] dp1 = new int[len - 1];
        dp1[0] = nums[0];
        if (len > 2) {
            dp1[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len - 1; i++) {
                dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
            }
        }

        int[] dp2 = new int[len];
        dp2[0] = 0;
        dp2[1] = nums[1];
        if (len > 2) {
            dp2[2] = Math.max(nums[1], nums[2]);
        }
        if (len > 3) {
            for (int i = 3; i < len; i++) {
                dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
            }
        }

        return Math.max(dp1[len - 2], dp2[len - 1]);

    }

    /*
    与上面方法类似，不过不使用额外的存储空间存dp数组的方法
    将遍历区间得到的最大值定义为函数返回
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int val1 = maxValue(nums, 0, nums.length - 2);
        int val2 = maxValue(nums, 1, nums.length - 1);
        return Math.max(val1, val2);


    }

    /**
     * 使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额，将空间复杂度降到 O(1)。
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int maxValue(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;


    }
}
