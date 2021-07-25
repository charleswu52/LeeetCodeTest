package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/25 9:14
 */
public class _338 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 338. 比特位计数
     * 难度：easy
     * <p>
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * <p>
     * 输入: 2
     * 输出: [0,1,1]
     * <p>
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * <p>
     * 进阶:
     * <p>
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     * <p>
     */

    /*
    题目解析：
    不允许使用内置函数，而且要求时间复杂度为O(n)
     */
    /*
    思路1： 位运算

     */
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            bits[i] = countOnes(i);
        }
        return bits;

    }

    /*
    该运算的时间复杂度是 O(logn)
     */
    public int countOnes(int x) {
        int res = 0;
        while (x > 0) {
            x &= (x - 1);
            res++;
        }
        return res;
    }

    /*
    思路1的时间复杂度是O(nlogn)
    因为包含了之前的一些重复项计算，所以改进使用动态规划的思想
    思路2：根据奇偶性判断
        1.奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
        2.偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。
                因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。

     */
    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2];

            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res;

    }
}
