package leetcode_everyday._2022.May;

import java.util.Arrays;

/**
 * @author WuChao
 * @create 2022/5/4 14:46
 */
public class _3_937 {
    /**
     * 每日一题：2022/5/3
     * <p>
     * 937. 重新排列日志文件
     * <p>
     * 难度：easy
     * <p>
     * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
     * <p>
     * 有两种不同类型的日志：
     * <p>
     * 字母日志：除标识符之外，所有字均由小写字母组成
     * 数字日志：除标识符之外，所有字均由数字组成
     * 请按下述规则将日志重新排序：
     * <p>
     * 所有 字母日志 都排在 数字日志 之前。
     * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
     * 数字日志 应该保留原来的相对顺序。
     * 返回日志的最终顺序。
     *
     *
     * <p>
     * 示例
     * <p>
     * 输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
     * <p>
     * 输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
     * <p>
     * 解释：
     * 字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
     * 数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
     * <p>
     * 范围
     * <p>
     * 1 <= logs.length <= 100
     * 3 <= logs[i].length <= 100
     * logs[i] 中，字与字之间都用 单个 空格分隔
     * 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
     */

    /*
    思路：自定义排序

     */

    class Pair{
        String log;
        int index;

        public Pair(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }

    public int logCompare(Pair pair1, Pair pair2) {
        String log1 = pair1.log, log2 = pair2.log;
        int index1 = pair1.index, index2 = pair2.index;
        String[] split1 = log1.split(" ",2);
        String[] split2 = log2.split(" ",2);
        boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
        boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
        if (isDigit1 && isDigit2) {
            return index1 - index2;
        }
        if (!isDigit1 && !isDigit2) {
            int sc = split1[1].compareTo(split2[1]);
            if (sc != 0) {
                return sc;
            }
            return split1[0].compareTo(split2[0]);
        }
        return isDigit1 ? 1 : -1;
    }
    public String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        Pair[] pairs = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairs[i] = new Pair(logs[i], i);
        }
        Arrays.sort(pairs, (a, b) -> logCompare(a, b));
        String[] reordered = new String[length];
        for (int i = 0; i < length; i++) {
            reordered[i] = pairs[i].log;
        }
        return reordered;

    }
}
