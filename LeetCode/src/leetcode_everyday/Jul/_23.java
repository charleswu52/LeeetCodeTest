package leetcode_everyday.Jul;

import java.util.BitSet;

/**
 * @author WuChao
 * @create 2021/7/23 10:54
 */
public class _23 {
    /**
     * <p> 每日一题：2021/7/23</p>
     * <p>1893. 检查是否区域内所有整数都被覆盖</p>
     * <p>难度: easy</p>
     * <p>
     * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
     * <p>
     * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
     * <p>
     * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
     * 输出：true
     * 解释：2 到 5 的每个整数都被覆盖了：
     * - 2 被第一个区间覆盖。
     * - 3 和 4 被第二个区间覆盖。
     * - 5 被第三个区间覆盖。
     * <p>
     * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
     * 输出：false
     * 解释：21 没有被任何一个区间覆盖。
     * </p>
     *
     * <p>范围</p>
     * <p>
     * 1 <= ranges.length <= 50
     * 1 <= starti <= endi <= 50
     * 1 <= left <= right <= 50
     * </p>
     */

    /*
    思路1：位图法
    巧妙利用BitSet的数据结构
    使用BitSet 将所有存在的区间置为1
    然后从left开始搜索，出现0的第一个位置，如果该位置大于left说明 [left,right]所有值均为1，否则为0
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        BitSet bitSet = new BitSet();
        for (int[] range : ranges) {
            //将位图中的 range[0] ~ range[1] + 1（不包括）设置成1
            bitSet.set(range[0], range[1] + 1);
        }
        // 从left开始从位图中获取第一个为0的下标
        int i = bitSet.nextClearBit(left);
        return i > right;
    }

    /*
    思路2：差分数组
    用差分数组 diff 维护相邻两个整数的被覆盖区间数量变化量，其中 diff[i] 对应覆盖整数 i 的区间数量相对于覆盖 i - 1 的区间数量变化量。
    这样，当遍历到闭区间 [l,r] 时，l 相对于 l - 1 被覆盖区间数量多 1，r + 1 相对于 r 被覆盖区间数量少 1。
    对应到差分数组上，我们需要将 diff[l] 加上 1，并将 {diff}[r + 1] 减去 1。

     在维护完差分数组 diff 后，我们遍历 diff 求前缀和得出覆盖每个整数的区间数量。下标 i 对应的被覆盖区间数量即为初始数量 0 加上 [1, i]
     闭区间的变化量之和。在计算被覆盖区间数量的同时，我们可以一并判断 [left,right] 闭区间内的所有整数是否都被覆盖。
     */

    public boolean isCovered2(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }
        // 前缀和
        int curr = 0;
        for (int i = 1; i <= 50; i++) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0) {
                return false;
            }
        }
        return true;

    }
}
