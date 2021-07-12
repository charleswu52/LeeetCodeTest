package leetcode_hot100.top40;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/12 9:30
 */
public class _78 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 78. 子集
     * 难度：hard
     * <p>
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 示例
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * <p>
     * 范围：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     */

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> list = new ArrayList<>(res.get(i));
                list.add(num);
                res.add(new ArrayList<>(list));
            }
        }
        return res;
    }


}
