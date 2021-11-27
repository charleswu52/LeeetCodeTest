package leetcode_everyday.Nov;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author WuChao
 * @create 2021/11/27 10:55
 */
public class _27_519 {
    /**
     * 每日一题：2021/11/27
     * <p>
     * 519. 随机翻转矩阵
     * <p>
     * 难度：medium
     * <p>
     * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标(i, j) ，
     * 并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
     * <p>
     * 尽量最少调用内置的随机函数，并且优化时间和空间复杂度。
     * <p>
     * 实现 Solution 类：
     * <p>
     * Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
     * int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
     * void reset() 将矩阵中所有的值重置为 0
     */


    /*
    思路： 哈希表 + swap

    在 [0,m∗n) 这连续一段的区间内进行随机，但当我们经过了多次翻转后，该区间内的某些位置会被断开，使得数组不再连续。

    如果我们希望在每次随机时都采用起始的方式（在连续一段内进行随机），需要确保某些位置被翻转后，剩余位置仍是连续。

    具体的，我们可以使用「哈希表」多记录一层映射关系：起始时所有位置未被翻转，我们规定未被翻转的位置其映射值为编号本身（idx=row∗n+col），
    由于未被翻转的部分具有等值映射关系，因此无须在哈希表中真实存储。当随机到某个位置 idx 时，进行分情况讨论：

    该位置未被哈希表真实记录（未被翻转）：说明 idx 可被直接使用，使用 idx 作为本次随机点。
        同时将右端点（未被使用的）位置的映射值放到该位置，将右端点左移。确保下次再随机到 idx，仍能直接使用 idx 的映射值，
        同时维护了随机区间的连续性；
    该位置已被哈希表真实记录（已被翻转）：此时直接使用 idx 存储的映射值（上一次交换时的右端点映射值）即可，然后用新的右端点映射值将其进行覆盖，
        更新右端点。确保下次再随机到 idx，仍能直接使用 idx 的映射值，同时维护了随机区间的连续性。
     */


    class Solution {

        int m, n, cnt; // cnt 表示剩余个数，同时cnt-1为区间右端点位置
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random(300);

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.cnt = m * n;

        }

        public int[] flip() {
            int x = random.nextInt(cnt--);
            int idx = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(cnt, cnt));
            return new int[]{idx / n, idx % n};

        }

        public void reset() {
            cnt = m * n;
            map.clear();
        }
    }
}
