package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/4/19 8:41
 */
public class _19_821 {
    /**
     * 每日一题：2022/4/19
     * <p>
     * 821. 字符的最短距离
     * <p>
     * 难度：easy
     * <p>
     * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
     * <p>
     * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
     * <p>
     * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
     * <p>
     * 示例
     * <p>
     * 输入：s = "loveleetcode", c = "e"
     * <p>
     * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
     * <p>
     * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
     * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
     * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
     * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
     * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
     * <p>
     * 范围
     * <p>
     * 1 <= s.length <= 104
     * s[i] 和 c 均为小写英文字母
     * 题目数据保证 c 在 s 中至少出现一次
     */

    /*
    思路1：存储对应位置的下标 遍历过程中计算最小值
     */
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        int[] ans = new int[len];
        List<Integer> store = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                store.add(i);
            }
        }
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != c) {
                int min = Integer.MAX_VALUE;
                for (int idx : store) {
                    min = Math.min(min, Math.abs(idx - i));
                }
                ans[i] = min;
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    /*
    思路2：两次遍历
     */
    public int[] shortestToChar2(String s, char c) {
        int len = s.length();
        int[] ans = new int[len];
        // 从左向右遍历
        for (int i = 0, idx = -len; i < len; i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }
        // 从右向左遍历
        for (int i = len - 1, idx = 2 * len; i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;


    }
}
