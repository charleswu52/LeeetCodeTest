package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/8 8:54
 */
public class _8_2055 {
    /**
     * 每日一题：2022/3/8
     * <p>
     * 2055. 蜡烛之间的盘子
     * <p>
     * 难度：medium
     * <p>
     * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，
     * 其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
     * <p>
     * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti]（包含左右端点的字符）。
     * 对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。
     * 如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
     * <p>
     * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，
     * 子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
     * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
     * <p>
     * 示例1
     * <p>
     * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
     * <p>
     * 输出：[2,3]
     * <p>
     * 解释：
     * - queries[0] 有两个盘子在蜡烛之间。
     * - queries[1] 有三个盘子在蜡烛之间。
     * <p>
     * 范围
     * <p>
     * 3 <= s.length <= 105
     * s 只包含字符 '*' 和 '|' 。
     * 1 <= queries.length <= 105
     * queries[i].length == 2
     * 0 <= lefti <= righti < s.length
     */

    /*
    思路1：暴力搜索
    ！！！ 超时 ！！！
     */
    public int[] platesBetweenCandles1(String s, int[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        int idx = 0;
        for (int[] query : queries) {
            int left = query[0], right = query[1];
            while (left <= right && s.charAt(left) != '|') left++;
            while (left <= right && s.charAt(right) != '|') right++;
            int ans = 0;
            if (right > left) {
                res[idx] = 0;
            } else {
                for (int i = left + 1; i < right; i++) {
                    if (s.charAt(i) == '*') {
                        ans++;
                    }
                }
                res[idx] = ans;
            }
            idx++;
        }
        return res;
    }

    /*
    思路2： 前缀和 + 二分搜索
    找到区间 [a, b] 两边缘的蜡烛，分别记为 c 和 d，显然区间 [c, d] 内的盘子都是需要被统计的。

    我们可以对字符串 s 进行从前往后的扫描，将所有的蜡烛下标添加到数组（数组严格递增），并预处理出盘子的「前缀和」数组。
    然后处理询问时的「找区间 [a, b] 边缘蜡烛」操作可通过对数组「二分」来做，而「查询区间 [c, d] 内的盘子数量」操作可直接查询「前缀和」数组。
     */
    public int[] platesBetweenCandles2(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length, m = queries.length;
        int[] ans = new int[m], sum = new int[n + 1];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '|') {
                list.add(i);
            }
            sum[i + 1] = sum[i] + (chars[i] == '*' ? 1 : 0);
        }
        if (list.size() == 0) {
            return ans;
        }
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1];
            int c = -1, d = -1;
            // 找到left 右边最近的蜡烛
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (list.get(mid) >= left) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (list.get(r) >= left) {
                c = list.get(r);
            } else {
                continue;
            }
            // 找到right 左边最近的蜡烛
            l = 0;
            r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid) <= right) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (list.get(r) <= right) {
                d = list.get(r);
            } else {
                continue;
            }
            if (c <= d) {
                ans[i] = sum[d + 1] - sum[c];
            }
        }
        return ans;


    }
}
