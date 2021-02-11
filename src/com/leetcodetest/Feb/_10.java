package com.leetcodetest.Feb;

public class _10 {
    /**
     * 每日一题：2021/2/10
     * 567. 字符串的排列
     * 难度: medium
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     * <p>
     * 示例：
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     * <p>
     * 数据范围：
     * 输入的字符串只包含小写字母
     * 两个字符串的长度都在 [1, 10,000] 之间
     */


    /*
    题目解析：
    方法：双指针（滑动窗口）
    这道题是 76. 最小覆盖子串 的简单版本。罗列一些要点：

    排列不讲究顺序，但是字符出现的 种类 和 次数 要恰好对应相等；
    暴力解法做了很多重复和没有必要的工作；
    可以采用双指针「一前一后、交替向右边移动」的算法，少考虑很多暴力解法须要考虑的子区间；
    由于我们只关心子区间里的元素的种数和各个字符出现的次数。因此须要统计字符串 s1 出现的字符的种数和次数，
    和在字符串 s2 上的两个变量所确定的滑动窗口中出现的字符种数和次数；

    还须要设计一个变量 winCount，表示滑动窗口在 s2 上滑动的时候，出现在 s1 中的字符的种类数，规则如下：
    如果某一个字符出现的次数恰好等于 s1 中对应字符出现的次数，winCount += 1。

    注意：满足对应字符出现的次数大于等于 s1 中出现的次数的时候才加 1；
    在左边界向右移动的过程当中，某一个字符对应的次数减少以后，恰好小于了 s1 对应的字符出现的次数，winCount -= 1；
    当滑动窗口中出现的字符种类数和 s1 中出现的字符种类数相等（根据 winCount 的定义，对应次数也相等），
    并且 s2 上的滑动窗口的长度等于字符串 s1 的长度的时候，就找到了 s1 的一个排列。

     */
    public boolean checkInclusion(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for (int i = 0; i < pLen; i++) {
            pFreq[pattern[i] - 'a']++;
        }

        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0) {
                pCount++;
            }
        }

        int left = 0;
        int right = 0;
        // 当滑动窗口中的某个字符个数与 s1 中对应相等的时候才计数
        int winCount = 0;
        while (right < tLen) {
            if (pFreq[text[right] - 'a'] > 0) {
                winFreq[text[right] - 'a']++;
                if (winFreq[text[right] - 'a'] == pFreq[text[right] - 'a']) {
                    winCount++;
                }
            }
            right++;

            while (pCount == winCount) {
                if (right - left == pLen) {
                    return true;
                }
                if (pFreq[text[left] - 'a'] > 0) {
                    winFreq[text[left] - 'a']--;
                    if (winFreq[text[left] - 'a'] < pFreq[text[left] - 'a']) {
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }


    public void _21_2_10() {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }
}
