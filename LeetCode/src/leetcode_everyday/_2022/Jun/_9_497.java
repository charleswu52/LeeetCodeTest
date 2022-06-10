package leetcode_everyday._2022.Jun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author CharlesWu
 * @Create 2022/6/10 9:26
 * @Version 1.0
 * @Description
 * @Note
 */
public class _9_497 {
    /**
     * 每日一题：2022/6/9
     * 497. 非重叠矩形中的随机点
     * 难度: medium
     * <p>
     * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，
     * (xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。
     * 所有满足要求的点必须等概率被返回。
     *
     * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
     *
     * 请注意 ，整数点是具有整数坐标的点。
     *
     * 实现 Solution 类:
     *
     * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
     * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * ["Solution", "pick", "pick", "pick", "pick", "pick"]
     * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
     * 输出:
     * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
     *
     * 解释：
     * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
     * solution.pick(); // 返回 [1, -2]
     * solution.pick(); // 返回 [1, -1]
     * solution.pick(); // 返回 [-1, -2]
     * solution.pick(); // 返回 [-2, -2]
     * solution.pick(); // 返回 [0, 0]
     * <p>
     * 数据范围：
     * <p>
     * 1 <= rects.length <= 100
     * rects[i].length == 4
     * -109 <= ai < xi <= 109
     * -109 <= bi < yi <= 109
     * xi - ai <= 2000
     * yi - bi <= 2000
     * 所有的矩形不重叠。
     * pick 最多被调用 10^4 次。
     */

    class Solution {
        Random random;
        List<Integer> arr;
        int[][] rects;

        public Solution(int[][] rects) {
            random = new Random();
            arr = new ArrayList<>();
            arr.add(0);
            this.rects = rects;
            for (int[] rect : rects) {
                int a = rect[0], b = rect[1], x = rect[2], y = rect[3];
                arr.add(arr.get(arr.size() - 1) + (x - a + 1) * (y - b + 1));
            }
        }

        public int[] pick() {
            int k = random.nextInt(arr.get(arr.size() - 1));
            int rectIdx = binarySearch(arr, k + 1) - 1;
            k -= arr.get(rectIdx);
            int[] rect = rects[rectIdx];
            int a = rect[0], b = rect[1], y = rect[3];
            int col = y - b + 1;
            int da = k / col;
            int db = k - col * da;
            return new int[]{a + da, b + db};
        }

        private int binarySearch(List<Integer> arr, int target) {
            int low = 0, high = arr.size() - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = arr.get(mid);
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }

}
