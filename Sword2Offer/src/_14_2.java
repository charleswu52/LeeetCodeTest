/**
 * @author WuChao
 * @since 2021/3/13 上午10:01
 */
public class _14_2 {
    /**
     * 剑指 Offer 14- I. 剪绳子
     * 难度: medium
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
     * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * <p>
     * 示例：
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * <p>
     * 数据范围：
     * 2 <= n <= 1000
     */


    /**
     * 这里比 14_1 多的一个问题是大数取余
     * 大数求余的解法：
     * 大数越界： 当 a 增大时，最后返回的 3^a 大小以指数级别增长，可能超出 int32 甚至 int64 的取值范围，导致返回值错误。
     * 大数求余问题： 在仅使用 int32 类型存储的前提下，正确计算 x^a 对 p 求余（即 x^a % p ）的值。
     * 解决方案： 循环求余 、 快速幂求余 ，其中后者的时间复杂度更低。两种方法均基于以下求余运算规则推出：
     *       (xy) % p = [(x % p)(y % p)] % p
     *
     */
    /**
     * 解法1：循环求余
     * x^a %p=[(x^a−1 % p)(x % p)]%p=[(x^a−1 % p)x]%p
     */

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;

        }
        int a = n / 3, b = n % 3;
        long res = 1;
        if (b == 0) {
            for (int i = 0; i < a; i++) {
                res = (res * 3) % 1000000007;
            }
        }
        if (b == 1) {
            for (int i = 0; i < a - 1; i++) {
                res = (res * 3) % 1000000007;
            }
            res = (res * 4) % 1000000007;
        }
        if (b == 2) {
            for (int i = 0; i < a; i++) {
                res = (res * 3) % 1000000007;
            }
            res = (res * 2) % 1000000007;
        }
        return (int) res;
    }

    /**
     * 解法2：快速幂求余
     * 根据求余运算性质可推出：
     * x^a % p=(x^2)^(a/2) % p=(x^2 % p)^(a/2) % p
     * 因此，当a是偶数时，：
     * x^a % p = (x^2 % p)^(a/2) % p
     * a是奇数时：
     * x^a % p = (x % p) (x^(a-1) % p ) % p = [x(x^2 % p)^(a/2)] %p
     */
    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int b = n % 3;
        long res = 1, x = 3;
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) {
                res = (res * x) % 1000000007;
            }
            x = (x * x) % 1000000007;
        }

        if (b == 0) {
            return (int) (res * 3 % 1000000007);
        }
        if (b == 1) {
            return (int) (res * 4 % 1000000007);
        }
        return (int) (res * 6 % 1000000007);
    }


    public void sword2Offer_14_2() {
        int n = 127;
        System.out.println(cuttingRope(n));
        System.out.println(cuttingRope2(n));

    }
}
