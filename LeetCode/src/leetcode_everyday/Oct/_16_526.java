package leetcode_everyday.Oct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/8/16 9:05
 */
public class _16_526 {
    /**
     * 每日一题：2021/8/16
     * 526. 优美的排列
     * 难度：medium
     * <p>
     * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
     * 我们就称这个数组为一个优美的排列。条件：
     * 1. 第 i 位的数字能被 i 整除
     * 2. i 能被第 i 位上的数字整除
     * 现在给定一个整数 N，请问可以构造多少个优美的排列？
     * <p>
     * 示例 1：
     * 输入: 2
     * 输出: 2
     * 解释:
     * <p>
     * 第 1 个优美的排列是 [1, 2]:
     * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
     * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
     * <p>
     * 第 2 个优美的排列是 [2, 1]:
     * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
     * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
     * <p>
     * 注意:
     * N 是一个正整数，并且不会超过15。
     */


    /*
    思路：回溯法
    从左向右依次向目标排列中放入数即可。

    具体地，我们定义函数 backtrack(index,n)，表示尝试向位置 index 放入数。其中 n 表示排列的长度。
    在当前函数中，我们首先找到一个符合条件的未被使用过的数，然后递归地执行 backtrack(index+1,n)，当该函数执行完毕，回溯到当前层，
    我们再尝试下一个符合条件的未被使用过的数即可。

    回溯过程中，我们可以用 vis 数组标记哪些数被使用过，每次我们选中一个数 x，我们就将 vis[x] 标记为 true，回溯完成后，
    我们再将其置为 false。

    特别地，为了优化回溯效率，我们可以预处理每个位置的符合条件的数有哪些，用二维数组 match 保存。当我们尝试向位置 index 放入数时，
    我们只需要遍历 match[index] 即可。

     */

    /*
    解决方案1：回溯法

    时间复杂度:O(n!)
    空间复杂度:O(n^2)
     */
    List<Integer>[] match;
    boolean[] visited;
    int res;

    public int countArrangement(int n) {
        visited = new boolean[n + 1];
        match = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(1, n);
        return res;

    }

    // 回溯计算排列数
    public void backtrack(int index, int n) {
        if (index == n + 1) {
            res++;
            return;
        }
        for (int x : match[index]) {
            if (!visited[x]) {
                visited[x] = true;
                backtrack(index + 1, n);
                visited[x] = false;
            }
        }
    }


}
