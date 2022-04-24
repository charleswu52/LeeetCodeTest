package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/24 8:42
 */
public class _24_868 {
    /**
     * 每日一题：2022/4/24
     * <p>
     * 868. 二进制间距
     * <p>
     * 难度：easy
     * <p>
     * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。
     * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。
     * 例如，"1001" 中的两个 1 的距离为 3 。
     * <p>
     * 示例
     * <p>
     * 输入：n = 22
     * <p>
     * 输出：2
     * <p>
     * 解释：22 的二进制是 "10110" 。
     * 在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
     * 第一对相邻的 1 中，两个 1 之间的距离为 2 。
     * 第二对相邻的 1 中，两个 1 之间的距离为 1 。
     * 答案取两个距离之中最大的，也就是 2 。
     * <p>
     * 范围
     * <p>
     * 1 <= n <= 109
     */

    /*
    思路：简单模拟
    转换成二进制表示进行遍历
     */
    public int binaryGap(int n) {
        String binaryString = Integer.toBinaryString(n);
        int ans = 0, pre = -1;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                if (pre != -1) {
                    ans = Math.max(ans, i - pre);
                }
                pre = i;
            }
        }
        return ans;
    }

    /*
    思路：位运算
     */
    public int binaryGap2(int n) {
        int ans = 0, pre = -1,idx = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (pre != -1) {
                    ans = Math.max(ans, idx - pre);
                }
                pre = idx;
            }
            n >>= 1;
            idx++;
        }
        return ans;

    }
}