package tme._2021_1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/21 14:30
 */
public class Main_1 {
    /**
     * 腾讯音乐娱乐（TME）2021暑期实习生招聘技术类笔试题
     * [编程题]找不同
     * <p>
     * 牛牛最近迷上了《找不同》这个小游戏，在这个游戏中，每一轮，会给你两张很相似的照片，需要你指出其中的所有不同之处。
     * 这一天，牛牛玩着这个游戏，路过牛妹身旁，偶然间注意到牛妹正对着很多数字发呆。
     * 牛牛瞄了一眼数字，随手指了一个数字，说这个数字在这些数中只出现了一次。经过牛妹人工检验，发现牛牛说得对。
     * 牛妹非常好奇牛牛的这个新能力，觉得是因为牛牛玩《找不同》玩多了，于是对于这类不同于其它的部分特别敏感。
     * 为了进一步检测牛牛的能力，牛妹决定拟定一份问卷，让牛牛回答，
     *
     * 每份问卷中有若干道题目，每道题目含有若干个数字，需要牛牛快速回答出，每道题所给的数字中，最小的一个只出现了一次的数字是什么？
     *
     * 由于题量很多，显然不能让牛妹人工核对答案，于是向你求助，希望你能给予牛妹帮助。
     * <p>
     * 输入描述:
     * 第一行输入一个正整数 T(1<=T<=1000)，代表这份问卷的题目总数。
     * 对于每道题，第一行输入一个正整数 n(1<=n<=1000)，代表这道题中的数字个数。
     * 第二行输入 n 个正整数 a1,a2,...,an(1<=a_i<=1000)，代表这道题中的每个数字。
     * <p>
     * 输出描述:
     * 对于问卷中的每道问题，一行输出一个整数代表答案；特殊的，如果不存在这样的数字，则输出 -1 代表无解。
     * <p>
     * 输入例子1:
     * 2
     * 3
     * 6 6 6
     * 3
     * 6 9 6
     *
     * 输出例子1:
     * -1
     * 9
     * <p>
     * 例子说明1:
     * 第一组测试数据，所有数字均为 6，无解。
     * 第二组测试数据，两个数字为  6，一个数字为  9，根据题意可以发现，答案为数字  9.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int res = Integer.MAX_VALUE;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int temp = scanner.nextInt();
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> next = iterator.next();
                if (next.getValue() == 1 && next.getKey() < res) {
                    res = next.getKey();
                }
            }
            if (res != Integer.MAX_VALUE) {
                System.out.println(res);
            } else {
                System.out.println(-1);
            }
        }

    }



}
