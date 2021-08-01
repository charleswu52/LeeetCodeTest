package leetcode_everyday.Oct;

import com.sun.deploy.ui.UIFactory;
import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/8/1 11:24
 */
public class _1_1337 {
    /**
     * 每日一题：2021/8/1
     * 1337. 矩阵中战斗力最弱的 K 行
     * 难度: easy
     * <p>
     * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     *
     *
     * <p>
     * 输入：mat =
     * [[1,1,0,0,0],
     * [1,1,1,1,0],
     * [1,0,0,0,0],
     * [1,1,0,0,0],
     * [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     *
     *
     *
     * <p>
     * 数据范围：
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
     */

    public int[] kWeakestRows(int[][] mat, int k) {
        Map<Integer, List<Integer>> map = new TreeMap<>(Integer::compareTo);
        for (int i = 0; i < mat.length; i++) {
            int sum = Arrays.stream(mat[i]).sum();
            if (!map.containsKey(sum)) {
                int finalI = i;
                map.put(sum, new ArrayList<Integer>() {{
                    add(finalI);
                }});
            } else {
                map.get(sum).add(i);
            }
        }
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            Collections.sort(value);
            for (int t : value) {
                if (idx < k) {
                    res[idx++] = t;
                } else {
                    break;
                }
            }
        }
        return res;



    }

    @Test
    public void test() {
        int[][] mat = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        int k = 3;
        System.out.println(Arrays.toString(kWeakestRows(mat, k)));
    }
}
