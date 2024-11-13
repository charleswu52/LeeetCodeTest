package leetcode_everyday._2024.Nov;

/**
 * @author charles
 * @create 2024/11/13 8:06
 */
public class _13_3261 {
    /**
     * 每日一题：2024/11/13
     * 3261.统计满足 K 约束的子字符串数量 II
     * 给你一个 二进制 字符串 s 和一个整数 k。
     * <p>
     * 另给你一个二维整数数组 queries ，其中 queries[i] = [li, ri] 。
     * <p>
     * 如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
     * <p>
     * 字符串中 0 的数量最多为 k。
     * 字符串中 1 的数量最多为 k。
     * 返回一个整数数组 answer ，其中 answer[i] 表示 s[li..ri] 中满足 k 约束 的子字符串的数量。
     * https://leetcode.cn/problems/count-substrings-that-satisfy-k-constraint-ii/solutions/2976328/tong-ji-man-zu-k-yue-shu-de-zi-zi-fu-chu-f4kb/
     */
    public long[] countKConstraintSubstrings(String S, int k, int[][] queries) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] left = new int[n];
        long[] sum = new long[n + 1];
        int[] cnt = new int[2];
        int l = 0;
        for (int i = 0; i < n; i++) {
            cnt[s[i] & 1]++;
            while (cnt[0] > k && cnt[1] > k) {
                cnt[s[l++] & 1]--;
            }
            left[i] = l; // 记录合法子串右端点 i 对应的最小左端点 l
            // 计算 i-left[i]+1 的前缀和
            sum[i + 1] = sum[i] + i - l + 1;
        }

        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int ql = queries[i][0];
            int qr = queries[i][1];
            // 如果区间内所有数都小于 ql，结果是 j=qr+1
            int j = lowerBound(left, ql - 1, qr + 1, ql);
            ans[i] = sum[qr + 1] - sum[j] + (long) (j - ql + 1) * (j - ql) / 2;
        }
        return ans;
    }

    // 返回在开区间 (left, right) 中的最小的 j，满足 nums[j] >= target
    // 如果没有这样的数，返回 right
    // 原理见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int left, int right, int target) {
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right;
    }


}
