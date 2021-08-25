package com.guomiaomiao;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/8/25 9:34
 */
public class Main_2 {


    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int mod = 1000000007;
        int[][][] dp = new int[26][n][n];

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                for (int k = 0; k < 26; ++k) {
                    char c = (char) ('a' + k);
                    if (j == i) {
                        if (S.charAt(i) == c) dp[k][i][j] = 1;
                        else dp[k][i][j] = 0;
                    } else { // j > i
                        if (S.charAt(i) != c) dp[k][i][j] = dp[k][i + 1][j];
                        else if (S.charAt(j) != c) dp[k][i][j] = dp[k][i][j - 1];
                        else { // S[i] == S[j] == c
                            if (j == i + 1) dp[k][i][j] = 1; // "aa" : {"a", "aa"}
                            else { // length is > 2
                                dp[k][i][j] = 2;
                                for (int m = 0; m < 26; ++m) { // count each one within subwindows [i+1][j-1]
                                    dp[k][i][j] += dp[m][i + 1][j - 1];
                                    dp[k][i][j] %= mod;
                                }
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int k = 0; k < 26; ++k) {
            ans += dp[k][0][n - 1];
            ans %= mod;
        }

        return ans;
    }

    @Test
    public void test() {
        String s = "aba";
        System.out.println(countPalindromicSubsequences(s));
    }
}

