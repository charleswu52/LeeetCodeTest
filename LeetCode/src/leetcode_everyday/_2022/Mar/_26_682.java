package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/26 9:03
 */
public class _26_682 {
    /**
     * 每日一题：2022/3/26
     * <p>
     * 682. 棒球比赛
     * <p>
     * 难度：easy
     * <p>
     * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
     *
     * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
     *
     * 整数 x - 表示本回合新获得分数 x
     * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
     * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
     * 请你返回记录中所有得分的总和。
     * <p>
     * 示例1
     * <p>
     * 输入：ops = ["5","2","C","D","+"]
     *
     * 输出：30
     *
     * 解释：
     * "5" - 记录加 5 ，记录现在是 [5]
     * "2" - 记录加 2 ，记录现在是 [5, 2]
     * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
     * "D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
     * "+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
     * 所有得分的总和 5 + 10 + 15 = 30
     * <p>
     * 范围
     * <p>
     * 1 <= ops.length <= 1000
     * ops[i] 为 "C"、"D"、"+"，或者一个表示整数的字符串。整数范围是 [-3 * 104, 3 * 104]
     * 对于 "+" 操作，题目数据保证记录此操作时前面总是存在两个有效的分数
     * 对于 "C" 和 "D" 操作，题目数据保证记录此操作时前面总是存在一个有效的分数
     */

    /*
    思路：简单模拟
     */
    public int calPoints(String[] ops) {
        int ans = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
            } else if (ops[i].equals("D")) {
                list.add(list.get(list.size() - 1) * 2);
            } else if (ops[i].equals("C")) {
                list.remove(list.size() - 1);
            } else {
                list.add(Integer.parseInt(ops[i]));
            }
        }
        return list.stream().reduce(Integer::sum).orElse(0);

    }
}
