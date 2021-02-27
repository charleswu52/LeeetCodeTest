package com.leetcodetest.Feb;

import java.util.*;

public class _26 {
    /**
     * 每日一题：2021/2/26
     * 1178. 猜字谜
     * 难度: hard
     * 国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
     * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
     * 单词 word 中包含谜面 puzzle 的第一个字母。
     * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
     * <p>
     * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）
     * 以及 "based"（其中的 "s" 没有出现在谜面中）。
     * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
     * <p>
     * 输入：
     * words = ["aaaa","asas","able","ability","actt","actor","access"],
     * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
     * 输出：[1,1,3,2,4,0]
     * 解释：
     * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
     * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
     * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
     * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
     * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
     * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
     * <p>
     * 数据范围：
     * 1 <= words.length <= 10^5
     * 4 <= words[i].length <= 50
     * 1 <= puzzles.length <= 10^4
     * puzzles[i].length == 7
     * words[i][j], puzzles[i][j] 都是小写英文字母。
     * 每个 puzzles[i] 所包含的字符都不重复。
     */


    /*
    暴力的思路：让所有 words 和 puzzle 两两匹配
    但是这样肯定是不行的，时间复杂度到了 O(M * N) = 10 ^ 9，会超时。
    一个简单的思路是，对于每个 puzzle 没有必要遍历所以 words，只用找符合条件的 words 出现了多少次就行了。这就是很多题解的思路：状态压缩。
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<LinkedHashSet> wordSet = new ArrayList<>();
        List<LinkedHashSet> puzzleSet = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (String word : words) {
            LinkedHashSet<Character> temp = new LinkedHashSet<Character>();
            for (int i = 0; i < word.length(); i++) {
                temp.add(word.charAt(i));
            }
            System.out.println(temp);
            wordSet.add(temp);
        }
        for (String puzzle : puzzles) {
            LinkedHashSet<Character> temp = new LinkedHashSet<Character>();
            for (int i = 0; i < puzzle.length(); i++) {
                temp.add(puzzle.charAt(i));
            }

            System.out.println(temp);
            puzzleSet.add(temp);
        }

        for (int i = 0; i < wordSet.size(); i++) {

        }


        return new ArrayList<>();
    }

    /**
     * 其他思路：状态压缩
     * 题目给出了两个条件：
     * 单词 word 中包含谜面 puzzle 的第一个字母。
     * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
     * <p>
     * 第一步：状态压缩
     * 注意题目的第二个条件只要求能找到（出现一次即可），对出现次数没要求。
     * 为了解决这个问题，我们可以使用二进制数字来表每个 word 和 puzzle，该二进制数字就是 word 和 puzzle的特征。
     * 这道题只包含26个小写字母，可以压缩到一个 int 中。int 中的每一位取0和1表示字符是否出现过。比如 "aabb" 可以用 11 表示，
     * "accc" 可以用 101 表示。
     * 可以看出不同的单词可能映射成同一个数字，比如 "aabbb" 和 "ab" 都映射成了 11。这就是状态压缩。
     * 把每个 word 都做状态压缩，用 hashmap 保存每个二进制数字出现的次数。
     * <p>
     * 第二步：匹配
     * 我们要用puzzle[0] + subset(puzzle[1:N - 1]) 对应的二进制数字去跟 word 的二进制做匹配。
     * <p>
     * 很多题解都在讨论二进制表示下的 subset 怎么求，我觉得都不好理解，直接做一下「78. 子集」不就得了？暴力求出每个puzzle[1:N - 1]的全排列，
     * 然后计算每个排列下puzzle[0] + subset(puzzle[1:N - 1])对应的二进制数字。
     * <p>
     * 题目说了 puzzle 的长度为 7 位，subset(puzzle[1:N - 1]) 的是时间复杂度为 O(2 ^ N) = 2^6 = 64 次计算，比较快。
     * 求出puzzle[0] + subset(puzzle[1:N - 1]) 对应的二进制数字之后，累加 hashmap 中该二进制数字出现的次数，就是该 puzzle 对应的 word 有多少。
     */
    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        // 状态压缩
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            System.out.println(mask);
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        // 匹配
        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集方法一
            // for (int choose = 0; choose < (1 << 6); ++choose) {
            //     int mask = 0;
            //     for (int i = 0; i < 6; ++i) {
            //         if ((choose & (1 << i)) != 0) {
            //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //         }
            //     }
            //     mask |= (1 << (puzzle.charAt(0) - 'a'));
            //     if (frequency.containsKey(mask)) {
            //         total += frequency.get(mask);
            //     }
            // }

            // 枚举子集方法二
            int mask = 0;
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask;
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }


    public void _21_2_26() {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        System.out.println(findNumOfValidWords2(words, puzzles));

    }


}
