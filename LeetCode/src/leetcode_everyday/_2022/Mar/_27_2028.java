package leetcode_everyday._2022.Mar;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/3/27 9:38
 */
public class _27_2028 {
    /**
     * 每日一题：2022/3/27
     * <p>
     * 2028. 找出缺失的观测数据
     * <p>
     * 难度：medium
     * <p>
     * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。
     * <p>
     * 幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
     * <p>
     * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
     * <p>
     * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，
     * 只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
     * <p>
     * k 个数字的 平均值 为这些数字求和后再除以 k 。
     * <p>
     * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
     * <p>
     * 示例1
     * <p>
     * 输入：rolls = [3,2,4,3], mean = 4, n = 2
     * <p>
     * 输出：[6,6]
     * <p>
     * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
     * <p>
     * 范围
     * <p>
     * m == rolls.length
     * 1 <= n, m <= 10^5
     * 1 <= rolls[i], mean <= 6
     */


    /*
    思路：数学模拟
     */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = (m + n) * mean;
        int remains = sum - Arrays.stream(rolls).sum();
        int[] ans = new int[n];
        if (n * 6 < remains || n > remains) {
            return new int[]{};
        }
        Arrays.fill(ans, remains / n);
        if (remains / n * n < remains) {
            int d = remains - (remains / n * n), idx = 0;
            while (d-- > 0) {
                ans[idx++]++;
            }
        }
        return ans;


    }
    @Test
    public void test() {
        int[] rolls = {1, 3, 6};
        int mean = 3, n = 4;
        System.out.println(Arrays.toString(missingRolls(rolls, mean, n)));
    }
}
