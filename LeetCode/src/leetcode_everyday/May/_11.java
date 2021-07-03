package leetcode_everyday.May;

/**
 * @author WuChao
 * @since 2021/5/11 上午8:04
 */
public class _11 {
    /**
     * 每日一题：2021/5/11
     * 1734. 解码异或后的排列
     * 难度: medium
     * <p>
     * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
     *
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
     *
     * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
     *
     * <p>
     * 示例：
     * 输入：encoded = [3,1]
     * 输出：[1,2,3]
     * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
     * <p>
     * 数据范围：
     * 3 <= n < 105
     * n 是奇数。
     * encoded.length == n - 1
     */

    /*
    思路：充分利用题目所给条件以及异或运算的性质
    首先应该得到编码前原perm数组的第一个元素。如果能得到数组 perm 的全部元素的异或运算结果，以及数组 perm 除了 perm[0] 以外
    的全部元素的异或运算结果，即可得到 perm[0] 的值。数组perm 的全部元素的异或运算结果即为从 1 到 n 的全部正整数的异或运算结果。
    数组 encoded 的所有下标为奇数的元素的异或运算结果即为数组 perm 除了 perm[0] 以外的全部元素的异或运算结果。
    根据 total 和 odd 的值，即可计算得到 perm[0] 的值。
    由于 perm[0] 已知，因此对 ii 从 11 到 n-1n−1 依次计算 perm[i] 的值，即可得到原始数组 perm。


     */
/*
本题目难点在于分析encode数组的特点得到原perm[0]的结果
 */
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        int odd = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            odd ^= encoded[i];
        }
        int[] perm = new int[n];
        perm[0] = total ^ odd;
        for (int i = 1; i < n; i++) {
            perm[i] = perm[i-1] ^ encoded[i-1];
        }
        return perm;
    }
}
