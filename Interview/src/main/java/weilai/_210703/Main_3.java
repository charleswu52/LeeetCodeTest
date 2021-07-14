package weilai._210703;

import java.util.Arrays;

public class Main_3 {
    public int theNumberofWays (int[] arr, int aim) {
        // write code here
        int mod = 1000000007;
        Arrays.sort(arr);
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int coin : arr) {
            for (int i = 0; i + coin < aim + 1; i++) {
                dp[i + coin] = (dp[i + coin] + dp[i]) % mod;
            }
        }
        return dp[aim];
    }
}
