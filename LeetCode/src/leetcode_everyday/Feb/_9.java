package leetcode_everyday.Feb;

public class _9 {
    /**
     * 每日一题：2021/2/9
     * 992. K 个不同整数的子数组
     * 难度: hard
     * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
     * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
     * 返回 A 中好子数组的数目。
     * <p>
     * 示例：
     * 输入：A = [1,2,1,2,3], K = 2
     * 输出：7
     * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
     * <p>
     * 数据范围：
     * 1 <= A.length <= 20000
     * 1 <= A[i] <= A.length
     * 1 <= K <= A.length
     */

    /*
    题目解析：
    把原问题转换成为容易求解的问题
    把「恰好」改成「最多」就可以使用双指针一前一后交替向右的方法完成，这是因为对于每一个确定的左边界，
    最多包含 K 种不同整数的右边界是唯一确定的，并且在左边界向右移动的过程中，右边界或者在原来的地方，或者在原来地方的右边。

    而「最多存在 K 个不同整数的子区间的个数」与「恰好存在 K 个不同整数的子区间的个数」的差
    恰好等于「最多存在 K - 1 个不同整数的子区间的个数」。

    因此原问题转变成求解「最多存在 K 个不同整数的子区间的个数」与 「最多存在 K - 1 个不同整数的子区间的个数」，它们其实是一个问题。
     */

    /*
    思路1：双指针（滑动窗口）
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        /*
        atMostWithKDistinct(A, K) ，表示「最多存在 KK 个不同整数的子区间的个数」。
         */
        return atMostWithDistinct(A, K) - atMostWithDistinct(A, K - 1);

    }

    /**
     * 最多包含K个不同整数的子区间的个数
     * @param A
     * @param K
     * @return
     */
    public int atMostWithDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0, right = 0;
        // 区间 [left,right)里包含不同整数的个数
        int count = 0;
        int res = 0;
        // 保证区间[left,right)里包含不同整数的个数小于等于k
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // 区间 [left,right)的长度就是对结果的贡献
            res += right - left;
        }
        return res;

    }

    public void _21_2_9() {
        int[] A = {1, 2, 1, 2, 3};
        int K = 3;
        System.out.println(subarraysWithKDistinct(A, K));
    }
}
