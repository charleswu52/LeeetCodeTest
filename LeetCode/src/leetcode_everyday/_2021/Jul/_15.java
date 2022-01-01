package leetcode_everyday._2021.Jul;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/15 8:06
 */
public class _15 {
    /**
     * 每日一题：2021/7/15
     * 1846. 减小和重新排列数组后的最大元素
     * 难度: medium
     * <p>
     * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
     * <p>
     * 1. arr 中 第一个 元素必须为 1 。
     * 2. 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），
     * 都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
     * 3. 你可以执行以下 2 种操作任意次：
     * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
     * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
     * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
     *
     *
     * <p>
     * 示例
     * 输入：arr = [2,2,1,2,1]
     * 输出：2
     * 解释：
     * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
     * arr 中最大元素为 2 。
     * <p>
     * 输入：arr = [100,1,1000]
     * 输出：3
     * 解释：
     * 一个可行的方案如下：
     * 1. 重新排列 arr 得到 [1,100,1000] 。
     * 2. 将第二个元素减小为 2 。
     * 3. 将第三个元素减小为 3 。
     * 现在 arr = [1,2,3] ，满足所有条件。
     * arr 中最大元素为 3 。
     * <p>
     * 输入：arr = [1,2,3,4,5]
     * 输出：5
     * 解释：数组已经满足所有条件，最大元素为 5 。
     * <p>
     * 限制：
     * 1 <= arr.length <= 105
     * 1 <= arr[i] <= 109
     */

    /*
    思路1：排序 + 贪心
    时间复杂度：O(nlogn)
    空间复杂度：O(logn)
    原题允许的操作是 重新排序 以及将一个数组元素减小
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 1;
        }
        Arrays.sort(arr);
        arr[0] = 1;
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    /*
    思路2：计数排序 + 贪心
    对思路1时间复杂度的改进，使用计数排序将时间复杂度降至O(n)
    记 arr 的长度为 n。由于第一个元素必须为 1，且任意两个相邻元素的差的绝对值小于等于 1，故答案不会超过 n。
    所以我们只需要对 arr 中值不超过 n 的元素进行计数排序，而对于值超过 n 的元素，由于其至少要减小到 n，我们可以将其视作 n。

    从另一个视角来看，为了尽可能地构造出最大的答案，我们相当于是在用 arr 中的元素去填补自身在 [1,n] 中缺失的元素。
    首先，我们用一个长为 n+1 的数组 cnt 统计 arr 中的元素个数（将值超过 n 的元素视作 n）。然后，从 1 到 n 遍历 cnt 数组，
    若 cnt[i]=0，则说明缺失元素 i，我们需要在后续找一个大于 i 的元素，将其变更为 i。我们可以用一个变量 miss 记录 cnt[i]=0 的出现次数，
    当遇到 cnt[i]>0 时，则可以将多余的 cnt[i]−1 个元素减小，补充到之前缺失的元素上。

    遍历 cnt 结束后，若此时 miss=0，则说明修改后的 arr 包含了 [1,n] 内的所有整数；否则，对于不同大小的缺失元素，
    我们总是优先填补较小的，因此剩余缺失元素必然是 [n−miss+1,n] 这一范围内的 miss 个数，因此答案为 n−miss。

     */

    public int maximumElementAfterDecrementingAndRearranging2(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }

}
