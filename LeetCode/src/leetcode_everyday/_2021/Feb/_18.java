package leetcode_everyday._2021.Feb;

public class _18 {
    /**
     * 每日一题：2021/2/18
     * 995. K 连续位的最小翻转次数
     * 难度: hard
     * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     * <p>
     * 输入：A = [0,0,0,1,0,1,1,0], K = 3
     * 输出：3
     * 解释：
     * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
     * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
     * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
     * <p>
     * 数据范围：
     * 给1 <= A.length <= 30000
     * 1 <= K <= A.length
     */

    /*
    暴力滑动窗口（超时）
    从 A[0]开始考虑，若 A[0]=0，则必定要翻转从位置 0 开始的子数组；若 A[0]=1，则不翻转从位置 0 开始的子数组。
    按照这一策略，我们从左到右地执行这些翻转操作。由于翻转操作是唯一的，若最终数组元素均为 1，则执行的翻转次数就是最小的。
    用 N 表示数组 A 的长度。若直接模拟上述过程，复杂度将会是 O(NK) 的。
     */
    public int minKBitFlips(int[] A, int K) {
        return 0;
    }

    /*
    官方题解：差分数组
    考虑不去翻转数字，而是统计每个数字需要翻转的次数。对于一次翻转操作，相当于把子数组中所有数字的翻转次数加 1。

    这启发我们用差分数组的思想来计算当前数字需要翻转的次数。我们可以维护一个差分数组 diff，其中 diff[i] 表示两个相邻元素 A[i-1] 和 A[i]的
    翻转次数的差，对于区间 [l,r]，将其元素全部加 1，只会影响到 l 和 r+1 处的差分值，故 diff[l] 增加 1，diff[r+1] 减少 1。

    通过累加差分数组可以得到当前位置需要翻转的次数，我们用变量 revCnt 来表示这一累加值。
    遍历到 A[i] 时，若 A[i]+revCnt 是偶数，则说明当前元素的实际值为 0，需要翻转区间 [i,i+K−1]，我们可以直接将 revCnt 增加 1，diff[i+K] 减少 1。
    注意到若 i+K>ni+K>n 则无法执行翻转操作，此时应返回 -1。
     */
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }


    /*
    【其他题解】滑动窗口：一次遍历，常数空间

    题目解析：通过观察，我们可以隐约发现，每次应该在第一个0元素位置开始反转，如果能够使得整个数组不存在0，即返回res作为反转次数。

    Tips:
    1. 当反转次数为奇数次，元素会由0 -> 1，1 -> 0; 当反转次数为偶数次，元素不变;
    2. 由于我们关注元素的奇偶性质，所以我们可以在处理过的元素中+2，这样可以保持性质不变；
    3. 同时我们还需要关注同一元素在窗口内反转了几次，用windowFlip表示。这样的话如果元素为0，则满足 windowFlip % 2 == A[i]
     */
    public int minKBitFlips3(int[] A, int K) {
        int len = A.length, res = 0, windowFlip = 0;
        for (int i = 0; i < len; i++) {
            if (i - K >= 0 && A[i - K] > 1) {
                A[i - K] -= 2; // 防止改变input
                windowFlip--;
            }
            if (windowFlip % 2 == A[i]) { //反转奇数次后是1 或者 反转偶数次后是0
                if (i + K - 1 >= len)
                    return -1;
                A[i] += 2;
                windowFlip++;
                res++;
            }

        }
        return res;
    }

    public void _21_2_18() {

        int[] A = {0, 0, 0, 1, 0, 1, 1, 0};
        int K = 3;
        System.out.println(minKBitFlips2(A, K));
        System.out.println(minKBitFlips3(A, K));
    }


}
