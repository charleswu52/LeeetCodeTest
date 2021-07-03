package leetcode_everyday.Mar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/31 上午8:23
 */
public class _31_2 {
    /**
     * 每日一题：2021/3/31
     * 78. 子集
     * 难度: medium
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 示例：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     */


    /**
     * 思路1:递归
     * 开始假设输出子集为空，
     * 遍历数组，对数组中的每一个整数，每一步都向输出子集中所有子集添加这个整数，并生成新的子集
     * 时间复杂度: O(n*2^n)
     * 空间复杂度: O(n*2^n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // 结果集合
        result.add(new ArrayList<>()); //添加空集
        // 遍历数组
        for (int num : nums) {
            // 对数组中的每个元素，先复制结果集合中已经存在的子集
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subset : result) {
                List<Integer> newSubset = new ArrayList<>(subset);// 复制结果中已经存在的子集
                newSubset.add(num); // 对复制后的子集逐个加入当前的数字
                newSubsets.add(newSubset); // 最后加入到输出结果中
            }
            result.addAll(newSubsets);

        }
        return result;
    }

    /**
     * 思路2:回溯
     * 遍历所有子集长度k(0<=k<=n),通过回溯生成所有给定长度的子集
     * 算法：
     *  对于所有子集长度 0 <= k <= n:
     *     对于开始位置 0 <= start <=n,寻找长度为k的子集：
     *        如果当前子集长度为K，则当前子集构建完成，将其加入输出结果。
     *        把nums[start]加入当前子集
     *        以当前子集为基础在nums[start+1,n-1]中寻找长度为k-1的子集
     *        把nums[start]从当前子集移除
     * 时间复杂度: O(n*2^n)
     * 空间复杂度: O(n*2^n)
     * @param nums
     * @return
     */
    List<List<Integer>> traceResult = new ArrayList<>();
    int n;
    public List<List<Integer>> subsets2(int[] nums) {
        n = nums.length;
        for (int k = 0; k <= n; k++) {
            backtrack(0, k, new ArrayList<>(), nums);
        }
        return traceResult;
    }

    /**
     * 回溯添加子集，运行一次能够构造所有长度为 k 的子集
     * @param start 子集第一个数在nums中最早可以出现的位置
     * @param k 当前需要构造子集的长度
     * @param cur 存储正在构造的子集
     * @param nums 原数组
     */
    public void backtrack(int start, int k, ArrayList<Integer> cur, int[] nums) {
        if (k == 0) {
            traceResult.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <n ; i++) {
            cur.add(nums[i]);
            backtrack(i + 1, k - 1, cur, nums);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * 方法3：字典排序(二进制排序)子集
     * 对于每个子集，nums中的每个数右两个选择，存在或不存在于这个子集。我们用0表示这个数不存在于这个子集，1表示存在。
     * 那么每个子集都对应于一个长度于nums相等的01字符串。
     * 从0到2^n的所有01字符串能够代表nums的所有子集
     * 算法：生成0到2^n的所有01字符串，根据01和存在于不存在的关系逐一构造出所有子集。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // 生成所有01字符串
        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); i++) {
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (bitmask.charAt(j) == '1') {
                    cur.add(nums[j]);
                }
            }
            result.add(cur);
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(subsets3(nums));

    }
}
