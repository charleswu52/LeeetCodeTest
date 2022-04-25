package leetcode_everyday._2022.Apr;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/4/25 9:17
 */
public class _25_398 {
    /**
     * 每日一题：2022/4/25
     * <p>
     * 398. 随机数索引
     * <p>
     * 难度：medium
     * <p>
     * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
     *
     * 注意：
     * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
     * <p>
     * 示例
     * <p>
     * int[] nums = new int[] {1,2,3,3,3};
     * Solution solution = new Solution(nums);
     *
     * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
     * solution.pick(3);
     *
     * // pick(1) 应该返回 0。因为只有nums[0]等于1。
     * solution.pick(1);
     * <p>
     */

    /*
    思路1：map + 随机数取样
     */
    class Solution {

        private Map<Integer, List<Integer>> map;
        private Random random;
        public Solution(int[] nums) {
            this.map = new HashMap<>();
            this.random = new Random();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> orDefault = map.getOrDefault(nums[i], new ArrayList<>());
                orDefault.add(i);
                map.put(nums[i], orDefault);
            }
        }

        public int pick(int target) {
            if (!map.containsKey(target)) {
                return -1;
            }
            int size = map.get(target).size();
            return map.get(target).get(random.nextInt(size));

        }
    }

    class Solution2 {

        private int[] nums;
        private Random random;
        public Solution2(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        public int pick(int target) {
            int n = this.nums.length, ans = 0;
            for (int i = 0, cnt = 0; i < n; i++) {
                if (nums[i] == target) {
                    cnt++;
                    if (random.nextInt(cnt) == 0) {
                        ans = i;
                    }
                }
            }
            return ans;

        }
    }
}
