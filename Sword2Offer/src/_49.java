import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/24 上午11:25
 */
public class _49 {
    /**
     * 剑指 Offer 49. 丑数
     * 难度: medium
     * <p>
     *
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * <p>
     * 示例：
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     *
     *
     * <p>
     * 数据范围：
     * 1 是丑数。
     * n 不超过1690
     */

    /*
    题目分析：
    丑数的递推性质： 丑数只包含因子 2, 3, 5 ，因此有 “丑数 == 某较小丑数 × 某因子” （例如：10 =5×2）。
    已知长度为n的丑数序列 x1,x2,...,xn,求第n+1个丑数x_n+1.根据递推性质，丑数x_n+1只可能是三种情况之一
        x_n+1 = min(x_a*2,x_b*3,x_c*5)
    由于x_n+1是最接近x_n的丑数，因此索引a,b,c需要满足以下条件：
        x_a*2 > x_n >= x_a-1 *2,即x_a 为首个乘以2后大于x_n的丑数
        x_b*3 > x_n >= x_b-1 *3,即x_b 为首个乘以3后大于x_n的丑数
        x_c*5 > x_n >= x_c-1 *5,即x_c 为首个乘以5后大于x_n的丑数

    dp解析：
    状态定义： 设动态规划列表 dp ，dp[i] 代表第 i + 1 个丑数；
    转移方程：
        1.当索引 a, b, c 满足以下条件时， dp[i] 为三种情况的最小值；
        2.每轮计算 dp[i] 后，需要更新索引 a, b, c 的值，使其始终满足方程条件。
            实现方法：分别独立判断 dp[i] 和 dp[a]×2,[b]×3, dp[c]×5 的大小关系，若相等则将对应索引 a , b , c 加 1 ；
            p[i]=min(dp[a]×2,dp[b]×3,dp[c]×5)
    初始状态：dp[0]=1 ，即第一个丑数为 1 ；
    返回值： dp[n-1]，即返回第 n 个丑数；

     */

    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i]==n2) a++;
            if (dp[i]==n3) b++;
            if (dp[i]==n5) c++;
        }
        return dp[n - 1];

    }

    @Test
    public void test() {
        int n = 12;
        System.out.println(nthUglyNumber(n));
    }
}
