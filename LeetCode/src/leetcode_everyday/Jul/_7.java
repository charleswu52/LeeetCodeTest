package leetcode_everyday.Jul;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/7 8:33
 */
public class _7 {
    /**
     * 每日一题：2021/7/7
     * 1711. 大餐计数
     * 难度: medium
     * <p>
     * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
     *
     * 你可以搭配 任意 两道餐品做一顿大餐。
     *
     * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。
     * 结果需要对 109 + 7 取余。
     *
     * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
     * <p>
     * 示例
     * 输入：deliciousness = [1,3,5,7,9]
     * 输出：4
     * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
     * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
     *
     *
     * 输入：deliciousness = [1,1,1,3,3,3,7]
     * 输出：15
     * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
     * <p>
     *
     * <p>
     * 限制：
     * 1 <= deliciousness.length <= 105
     * 0 <= deliciousness[i] <= 220
     */

    /*
    题目解析：
    给出一个数组，求数组中任意两个数字之和是2的幂的所有可能的组合
    思路：
    最简单的思路直接暴力，枚举各种组合判断和是否是2的幂，无脑做法，时间复杂度 O(n^2) 肯定超时

    改进： 哈希表
    使用 哈希表 存储每个元素出现的次数，遍历某个元素时在哈希表中寻找与当前元素的和等于2的幂的元素个数，然后使用当前元素更新哈希表。

    对每个数来说，与它和是2的幂的数有多少个也得遍历，但是遍历上界是什么呢，就需要在遍历原数组的时候记录最大值
    令 maxVal 表示数组 deliciousness 中的最大元素，则数组中的任意两个元素之和都不会超过 maxVal×2。
    令 maxSum=maxVal×2，则任意一顿大餐的美味程度之和为不超过 maxSum 的某个 2 的幂。

    知道这些前提后，再回头遍历数组中每个元素，对每个元素在遍历所有可能的2的幂的结果，求出组合数

     */

    public int countPairs(int[] deliciousness) {
        int ans = 0;
        int mod = 1000000007;
        /*
        O(n^2)做法，超时
        for (int i = 0; i < deliciousness.length-1; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                if (isPowerOf2(deliciousness[i] + deliciousness[j])) {
                    ans = (ans + 1) % mod;
                }
            }
        }
         */

        int maxVal = Arrays.stream(deliciousness).max().getAsInt();
        int maxSum = 2 * maxVal;
        Map<Integer, Integer> map = new HashMap<>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                ans = (ans + count) % mod;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        return ans;
    }

    public boolean isPowerOf2(int num) {
        double ans = Math.log10(num) / Math.log10(2);
        return ans == (int) ans;
    }

    @Test
    public void test() {
        int num = 6;
        System.out.println(isPowerOf2(num));

    }
}
