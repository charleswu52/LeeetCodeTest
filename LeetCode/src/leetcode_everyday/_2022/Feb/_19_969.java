package leetcode_everyday._2022.Feb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/2/19 10:12
 */
public class _19_969 {
    /**
     * 每日一题：2022/2/19
     * <p>
     * 969. 煎饼排序
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
     * <p>
     * 一次煎饼翻转的执行过程如下：
     * <p>
     * 选择一个整数 k ，1 <= k <= arr.length
     * 反转子数组 arr[0...k-1]（下标从 0 开始）
     * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
     * <p>
     * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
     *
     * <p>
     * 示例
     * <p>
     * 输入：[3,2,4,1]
     * <p>
     * 输出：[4,2,4,3]
     * <p>
     * 解释：
     * <p>
     * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
     * 初始状态 arr = [3, 2, 4, 1]
     * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
     * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
     * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
     * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
     *
     * <p>
     * 范围
     * <p>
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= arr.length
     * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
     */

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int n = arr.length; n > 1; n--) {
            int index = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == n - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, n - 1);
            res.add(index + 1);
            res.add(n);
        }
        return res;

    }

    public void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
