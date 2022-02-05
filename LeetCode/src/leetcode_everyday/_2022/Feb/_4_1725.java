package leetcode_everyday._2022.Feb;

import java.util.HashMap;

/**
 * @author WuChao
 * @create 2022/2/4 9:28
 */
public class _4_1725 {
    /**
     * 每日一题：2022/2/4
     * <p>
     * 1725. 可以形成最大正方形的矩形数目
     * <p>
     * 难度：easy
     * <p>
     * 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
     * <p>
     * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
     * <p>
     * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
     * <p>
     * 请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
     * <p>
     * 输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
     * <p>
     * 输出：3
     * <p>
     * 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
     * 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
     * <p>
     * 范围
     * <p>
     * 1 <= rectangles.length <= 1000
     * rectangles[i].length == 2
     * 1 <= li, wi <= 109
     * li != wi
     */

    /*
     思路：哈希表统计每个裁剪的正方形的边长
     */
    public int countGoodRectangles(int[][] rectangles) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int[] rec : rectangles) {
            int maxLen = Math.min(rec[0], rec[1]);
            map.put(maxLen, map.getOrDefault(maxLen, 0) + 1);
            max = Math.max(maxLen, max);
        }
        return map.get(max);
    }
}
