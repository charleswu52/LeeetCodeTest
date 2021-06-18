package leetcodetest.Jun;

/**
 * @author WuChao
 * @create 2021/6/18 8:40
 */
public class _18 {
    /**
     * 每日一题：2021/6/18
     * 483. 最小好进制
     * 难度: hard
     * <p>
     * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
     * <p>
     * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
     *
     * <p>
     * 示例:
     * 输入："13"
     * 输出："3"
     * 解释：13 的 3 进制是 111。
     * <p>
     * 输入："4681"
     * 输出："8"
     * 解释：4681 的 8 进制是 11111。
     * <p>
     * 输入："1000000000000000000"
     * 输出："999999999999999999"
     * 解释：1000000000000000000 的 999999999999999999 进制是 11。
     * <p>
     * 数据范围：
     * n的取值范围是 [3, 10^18]。
     * 输入总是有效且没有前导 0。
     */


    /*
    思路：数学分析
    一个数用k进制表示，全1 :
       n = k^0 + k^1 + k^2 + ... + k^m
       是一个等比数列求和公式  =>  n = (k^m - 1)/(k - 1)
    这里只有n可知，k和m都是未知，要求最小的k，就得使m尽可能大，k>=2,所以 m 最大值为 m <= log_2^n +1
    其中 n ∈ [3, 10^18] => m最小为2
     */
    public String smallestGoodBase(String n) {
        // n 用字符串表示 范围 是[3,10^18],可以用long表示
        long num = Long.parseLong(n);
        // m的范围
        int mMin = 2, mMax = (int)(Math.log(num + 1) / Math.log(2));  // 换底公式
        // 枚举m的范围
        for (int m = mMax; m >= mMin ; m--) {
            // 再找k ，没法用公式求，也只能遍历，用二分快一点
            long left = 2, right = (long) Math.pow(num, 1.0 / (m - 1)) + 1;
            while (left < right) {
                long mid = left + ((right - left) >> 1), sum = 0;
                // 等比数列求和
                for (int j = 0; j < m; j++) {
                    sum = sum * mid + 1;
                }
                if (sum == num) {
                    return String.valueOf(mid);
                } else if (sum < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return String.valueOf(num - 1);



    }


}
