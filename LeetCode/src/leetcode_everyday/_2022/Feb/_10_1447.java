package leetcode_everyday._2022.Feb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/2/10 9:39
 */
public class _10_1447 {
    /**
     * 每日一题：2022/2/10
     * <p>
     * 1447. 最简分数
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
     * <p>
     * 输入：n = 2
     *
     * 输出：["1/2"]
     *
     * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
     *
     * 输入：n = 4
     *
     * 输出：["1/2","1/3","1/4","2/3","3/4"]
     *
     * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 100
     */

    /*
    思路：数学 判断两个数是否互质
     */
    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (isSimple(j, i)) {
                    res.add(j + "/" + i);
                }
            }
        }
        return res;

    }

    public boolean isSimple(int a, int b) {
        if (a == 1 && b == 1) {
            return true;
        } else if (a <= 0 || b <= 0 || a == b) {
            return false;
        } else if (a == 1 || b == 1) {
            return true;
        } else {
            int temp = 0;
            while (true) {
                temp = a % b;
                if (temp == 0) {
                    break;
                } else {
                    a = b;
                    b = temp;
                }
            }

        }
        return b == 1;

    }
}
