package leetcode_everyday._2022.Jan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/1/8 21:22
 */
public class _8_89 {
    /**
     * 每日一题：2022/1/7
     * <p>
     * 89. 格雷编码
     * <p>
     * 难度：medium
     * <p>
     * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
     *      每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
     *      第一个整数是 0
     *      一个整数在序列中出现 不超过一次
     *      每对 相邻 整数的二进制表示 恰好一位不同 ，且
     *      第一个 和 最后一个 整数的二进制表示 恰好一位不同
     * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：[0,1,3,2]
     * 解释：
     * [0,1,3,2] 的二进制表示是 [00,01,11,10] 。
     * - 00 和 01 有一位不同
     * - 01 和 11 有一位不同
     * - 11 和 10 有一位不同
     * - 10 和 00 有一位不同
     * [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
     * - 00 和 10 有一位不同
     * - 10 和 11 有一位不同
     * - 11 和 01 有一位不同
     * - 01 和 00 有一位不同
     *
     * <p>
     * 范围
     * <p>
     * 输入：n = 1
     * 输出：[0,1]
     **/

    /*
    思路1： 对称生成
    
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        while (n-- > 0) {
            int m = res.size();
            for (int i = m - 1; i >= 0; i--) {
                res.set(i, res.get(i) << 1);
                res.add(res.get(i) + 1);
            }
        }
        return res;

    }
}
