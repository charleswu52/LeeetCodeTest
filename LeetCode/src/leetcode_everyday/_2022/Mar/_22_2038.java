package leetcode_everyday._2022.Mar;

/**
 * @author WuChao
 * @create 2022/3/22 8:41
 */
public class _22_2038 {
    /**
     * 每日一题：2022/3/22
     * <p>
     * 2038. 如果相邻两个颜色均相同则删除当前颜色
     * <p>
     * 难度：medium
     * <p>
     * 总共有 n 个颜色片段排成一列，每个颜色片段要么是 'A' 要么是 'B' 。给你一个长度为 n 的字符串 colors ，
     * 其中 colors[i] 表示第 i 个颜色片段的颜色。
     *
     * Alice 和 Bob 在玩一个游戏，他们 轮流 从这个字符串中删除颜色。Alice 先手。
     *
     * 如果一个颜色片段为 'A' 且 相邻两个颜色 都是颜色 'A' ，那么 Alice 可以删除该颜色片段。Alice 不可以 删除任何颜色 'B' 片段。
     * 如果一个颜色片段为 'B' 且 相邻两个颜色 都是颜色 'B' ，那么 Bob 可以删除该颜色片段。Bob 不可以 删除任何颜色 'A' 片段。
     * Alice 和 Bob 不能 从字符串两端删除颜色片段。
     * 如果其中一人无法继续操作，则该玩家 输 掉游戏且另一玩家 获胜 。
     * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回 true，否则 Bob 获胜，返回 false。
     * <p>
     * 示例1
     * <p>
     * 输入：colors = "AAABABB"
     * 输出：true
     * 解释：
     * AAABABB -> AABABB
     * Alice 先操作。
     * 她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
     *
     * 现在轮到 Bob 操作。
     * Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
     * 因此，Alice 获胜，返回 true 。
     * <p>
     * 范围
     * <p>
     * 1 <= colors.length <= 10^5
     * colors 只包含字母 'A' 和 'B'
     */

    /*
    思路1： 模拟 寻找每个人可以删除的情况  -->> 超时
     */
    public boolean winnerOfGame(String colors) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colors.length(); i++) {
            sb.append(colors.charAt(i));
        }
        while (true) {
            int aaa = sb.indexOf("AAA");
            if (aaa == -1) {
                return false;
            }
            sb.delete(aaa + 1, aaa + 2);
            int bbb = sb.indexOf("BBB");
            if (bbb == -1) {
                return true;
            }
            sb.delete(bbb + 1, bbb + 2);
        }
    }

    /*
    思路2：巧思
     */
    public boolean winnerOfGame2(String colors) {
        int count = 0;
        for (int i = 0; i < colors.length()-2; i++) {
            if(colors.substring(i,i+3).equals("AAA")) count++;
            if(colors.substring(i,i+3).equals("BBB")) count--;
        }
        return count > 0;
    }
}
