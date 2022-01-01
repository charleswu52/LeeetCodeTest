package leetcode_everyday._2021.Mar;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/3/31 上午8:00
 */
public class _31 {
    /**
     * 每日一题：2021/3/31
     * 90. 子集 II
     * 难度: medium
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列
     *
     * <p>
     * 示例：
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     */

    /*
    题目解析：与子集I题目之处在于nums中可能存在相同的元素
    因此在实现时，可以先将数组排序，迭代时，若发现没有选择上一个数且当前数组与上一个数相同，则可以跳过当前生成的子集
     */
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // 先对原数组元素排序
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            temp.clear();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    temp.add(nums[i]);
                }
            }
            if (flag) {
                result.add(new ArrayList<>(temp));
            }
        }
        return result;
    }

    /**
     * 思路2：递归的方式
     * 类似与 子集I中的方法1 的思路
     * 先将当前求得的所有子集拷贝一份，然后将nums[i]添加到拷贝的子集中。
     * 对于重复的元素，可以先用Set集合保存中间结果，这样可以过滤掉一部分重复结果，同时在开始之前需要对原数组进行排序，
     * 这样可以限制元素添加到集合中的顺序避免重复集合的出现
     */

    Set<List<Integer>> ans = new HashSet<>();// 结果集
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        ans.add(new ArrayList<>());// 添加空集合
        Arrays.sort(nums);// 在递归求解之前先对数组进行排序
        getSets(new ArrayList<List<Integer>>(),0,nums);
        return new ArrayList<>(ans);

    }

    /**
     * 递归求幂集
     * @param help 存放从当前ans复制过来的内容
     * @param i
     * @param nums
     */
    private void getSets(List<List<Integer>> help, int i, int[] nums) {
        if (i == nums.length) {
            return; // 递归出口
        }
        for (List<Integer> e : ans) {
            help.add(e);
        }
        for (List<Integer> E : help) {
            List<Integer> temp = new ArrayList<>(E);
            temp.add(nums[i]);
            ans.add(temp);
        }
        help.clear();//清空help集合
        getSets(help, i + 1, nums);
    }


        @Test
    public void test() {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));

    }
}
