package leetcode_everyday.May;

/**
 * @author WuChao
 * @since 2021/5/12 上午8:41
 */
public class _12 {
    /**
     * 每日一题：2021/5/12
     * 1310. 子数组异或查询
     * 难度: medium
     * <p>
     * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
     *
     * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     *
     * 并返回一个包含给定查询 queries 所有结果的数组。
     * <p>
     * 示例：
     * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * 输出：[2,7,14,8]
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001
     * 3 = 0011
     * 4 = 0100
     * 8 = 1000
     * 查询的 XOR 值为：
     * [0,1] = 1 xor 3 = 2
     * [1,2] = 3 xor 4 = 7
     * [0,3] = 1 xor 3 xor 4 xor 8 = 14
     * [3,3] = 8
     * <p>
     * 数据范围：
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] < arr.length
     */

    /*
    思路1：朴素解法
    按照题目要求直接对每一组query中下标范围对原数组中对应的数做异或操作
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = 0;
            int left = queries[i][0];
            int right = queries[i][1];
            for (int j = left; j <= right; j++) {
                temp ^= arr[j];
            }
            ans[i] = temp;
        }
        return ans;
    }

    /*
    思路2：
    前缀异或
    由于有 m 个查询，对于每个查询都要计算结果，因此应该优化每个查询的计算时间。理想情况下，每个查询的计算时间应该为 O(1)。
    为了将每个查询的计算时间从 O(n) 优化到 O(1)，需要计算数组的前缀异或。
    定义前缀数组xor,xor[i]表示从arr[0]到arr[i-1]的异或运算结果

     */
    public int[] xorQueries2(int[] arr, int[][] queries) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        xor[0] = 0;
        for (int i = 1,j=0; i <= len; i++,j++) {
            xor[i] = xor[i - 1] ^ arr[j];
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            if (left == 0) {
                res[i] = xor[right + 1];
            } else {
                res[i] = xor[left] ^ xor[right + 1];
            }
        }
        return res;
    }

    }
