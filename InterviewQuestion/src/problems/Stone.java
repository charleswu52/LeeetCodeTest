package problems;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/11 19:52
 */
public class Stone {
    public  static int[] result(int A, int B, int n, int[] e, int[] a) {
        // write code here
        long inf = Long.MAX_VALUE;
        long[][] dp = new long[n + 1][200];
        for (long[] t : dp) {
            Arrays.fill(t, inf);
        }
        System.out.println(Arrays.deepToString(dp));
        int sum = 0, ans = 0;
        dp[n][0] = -inf;
        for (int i = n - 1; i >= 0; i--){
            sum += a[i];
            for (int j = 0; j <= sum; j++){
                 long Achoose = -(dp[i + 1][sum - j + 1] - 1) - e[i];
                 long Apass = Math.max(1L, dp[i + 1][j] + (e[i] + 1));
                dp[i][j] = Math.min(Achoose,Apass);
                if (i == 0 && dp[i][j] <= A - B)
                    ans = j;
            }
        }
        return new int[]{ans, sum - ans};
    }


    public static void main(String[] args) {


        int A = 9, B = 0, n = 7;
        int[] e = {66,2,6,2,8,4,3};
        int[] a = {7,12,65,7,4,40,15};
        System.out.println(Arrays.toString(result(A, B, n, e, a)));
    }


}
