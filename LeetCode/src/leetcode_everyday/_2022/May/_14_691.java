package leetcode_everyday._2022.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/5/14 9:01
 */
public class _14_691 {
    /**
     * 每日一题：2022/5/14
     * <p>
     * 691. 贴纸拼词
     * <p>
     * 难度：hard
     * <p>
     * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
     * <p>
     * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
     * <p>
     * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
     * <p>
     * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
     * <p>
     * 示例
     * <p>
     * 输入： stickers = ["with","example","science"], target = "thehat"
     * <p>
     * 输出：3
     * <p>
     * 解释：
     * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
     * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     * 此外，这是形成目标字符串所需的最小贴纸数量。
     * <p>
     */

    /*
    思路：记忆化搜索+状态转移
     */
    public int minStickers(String[] stickers, String target) {
        int m = target.length();
        int[] memo = new int[1 << m];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        int res = dp(stickers, target, memo, (1 << m) - 1);
        return res <= m ? res : -1;

    }

    /*
    动态规划 计算状态转移
     */
    private int dp(String[] stickers, String target, int[] memo, int mask) {
        int m = target.length();
        if (memo[mask] < 0) {
            int res = m + 1;
            for (String sticker : stickers) {
                int left = mask;
                int[] cnt = new int[26];
                for (int i = 0; i < sticker.length(); i++) {
                    cnt[sticker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < target.length(); i++) {
                    char ch = target.charAt(i);
                    if (((mask >> i) & 1) == 1 && cnt[ch - 'a'] > 0) {
                        cnt[ch - 'a']--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask) {
                    res = Math.min(res, dp(stickers, target, memo, left) + 1);
                }
            }
            memo[mask] = res;
        }
        return memo[mask];
    }
}
