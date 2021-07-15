package leetcode_hot100.top60;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/7/14 13:11
 */
public class _139 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 139. 单词拆分
     * 难度：medium
     * <p>
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     *
     * <p>
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     *
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     *
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *
     * <p>
     * 数据范围:
     */

    /*
    题目解析：
    将一个字符串进行拆分，判断其能否被拆分成单词字典中的单词

    思路： 动态规划

    定义 dp[i] 表示字符串s的前i个字符s[0...i-1]能否被空格切分成若干单词
    1. 初始化 dp=[False,⋯,False]，长度为 n+1。n 为字符串长度。dp[i] 表示 s 的前 i 位是否可以用 wordDict 中的单词表示。
    2. 初始化 dp[0]=True，空字符可以被表示。
    3. 遍历字符串的所有子串，遍历开始索引 i，遍历区间 [1,n]：
        遍历结束索引 j，遍历区间 [0,i)：
            若 dp[j]=True 且 s[j,⋯,i) 在 wordlist 中：dp[i]=True。解释：dp[j]=True 说明 s 的前 j 位可以用 wordDict 表示，
            则 s[j,⋯,i) 出现在 wordDict 中，说明 s 的前 j 位可以表示。
    4. 返回 dp[n]
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // 放到集合中搜索速度加快
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
