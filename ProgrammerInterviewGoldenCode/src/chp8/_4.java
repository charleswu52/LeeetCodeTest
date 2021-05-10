package chp8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/10 上午11:15
 */
public class _4 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.04. 幂集
     * 难度: medium
     * <p>
     * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * 输入：nums = [0, 2, 3, 4, 5]
     * 输出：0
     * 说明: 0下标的元素为0
     * <p>
     * 输入： nums = [1,2,3]
     * 输出：
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     */



    /*
    思路1：朴素法解决
   比如先加入一个空集让他成为新的子集，然后每遍历一个元素就在原来的子集的后面追加这个值。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 添加空集
        res.add(new ArrayList<>());
        for (int num : nums) {
            //每遍历一个元素就在之前子集中的每个集合追加这个元素，让他变成新的子集
            for (int i = 0, j = res.size(); i < j; i++) {
                // 遍历得到之前的每个子集，重新封装成一个新的子集
                List<Integer> list = new ArrayList<>(res.get(i));
                // 追加新的元素
                list.add(num);
                // 再重新添加到集合中
                res.add(list);
            }
        }
        return res;
    }

    /*
    思路2：回溯法
    可以把它想象成为一颗n叉树，通过DFS遍历这棵n叉树，他所走过的所有路径都是子集的一部分，
     */
    List<List<Integer>> res;
    public List<List<Integer>> subsets2(int[] nums) {
        res = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, 0);
        return res;
    }

    public void backtrack(List<Integer> path, int[] nums, int level) {
        res.add(new ArrayList<>(path));
        for (int i = level; i < nums.length; i++) {
            // 做出选择
            path.add(nums[i]);
            // 回溯
            backtrack(path, nums, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }

    }



}
