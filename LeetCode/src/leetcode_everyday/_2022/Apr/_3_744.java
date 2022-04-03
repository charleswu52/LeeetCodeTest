package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/3 16:57
 */
public class _3_744 {
    /**
     * 每日一题：2022/4/3
     * <p>
     * 744. 寻找比目标字母大的最小字母
     * <p>
     * 难度：easy
     * <p>
     * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
     * <p>
     * 在比较时，字母是依序循环出现的。举个例子：
     * <p>
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     * <p>
     * 示例
     * <p>
     * 输入: letters = ["c","f","j"], target = "d"
     * <p>
     * 输出: "f"
     * <p>
     * 范围
     * <p>
     * 2 <= letters.length <= 10^4
     * letters[i] 是一个小写字母
     * letters 按非递减顺序排序
     * letters 最少包含两个不同的字母
     * target 是一个小写字母
     */

    /*
    思路：二分搜索
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < letters[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];

    }
}
