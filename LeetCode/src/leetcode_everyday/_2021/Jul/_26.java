package leetcode_everyday._2021.Jul;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/26 8:17
 */
public class _26 {
    /**
     * <p> 每日一题：2021/7/26 </p>
     * <p> 1713. 得到子序列的最少操作次数 </p>
     * <p> 难度: hard </p>
     *
     * <p>
     * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
     *
     * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。
     * 你可以在数组最开始或最后面添加整数。
     *
     * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
     *
     * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
     * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
     *
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：target = [5,1,3], arr = [9,4,2,3,4]
     * 输出：2
     * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
     *
     *
     * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
     * 输出：3
     *
     * </p>
     *
     * <p> 范围 </p>
     * <p>
     * 1 <= target.length, arr.length <= 105
     * 1 <= target[i], arr[i] <= 109
     * target 不包含任何重复元素。
     *
     * </p>
     */

    /*
    思路：贪心 + 二分查找
    记数组 target 的长度为 n，数组 arr 的长度为 m。

    根据题意，target 和 arr 这两个数组的公共子序列越长，需要添加的元素个数也就越少。
    因此最少添加的元素个数为 n 减去两数组的最长公共子序列的长度。

    因此问题就变成了求最长公共子序列的问题。这里根据数组特点，target数组的元素互不相同，因此可以用一个哈希表记录target中每个元素所处的下标，
    然后将arr数组变成每个元素对应target数组的每个下标，同时target 也新用一个下标数组表示。
    因此求原数组的最长公共子序列等价于求上述转换后两数组的最长公共子序列。
     */

    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int tLen = target.length, aLen = arr.length;
        // 1. 将target的元素和对应的下标放入 map
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        // 2. 将arr中与target元素相等的数的下标放入数组
        int[] index = new int[aLen];
        int idx = 0;
        for (int i = 0; i < aLen; i++) {
            if (map.containsKey(arr[i])) {
                index[idx++] = map.get(arr[i]);
            }
        }

        // 3.调用最长递增公共子串的代码
        int commonLen = lengthOfLIS(index, idx);
        return tLen - commonLen;

    }

    /**
     * 计算数组的递增公共子串
     * @see leetcode_hot100.top80._300
     * @param nums
     * @param n
     * @return
     */
    public int lengthOfLIS(int[] nums,int n) {
        if (n == 0) {
            return 0;
        }
        int res = 1;
        // dp[i]为以i位置的元素结尾所构成的递增子序列长度。
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int mid = (i + j) >> 1;
                if (dp[mid] >= num) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            }
            dp[i] = num;
            if (j == res) {
                res++;
            }
        }
        return res;
    }
}
