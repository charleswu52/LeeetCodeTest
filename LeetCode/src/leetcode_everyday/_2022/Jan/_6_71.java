package leetcode_everyday._2022.Jan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author WuChao
 * @create 2022/1/6 21:39
 */
public class _6_71 {
    /**
     * 每日一题：2022/1/6
     * <p>
     * 71. 简化路径
     * <p>
     * 难度：medium
     * <p>
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     * <p>
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
     * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，
     * 任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     * <p>
     * 请注意，返回的 规范路径 必须遵循下述格式：
     * <p>
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：path = "/home/"
     * <p>
     * 输出："/home"
     * <p>
     * 解释：注意，最后一个目录名后面没有斜杠。
     * <p>
     * 示例 2：
     * <p>
     * 输入：path = "/../"
     * <p>
     * 输出："/"
     * <p>
     * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
     * <p>
     * 范围
     * <p>
     * 1 <= path.length <= 3000
     * <p>
     * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
     * <p>
     * path 是一个有效的 Unix 风格绝对路径。
     **/

    /*
    思路：使用 栈 进行模拟
    从前到后处理 每个 path, 每次以 item 为单位进行处理(有效的文件名)，根据item的值进行分情况讨论：
        item 为有效值：存入栈中
        item 为.. ：弹出栈顶元素
        item 为. ：不作处理
     */
    public String simplifyPath(String path) {
        Deque<String> stringDeque = new ArrayDeque<>();
        int n = path.length();
        for (int i = 1; i < n; ) {
            if (path.charAt(i) == '/' && ++i >=0) continue;
            int j = i + 1;
            while (j < n && path.charAt(j) != '/') j++;
            String item = path.substring(i, j);
            if (item.equals("..")) {
                if (!stringDeque.isEmpty()) {
                    stringDeque.pollLast();
                }
            } else if (!item.equals(".")) {
                stringDeque.addLast(item);
            }
            i = j;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stringDeque.isEmpty()) {
            stringBuilder.append("/" + stringDeque.pollFirst());
        }
        return stringBuilder.length() == 0 ? "/" : stringBuilder.toString();


    }
}
