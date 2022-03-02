package leetcode_everyday._2022.Mar;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/3/2 8:44
 */
public class _2_564 {
    /**
     * 每日一题：2022/3/2
     * <p>
     * 564. 寻找最近的回文数
     * <p>
     * 难度：hard
     * <p>
     * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
     * <p>
     * “最近的”定义为两个整数差的绝对值最小。
     *
     * <p>
     * 示例
     * <p>
     * 输入: n = "123"
     * <p>
     * 输出: "121"
     * <p>
     * 范围
     * <p>
     * 1 <= n.length <= 18
     * n 只由数字组成
     * n 不含前导 0
     * n 代表在 [1, 10^18 - 1] 范围内的整数
     */

    /*
    思路1：暴力 -- 超时做法
     */
    public String nearestPalindromic1(String n) {
        long num = Long.parseLong(n);
        long i = 1, j = -1;
        String res;
        while (true) {
            long a = num + j;
            if (isHuiwen(a)) {
                res = String.valueOf(a);
                break;
            }
            long b = num + i;
            if (isHuiwen(b)) {
                res = String.valueOf(b);
                break;
            }
            j--;
            i++;
        }
        return res;

    }

    public boolean isHuiwen(long num) {
        String a = String.valueOf(num);
        String reverse = new StringBuilder().append(a).reverse().toString();
        return a.equals(reverse);
    }

    /*
    思路2： 贪心 + 上下界处理
    https://leetcode-cn.com/problems/find-the-closest-palindrome/solution/gong-shui-san-xie-tan-xin-fen-xi-shang-x-vtr6/
     */
    public String nearestPalindromic(String n) {
        int len = n.length();
        long cur = Long.parseLong(n);
        Set<Long> set = new HashSet<>();
        set.add((long) Math.pow(10, len - 1) - 1);
        set.add((long) Math.pow(10, len) + 1);
        long temp = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = temp - 1; i <= temp + 1; i++) {
            long t = -1;
            if (len % 2 == 0) {
                t = getNum(i, true);
            }else{
                t = getNum(i, false);
            }
            if (t!=cur) set.add(t);
        }
        long ans = -1;
        for (long l : set) {
            if (ans == -1) {
                ans = l;
            } else if (Math.abs(cur - l) < Math.abs(cur - ans)) {
                ans = l;
            } else if (Math.abs(cur - l) == Math.abs(cur - ans) && l < ans) {
                ans = l;
            }
        }
        return String.valueOf(ans);
    }

    public long getNum(long k, boolean isEven) {
        StringBuilder sb = new StringBuilder();
        sb.append(k);
        int idx = isEven ? sb.length() - 1 : sb.length() - 2;
        while (idx >= 0) {
            sb.append(sb.charAt(idx--));
        }
        return Long.parseLong(sb.toString());
    }
}
