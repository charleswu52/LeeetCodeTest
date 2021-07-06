package leetcode_hot100.top20;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/6 12:21
 */
public class _39 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 39. 组合总和
     * 难度：medium
     * <p>
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     *
     * <p>
     * 示例
     * <p>
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     * [7],
     * [2,2,3]
     * ]
     * <p>
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     * <p>
     * 范围
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     */

    /*
    思路： 回溯法
     */

    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        backtrace(candidates, path, 0, target);
        return res;
    }

    public void backtrace(int[] candidates,List<Integer> path, int idx, int target) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 直接跳过
        backtrace(candidates, path, idx + 1, target);
        // 选择当前的数
        if (target - candidates[idx] >= 0) {
            path.add(candidates[idx]);
            // 每一个元素可以重复使用，所以下一次的起点仍然是 idx
            backtrace(candidates, path, idx, target - candidates[idx]);
            path.remove(path.size() - 1);
        }
    }
}
