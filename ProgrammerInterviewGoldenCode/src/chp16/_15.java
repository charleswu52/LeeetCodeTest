package chp16;

/**
 * @author WuChao
 * @since 2021/5/31 上午10:28
 */
public class _15 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.15. 珠玑妙算
     * 难度: easy
     * <p>
     * 珠玑妙算游戏（the game of master mind）的玩法如下。
     *
     * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
     * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     *
     * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     * <p>
     * 示例:
     * 输入： [[0,0],[1,1],[1,0],[2,0]]
     * 输出： [0,2]
     * 解释： 所求直线穿过的3个点的编号为[0,2,3]
     * <p>
     * 数据范围：
     * 2 <= len(Points) <= 300
     * len(Points[i]) = 2
     */

    public int[] masterMind(String solution, String guess) {
        int[] ans = new int[2];
        int res1 = 0, res2 = 0;
        int[] s = new int[4];
        int[] g = new int[4];
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == guess.charAt(i)) {
                res1++;
            }
            switch (solution.charAt(i)) {
                case 'R':
                    s[0]++;
                    break;
                case 'G':
                    s[1]++;
                    break;
                case 'B':
                    s[2]++;
                    break;
                case 'Y':
                    s[3]++;
                    break;
            }
            switch (guess.charAt(i)) {
                case 'R':
                    g[0]++;
                    break;
                case 'G':
                    g[1]++;
                    break;
                case 'B':
                    g[2]++;
                    break;
                case 'Y':
                    g[3]++;
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            res2 += Math.min(s[i], g[i]);
        }
        res2 -= res1;
        ans[0] = res1;
        ans[1] = res2;
        return ans;

    }
}
