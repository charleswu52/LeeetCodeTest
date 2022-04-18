package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/4/18 9:00
 */
public class _18_386 {
    /**
     * 每日一题：2022/4/18
     * <p>
     * 386. 字典序排数
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     *
     * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
     * <p>
     * 示例
     * <p>
     * 输入：n = 13
     *
     * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 5 * 10^4
     */

    /*
    思路：模拟
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) {
            res.add(cur);
            if (cur * 10 <= n) {
                cur *= 10;
            } else {
                if (cur >= n) {
                    cur /= 10;
                }
                cur += 1;
                while (cur % 10 == 0) {
                    cur /= 10;
                }

            }
        }
        return res;

    }

}
