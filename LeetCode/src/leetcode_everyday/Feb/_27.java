package leetcode_everyday.Feb;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/2/27 上午9:46
 */
public class _27 {
    /**
     * 每日一题：2021/2/27
     * 395. 至少有K个重复字符的最长子串
     * 难度: medium
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
     * <p>
     * 输入:
     * s = "aaabb", k = 3
     * 输出:
     * 3
     * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * <p>
     * 输入:
     * s = "ababbc", k = 2
     * <p>
     * 输出:
     * 5
     * <p>
     * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     * <p>
     * 数据范围：
     */

    /*
    可以将问题进行转换：
    求字符串的最长子串，其中子串的最小度不能<k
    原本的解题思路是使用滑动窗口，但是感觉并不好做，参考其他题解
    这里只是学着使用了HashMap，并自定义排序算法将map中value进行排序
     */
    public int longestSubstring(String s, int k) {
        HashMap<Character, int[]> store = new HashMap<Character, int[]>();
        for (int i = 0; i < s.length(); i++) {
            if (!store.containsKey(s.charAt(i))) {
                store.put(s.charAt(i), new int[]{i, i, 1, 1});
            } else {
                store.get(s.charAt(i))[1] = i;
                store.get(s.charAt(i))[2] += 1;
                store.get(s.charAt(i))[3] = i - store.get(s.charAt(i))[0] + 1;
            }
        }
        List<Map.Entry<Character, int[]>> entryList = new ArrayList<Map.Entry<Character, int[]>>(store.entrySet());
        // 排序前
        for (int i = 0; i < entryList.size(); i++) {
            System.out.print(entryList.get(i).getKey() + ":");
            System.out.println(Arrays.toString(entryList.get(i).getValue()));

        }

        // 排序实现
        Collections.sort(entryList, new Comparator<Map.Entry<Character, int[]>>() {
            @Override
            public int compare(Map.Entry<Character, int[]> o1, Map.Entry<Character, int[]> o2) {
                if (o1.getValue()[2] == o2.getValue()[2]) {
                    return o2.getValue()[3] - o1.getValue()[3];
                } else {
                    return o2.getValue()[1] - o1.getValue()[2];
                }
            }
        });
        // 排序后
        System.out.println("---------------------");

        for (int i = 0; i < entryList.size(); i++) {
            System.out.print(entryList.get(i).getKey() + ":");
            System.out.println(Arrays.toString(entryList.get(i).getValue()));

        }
        int res = 0;

        return res;
    }

    /*
    参考题解：复习递归的用法
    题目解析：要求的一个最长的子字符串的长度，该子字符串中每个字符出现的次数都最少为 k。

    求最长子字符串/区间的这类题一般可以用滑动窗口来做，但是本题滑动窗口的代码不好写，我改用递归。也借本题来帮助大家理解递归。

    重点：我们在调用递归函数的时候，把递归函数当做普通函数（黑箱）来调用，即明白该函数的输入输出是什么，而不用管此函数内部在做什么。
    下面是详细讲解。

    1.递归最基本的是记住递归函数的含义（务必牢记函数定义）：
    本题的 longestSubstring(s, k) 函数表示的就是题意，即求一个最长的子字符串的长度，该子字符串中每个字符出现的次数都最少为 k。
    函数入参 s 是表示源字符串；k 是限制条件，即子字符串中每个字符最少出现的次数；函数返回结果是满足题意的最长子字符串长度。

    2.递归的终止条件（能直接写出的最简单 case）：如果字符串 s 的长度少于 k，那么一定不存在满足题意的子字符串，返回 0；

    3.调用递归（重点）：如果一个字符 c 在 s 中出现的次数少于 k 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。
    所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果：把 s 按照 c 分割（分割后每个子串都不包含 c），得到很多子字符串 t；
    下一步要求 t 作为源字符串的时候，它的最长的满足题意的子字符串长度（到现在为止，我们把大问题分割为了小问题(s → t)）。
    此时我们发现，恰好已经定义了函数 longestSubstring(s, k) 就是来解决这个问题的！
    所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归。

    4.未进入递归时的返回结果：如果 s 中的每个字符出现的次数都大于 k 次，那么 s 就是我们要求的字符串，直接返回该字符串的长度。

    总之，通过上面的分析，我们看出了：我们不是为了递归而递归。而是因为我们把大问题拆解成了小问题，恰好有函数可以解决小问题，所以直接用这个函数。
    由于这个函数正好是本身，所以我们把此现象叫做递归。小问题是原因，递归是结果。而递归函数到底怎么一层层展开与终止的，不要用大脑去想，这是计算机干的事。
    我们只用把递归函数当做一个能解决问题的黑箱就够了，把更多的注意力放在拆解子问题、递归终止条件、递归函数的正确性上来。
     */

    public int longestSubstring2(String s, int k) {
        if (s.length() < k) return 0;
        HashMap<Character, Integer> counter = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            if (counter.get(c) < k) {
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstring2(t, k));
                }
                return res;
            }
        }
        return s.length();
    }

    public void _21_2_27() {
        String s = "dabcbbc";
        int k = 3;
        System.out.println(longestSubstring2(s, k));
    }
}
