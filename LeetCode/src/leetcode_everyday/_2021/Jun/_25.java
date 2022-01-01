package leetcode_everyday._2021.Jun;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/25 9:20
 */
public class _25 {
    /**
     * 每日一题：2021/6/25
     * 752. 打开转盘锁
     * 难度: medium
     * <p>
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * <p>
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * <p>
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * <p>
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     * <p>
     * 示例
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，范围：
     * 1 因为当拨动到 "0102" 时这个锁就会被锁定。<= points.length <= 300
     * <p>
     * 数据范围：
     * 死亡列表 deadends 的长度范围为 [1, 500]。
     * 目标数字 target 不会在 deadends 之中。
     * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
     */

    /*
    思路：
    因为要求的是从 0000 到目标数字转动的最小次数，因此对于所有可能的情况使用搜索的方式，这种情况适合用广搜而非深搜，因为找到目标串要求次数最少
    而不在于转化过程的路径。
    从 "0000" 开始BFS
     */
    public int openLock(String[] deadends, String target) {
        // 先记录deadends
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        // 已经走过的密码也记录下来，避免重复
        Set<String> visited = new HashSet<>();
        // 使用BFS搜索 自然要用队列保存中间结果
        Queue<String> queue = new LinkedList<>();
        // 从起点 "0000" 开始
        queue.offer("0000");
        visited.add("0000");
        int res = 0; // 最少转化次数

        // 搜索
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return res;
                }

                // 将未遍历到的点节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = downOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 一层遍历完
            res++;
        }
        return -1;



    }

    /**
     * 指定位上向上拨动一次的变化
     */
    public static String upOne(String str, int idx) {
        char[] chars = str.toCharArray();
        if (chars[idx] == '9') {
            chars[idx] = '0';
        } else {
            chars[idx] += 1;
        }
        return new String(chars);
    }

    /**
     * 指定位上向下拨动一次的变化
     */
    public static String downOne(String str, int idx) {
        char[] chars = str.toCharArray();
        if (chars[idx] == '0') {
            chars[idx] = '9';
        } else {
            chars[idx] -= 1;
        }
        return new String(chars);
    }

    
}
