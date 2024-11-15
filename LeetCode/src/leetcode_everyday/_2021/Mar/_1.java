package leetcode_everyday._2021.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/1 下午10:42
 */
public class _1 {
    /**
     * 每日一题：2021/3/1
     * 303. 区域和检索 - 数组不可变
     * 难度: easy
     * 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
     * （也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     *
     * <p>
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     *
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     *
     * <p>
     * 数据范围：
     * 0 <= nums.length <= 104
     * -10^5 <= nums[i] <=10^5
     * 0 <= i <= j < nums.length
     * 最多调用 10^4 次 sumRange 方法
     */

    class NumArray {

        List<Integer> integerList;

        public NumArray(int[] nums) {
            integerList = new ArrayList<>();
            for (int num : nums) {
                integerList.add(num);
            }
        }

        public int sumRange(int i, int j) {
            int res = 0;
            for (int k = i; k <= j; k++) {
                res += integerList.get(k);
            }
            return res;
        }
    }

    public void _21_3_1(){

        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))


    }
}
