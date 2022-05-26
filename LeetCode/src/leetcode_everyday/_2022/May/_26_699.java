package leetcode_everyday._2022.May;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/5/26 9:00
 */
public class _26_699 {
    /**
     * 每日一题：2022/5/26
     * <p>
     * 699. 掉落的方块
     * <p>
     * 难度：hard
     * <p>
     * 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
     * <p>
     * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(positions[i][0])，
     * side_length 表示该方块的边长(positions[i][1])。
     * <p>
     * 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。
     * 在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
     * <p>
     * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。
     * 邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
     * <p>
     * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i]
     * 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
     * <p>
     * 示例
     * <p>
     * 输入: [[1, 2], [2, 3], [6, 1]]
     * 输出: [2, 5, 5]
     * 解释:
     * <p>
     * 第一个方块 positions[0] = [1, 2] 掉落：
     * _aa
     * _aa
     * -------
     * 方块最大高度为 2 。
     * <p>
     * 第二个方块 positions[1] = [2, 3] 掉落：
     * __aaa
     * __aaa
     * __aaa
     * _aa__
     * _aa__
     * --------------
     * 方块最大高度为5。
     * 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
     * <p>
     * 第三个方块 positions[1] = [6, 1] 掉落：
     * __aaa
     * __aaa
     * __aaa
     * _aa
     * _aa___a
     * --------------
     * 方块最大高度为5。
     * <p>
     * 因此，我们返回结果[2, 5, 5]。
     * <p>
     * 范围
     * <p>
     * 1 <= positions.length <= 1000.
     * 1 <= positions[i][0] <= 10^8.
     * 1 <= positions[i][1] <= 10^6.
     */

    /*
    思路1：暴力枚举
     */
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1] - 1;
            int height = positions[i][1];
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1] - 1;
                if (right1 >= left2 && right2 >= left1) {
                    height = Math.max(height, heights.get(j) + positions[i][1]);
                }
            }
            heights.add(height);
        }
        for (int i = 1; i < n; i++) {
            heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
        }
        return heights;
    }

    /*
    思路2：有序集合 TreeMap
     */
    public List<Integer> fallingSquares2(int[][] positions) {
        int n = positions.length;
        List<Integer> ret = new ArrayList<Integer>();
        TreeMap<Integer, Integer> heightMap = new TreeMap<Integer, Integer>();
        heightMap.put(0, 0); // 初始时从 0 开始的所有点的堆叠高度都是 0
        for (int i = 0; i < n; i++) {
            int size = positions[i][1];
            int left = positions[i][0], right = positions[i][0] + positions[i][1] - 1;
            Integer lp = heightMap.higherKey(left), rp = heightMap.higherKey(right);
            Integer prevRightKey = rp != null ? heightMap.lowerKey(rp) : heightMap.lastKey();
            int rHeight = prevRightKey != null ? heightMap.get(prevRightKey) : 0; // 记录 right + 1 对应的堆叠高度（如果 right + 1 不在 heightMap 中）

            // 更新第 i 个掉落的方块的堆叠高度
            int height = 0;
            Integer prevLeftKey = lp != null ? heightMap.lowerKey(lp) : heightMap.lastKey();
            Map<Integer, Integer> tail = prevLeftKey != null ? heightMap.tailMap(prevLeftKey) : heightMap;
            for (Map.Entry<Integer, Integer> entry : tail.entrySet()) {
                if (entry.getKey() == rp) {
                    break;
                }
                height = Math.max(height, entry.getValue() + size);
            }

            // 清除 heightMap 中位于 (left, right] 内的点
            Set<Integer> keySet = new TreeSet<Integer>(tail.keySet());
            for (Integer tmp : keySet) {
                if (lp == null || tmp < lp) {
                    continue;
                }
                if (rp != null && tmp >= rp) {
                    break;
                }
                heightMap.remove(tmp);
            }

            heightMap.put(left, height); // 更新 left 的变化
            if (rp == null || rp != right + 1) { // 如果 right + 1 不在 heightMap 中，更新 right + 1 的变化
                heightMap.put(right + 1, rHeight);
            }
            ret.add(i > 0 ? Math.max(ret.get(i - 1), height) : height);
        }
        return ret;
    }
}
