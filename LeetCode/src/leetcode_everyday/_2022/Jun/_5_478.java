package leetcode_everyday._2022.Jun;

import java.util.Random;

/**
 * @Author CharlesWu
 * @Create 2022/6/5 9:43
 * @Version 1.0
 * @Description
 * @Note
 */
public class _5_478 {
    /**
     * 每日一题：2022/6/5
     * 478. 在圆内随机生成点
     * 难度: medium
     * <p>
     * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
     *
     * 实现 Solution 类:
     *
     * Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
     * randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * ["Solution","randPoint","randPoint","randPoint"]
     * [[1.0, 0.0, 0.0], [], [], []]
     *
     * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
     *
     * 解释:
     * Solution solution = new Solution(1.0, 0.0, 0.0);
     * solution.randPoint ();//返回[-0.02493，-0.38077]
     * solution.randPoint ();//返回[0.82314,0.38945]
     * solution.randPoint ();//返回[0.36572,0.17248]
     * <p>
     * 数据范围：
     * <p>
     * 0 < radius <= 108
     * -107 <= x_center, y_center <= 107
     * randPoint 最多被调用 3 * 10^4 次
     */

    /*
    思路：
     */
    class Solution {
        Random random;
        double xc,yc, r;

        public Solution(double radius, double x_center, double y_center) {
            random = new Random();
            xc = x_center;
            yc = y_center;
            r = radius;
        }

        public double[] randPoint() {
            while (true) {
                double x = random.nextDouble() * (2 * r) - r;
                double y = random.nextDouble() * (2 * r) - r;
                if (x * x + y * y <= r * r) {
                    return new double[]{xc + x, yc + y};
                }
            }

        }
    }
}
