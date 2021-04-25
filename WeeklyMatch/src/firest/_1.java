package firest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/25 上午10:40
 */
public class _1 {
    public int sumBase(int n, int k) {
        List<Character> nums = new ArrayList<>();
        int tmp;
        while (n != 0) {
            tmp = n % k;
            if (tmp > 10) {
                nums.add((char) ('A' + tmp - 10));
            } else {
                nums.add((char) (tmp + '0'));
            }
            n /= k;
        }
//        System.out.println(nums);
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            ans += nums.get(i) - '0';
        }
        return ans;

    }
    @Test
    public void test() {
        int n = 42, k = 2;
        System.out.println(sumBase(n, k));

    }
}
