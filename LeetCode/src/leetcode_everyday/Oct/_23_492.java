package leetcode_everyday.Oct;

/**
 * @author WuChao
 * @create 2021/10/23 10:02
 */
public class _23_492 {
    /**
     * 每日一题：2021/10/23
     * <p>
     * 492. 构造矩形
     * <p>
     * 难度：easy
     * <p>
     * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，
     * 你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
     * <p>
     * 1. 你设计的矩形页面必须等于给定的目标面积。
     * <p>
     * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     * <p>
     * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
     * <p>
     * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入: 4
     * <p>
     * 输出: [2, 2]
     * <p>
     * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
     * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
     * <p>
     * 范围
     * <p>
     * 给定的面积不大于 10,000,000 且为正整数。
     * 你设计的页面的长度和宽度必须都是正整数。
     */

    /*
    思路1；暴力遍历
     */
    public int[] constructRectangle(int area) {
        int[] res = new int[2];
        int minus = Integer.MAX_VALUE;
        for (int l = area; l > 0; l--) {
            int tmp = area / l;
            int w;
            if (tmp * l == area) {
                w = tmp;
                if (l >= w && l - w < minus) {
                    minus = l - w;
                    res[0] = l;
                    res[1] = w;
                }
            }

        }
        return res;
    }

    /*
    思路2：优化的暴力遍历，从 根号(area)开始遍历
     */
    public int[] constructRectangle2(int area) {
        for (int i = (int) Math.sqrt(area); ; i--) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
    }

}
