package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/18 9:35
 */
public class _18_668 {
    /**
     * 每日一题：2022/5/18
     * <p>
     * 668. 乘法表中第k小的数
     * <p>
     * 难度：hard
     * <p>
     * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
     * <p>
     * 给定高度 m 、宽度 n 的一张 m * n 的乘法表，以及正整数k，你需要返回表中第 k 小的数字。
     * <p>
     * 示例:
     * <p>
     * 输入: m = 3, n = 3, k = 5
     * 输出: 3
     * 解释:
     * 乘法表:
     * 1	2	3
     * 2	4	6
     * 3	6	9
     * <p>
     * 第5小的数字是 3 (1, 2, 2, 3, 3).
     */

    /*
    思路：二分查找
    由于 m 和 n 很大，直接求出所有数字然后找到第 k 小会超出时间限制。不妨考虑一个反向问题：对于乘法表中的数字 x，它是乘法表中第几小的数字？

    求第几小等价于求有多少数字不超过 x。我们可以遍历乘法表的每一行，对于乘法表的第 i 行，其数字均为 i 的倍数，
    因此不超过 x 的数字有 min(⌊x/i⌋,n) 个，所以整个乘法表不超过 x 的数字个数为

    由于 x 越大上式越大，x 越小上式越小，因此我们可以二分 x 找到答案，二分的初始边界为乘法表的元素范围，即 [1,mn]。
     */
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = mid / n * n;
            for (int i = mid / n + 1; i <= m; i++) {
                count += mid / i;
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
