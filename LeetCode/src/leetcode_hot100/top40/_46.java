package leetcode_hot100.top40;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/7 9:40
 */
public class _46 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 46. 全排列
     * 难度：medium
     * <p>
     * 给定一个 不含重复 数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <p>
     * 示例
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * <p>
     * 范围
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums 中的所有整数 互不相同
     */

    /*
    基本的，回溯题，DFS
     */

    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrace(path, nums);
        return res;

    }

    public void backtrace(List<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtrace(path, nums);
            path.remove(path.size() - 1);
        }
    }

}
