package leetcode_everyday._2021.Dec;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2021/12/27 上午9:29
 */
public class _27_825 {
    /**
     * 每日一题：2021/12/27
     * <p>
     * 825. 适龄的朋友
     * <p>
     * 难度：medium
     * <p>
     * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
     * <p>
     * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
     * <p>
     * age[y] <= 0.5 * age[x] + 7
     * age[y] > age[x]
     * age[y] > 100 && age[x] < 100
     * 否则，x 将会向 y 发送一条好友请求。
     * <p>
     * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
     * <p>
     * 返回在该社交媒体网站上产生的好友请求总数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：ages = [16,16]
     * <p>
     * 输出：2
     * <p>
     * 解释：2 人互发好友请求。
     *
     * <p>
     * 范围
     * <p>
     * n == ages.length
     * 1 <= n <= 2 * 10^4
     * 1 <= ages[i] <= 120
     **/

    /*
    思路：排序 + 双指针
    以 y 的角度来说，可总结为：年龄比我小的不考虑（同龄的可以），年龄比我大可以考虑，但是不能超过一定范围则不考虑。
    先对 ages 进行排序 枚举每个 ages[k] 同时用 i 和 j 维护左右区间，[i,j) 代表在 ages 上 会向 y = ages[k] 发送请求的x连续段 统计每个
    y = ages[k] 的x有多少个 就是答案
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length, ans = 0;
        for (int k = 0, i = 0, j = 0; k < n; k++) {
            while (i < k && !check(ages[i], ages[k])) i++;
            if (j < k) j = k;
            while (j < n && check(ages[j], ages[k])) j++;
            if (j > i) ans += j - i - 1;
        }
        return ans;
    }

    public boolean check(int x, int y) {
        if (y <= 0.5 * x + 7) {
            return false;
        }
        return y <= x;
    }
}
