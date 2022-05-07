package leetcode_everyday._2022.May;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author WuChao
 * @create 2022/5/7 9:24
 */
public class _7_433 {
    /**
     * 每日一题：2022/5/7
     * <p>
     * 433. 最小基因变化
     * <p>
     * 难度：medium
     * <p>
     * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
     * <p>
     * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
     * <p>
     * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
     * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
     * <p>
     * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
     * <p>
     * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
     * <p>
     * 示例
     * <p>
     * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
     * <p>
     * 输出：2
     * <p>
     * 范围
     * <p>
     * start.length == 8
     * end.length == 8
     * 0 <= bank.length <= 10
     * bank[i].length == 8
     * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
     */

    /*
    思路：广度优先搜索
     */
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> candidate = new HashSet<>();
        for (String s : bank) {
            candidate.add(s);
        }
        if (!candidate.contains(end)) {
            return -1;
        }
        char[] keys = {'A', 'G', 'C', 'T'};
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != cur.charAt(j)) {
                            StringBuilder stringBuilder = new StringBuilder(cur);
                            stringBuilder.setCharAt(j, keys[k]);
                            String next = stringBuilder.toString();
                            if (!visited.contains(next) && candidate.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                visited.add(next);
                                queue.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;

    }
}
