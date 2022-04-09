package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/9 10:13
 */
public class _9_780 {
    /**
     * 每日一题：2022/4/9
     * <p>
     * 780. 到达终点
     * <p>
     * 难度：hard
     * <p>
     * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
     * <p>
     * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
     * <p>
     * 在比较时，字母是依序循环出现的。举个例子：
     * <p>
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     * <p>
     * 示例
     * <p>
     * 输入: sx = 1, sy = 1, tx = 3, ty = 5
     * <p>
     * 输出: true
     * <p>
     * 解释:
     * <p>
     * 可以通过以下一系列转换从起点转换到终点：
     * (1, 1) -> (1, 2)
     * (1, 2) -> (3, 2)
     * (3, 2) -> (3, 5)
     * <p>
     * 范围
     * <p>
     * 1 <= sx, sy, tx, ty <= 109
     */
    /*
    思路：逆向推导

     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > 0 && ty > 0) {//因为sx, sy, tx, ty 是范围在 [1, 10^9] 的整数，逆推不能出界
            if (sx == tx && sy == ty) {//判断是否到达了起始值
                return true;
            }
            //每次逆推只能有tx、ty中较大值减去若干个较小值
            if (tx > ty) {//此时只能有tx减去ty
                //tx - sx是目标与起始值在x的差距，我们需要一次减去n * ty达到快速逼近sx的目的
                tx -= Math.max((tx - sx) / ty, 1) * ty;
            } else {//此时只能有ty减去tx
                //ty - sy是目标与起始值在y的差距，我们需要一次减去n * tx达到快速逼近sy的目的
                ty -= Math.max((ty - sy) / tx, 1) * tx;
            }
        }
        return false;

    }
}
