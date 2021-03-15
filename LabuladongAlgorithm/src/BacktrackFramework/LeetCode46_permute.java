package BacktrackFramework;

import java.util.LinkedList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/15 下午12:57
 */
public class LeetCode46_permute {
    /**
     * 回溯算法学习框架
     * 案例
     * 46. 全排列
     * 难度：medium
     *
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * <p>
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     */
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        // 记录 路径
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(path, nums);
        return res;
    }

    public void backtrack(LinkedList<Integer> path, int[] nums) {
        //触发结束条件
        if (path.size() == nums.length) {
            res.add(new LinkedList(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (path.contains(nums[i])) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 进入下一层决策
            backtrack(path, nums);
            // 撤销选择
            path.removeLast();// 删除最后添加到元素
        }
    }



    public static void main(String[] args) {
        LeetCode46_permute test = new LeetCode46_permute();
        int[] nums = {1, 2, 3};
        System.out.println(test.permute(nums));

    }
}
