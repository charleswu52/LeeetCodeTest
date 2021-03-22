import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/3/22 上午8:20
 */
public class _43 {
    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     * 难度: hard
     * <p>
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     *
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     *
     *<p>
     * 示例：
     * 输入：n = 12
     * 输出：5
     *
     * <p>
     * 数据范围：
     * 1 <= n < 2^31
     */

    // 暴力测试
    public int countDigitOne(int n) {
        int ans = 0;
//        for (int i = 1; i <= n; i++) {
//            char[] chars = String.valueOf(i).toCharArray();
//            for (char ch : chars) {
//                if (ch == '1') {
//                    ans++;
//                }
//            }
//        }

        int res = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                res++;
            }
            n >>>= 1;
        }
        return res;

    }

    // 参考题解
    public int countDigitOne2(int n) {
        int digit =1;//位，从个位向上增加
        int res = 0;
        int high = n / 10, cur = n % 10, low = 0; // 将当前数分位高位、当前位、低位三部分
        while ( high != 0 || cur != 0){
            // 分情况统计
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            // cur向前移动
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;

    }


        @Test
    public void test(){
        int n = 824883294;
        System.out.println(countDigitOne2(n));
    }
}
