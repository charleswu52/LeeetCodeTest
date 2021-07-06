package _210705.shoppe;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/7/5 15:04
 */
public class Main_3 {


    public int petalsBreak(int[] petals) {
        int n = petals.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
           dp[i] = (int) Math.ceil(petals[i] / 2.0);
           System.out.println(dp[i]);
        }
        return Arrays.stream(dp).sum();
    }

    @Test
    public void test() {
        int[] petals = {1, 3, 4};
        System.out.println(petalsBreak(petals));

    }

}
