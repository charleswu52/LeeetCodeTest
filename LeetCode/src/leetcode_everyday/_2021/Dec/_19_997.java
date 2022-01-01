package leetcode_everyday._2021.Dec;

/**
 * @author WuChao
 * @create 2021/12/19 上午9:55
 */
public class _19_997 {
    /**
     * 每日一题：2021/12/19
     * <p>
     * 997. 找到小镇的法官
     * <p>
     * 难度：easy
     * <p>
     * 在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
     *
     * 如果小镇的法官真的存在，那么：
     *
     * 小镇的法官不相信任何人。
     * 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足条件 1 和条件 2 。
     * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
     *
     * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2, trust = [[1,2]]
     * 输出：2
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 1000
     * 0 <= trust.length <= 104
     * trust[i].length == 2
     * trust[i] 互不相同
     * trust[i][0] != trust[i][1]
     * 1 <= trust[i][0], trust[i][1] <= n
     **/

    /*
    思路：简单的出入度统计
     */
    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        for (int[] t : trust) {
            in[t[1]]++;
            out[t[0]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (in[i] == n-1 && out[i]==0) {
                return i;
            }
        }
        return -1;
    }
}
