package leetcode_everyday.Dec;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/12/29 上午10:23
 */
public class _29_1995 {
    /**
     * 每日一题：2021/12/29
     * <p>
     * 1995. 统计特殊四元组
     * <p>
     * 难度：easy
     * <p>
     * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
     * <p>
     * nums[a] + nums[b] + nums[c] == nums[d] ，且
     * a < b < c < d
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,6]
     * <p>
     * 输出：1
     * <p>
     * 解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [3,3,6,4,5]
     * <p>
     * 输出：0
     * <p>
     * 解释：[3,3,6,4,5] 中不存在满足要求的四元组。
     * <p>
     * 范围
     * <p>
     * 4 <= nums.length <= 50
     * 1 <= nums[i] <= 100
     **/

    /*
    思路：哈希
     */
    int[] n1 = new int[105] ;// n1[i] = j 表示一元组中 i 大小 的个数为 j
    int[] n2 = new int[105] ;// n2[i] = j 表示二元组中 i 大小 的个数为 j
    int[] n3 = new int[105] ;// n3[i] = j 表示三元组中 i 大小 的个数为 j

    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();


    public int countQuadruplets(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            res += n3[nums[i]];
            for (int num : set2) {
                if (num + nums[i] < 105) {
                    n3[num + nums[i]] += n2[num];
                }
            }
            for (int num : set1) {
                if (num + nums[i] >= 105) {
                    continue;
                }
                if (!set2.contains(num + nums[i])) {
                    set2.add(num + nums[i]);
                }
                n2[num + nums[i]] += n1[num];
            }
            if (n1[nums[i]]==0){
                set1.add(nums[i]);
            }
            n1[nums[i]]++;

        }

        return res;

    }

}
