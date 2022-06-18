package leetcode_everyday._2022.Jun;

/**
 * @author WuChao
 * @create 2022/6/17 10:15
 */
public class _17_1089 {
    /**
     * 每日一题：2022/6/17
     * <p>
     * 1089. 复写零
     * <p>
     * 难度：easy
     * <p>
     * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     *
     * 注意：请不要在超过该数组长度的位置写入元素。
     *
     * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     * <p>
     * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
     * <p>
     * 0 <= i, j < nums.length
     * i != j
     * nums[i] - nums[j] == k
     * 注意，|val| 表示 val 的绝对值。
     * <p>
     * 示例
     * <p>
     * 输入：[1,0,2,3,0,4,5,0]
     *
     * 输出：null
     *
     * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     * <p>
     * 范围
     * <p>
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     */

    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (i != n - 1) {
                    for (int j = n-1; j > i; j--) {
                        arr[j] = arr[j - 1];
                    }
                }
                i++;
            }
        }
    }
}
