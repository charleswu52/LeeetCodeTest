package leetcode_everyday._2022.Feb;

/**
 * @author WuChao
 * @create 2022/2/21 15:56
 */
public class _21_838 {
    /**
     * 每日一题：2022/2/21
     * <p>
     * 838. 推多米诺
     * <p>
     * 难度：medium
     * <p>
     * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
     * <p>
     * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
     * <p>
     * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
     * <p>
     * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
     * <p>
     * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
     * <p>
     * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
     * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
     * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
     * 返回表示最终状态的字符串。
     * <p>
     * 示例
     * <p>
     * 输入：dominoes = ".L.R...LR..L.."
     * <p>
     * 输出："LL.RR.LLRRLL.."
     * <p>
     * 范围
     * <p>
     * n == dominoes.length
     * 1 <= n <= 10^5
     * dominoes[i] 为 'L'、'R' 或 '.'
     */

    /*
    思路1：双指针
     */
    public String pushDominoes(String dominoes) {
        dominoes = "L" + dominoes + "R"; // 在原先的多米诺骨牌序列的基础之上添加两个额外的”虚拟“骨牌
        int left = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int right = 1; right < dominoes.length(); right++) {
            if (dominoes.charAt(right) == '.') {
                continue;
            }
            if (left != 0) {
                stringBuilder.append(dominoes.charAt(left));
            }
            int mid = right - left - 1;
            if (dominoes.charAt(left) == dominoes.charAt(right)) {
                for (int i = 0; i < mid; i++) {
                    stringBuilder.append(dominoes.charAt(left));
                }
            } else if (dominoes.charAt(left) == 'L' && dominoes.charAt(right) == 'R') {
                for (int i = 0; i < mid; i++) {
                    stringBuilder.append(".");
                }
            } else {
                for (int i = 0; i < mid / 2; i++) {
                    stringBuilder.append('R');
                }
                if (mid % 2 == 1) {
                    stringBuilder.append('.');
                }
                for (int i = 0; i < mid / 2; i++) {
                    stringBuilder.append('L');
                }
            }
            left = right;
        }
        return stringBuilder.toString();


    }
}
