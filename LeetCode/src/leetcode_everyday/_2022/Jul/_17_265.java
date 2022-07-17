package leetcode_everyday._2022.Jul;

/**
 * @author WuChao
 * @create 2022/7/17 9:53
 */
public class _17_265 {
    /**
     * 每日一题：2022/7/17
     * <p>
     * 565. 数组嵌套
     * <p>
     * 难度：medium
     * <p>
     * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，
     * 其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
     * <p>
     * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
     * <p>
     * 示例
     * <p>
     * 输入: A = [5,4,0,3,1,6,2]
     * 输出: 4
     * 解释:
     * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
     * <p>
     * 其中一种最长的 S[K]:
     * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
     * <p>
     * 范围
     * <p>
     * N是[1, 20,000]之间的整数。
     * A中不含有重复的元素。
     * A中的元素大小在[0, N-1]之间。
     */

    /*
    0 5 6 2
    5 6 2 0   ==> 4

    1 4
    4 1  => 2

    思路1： 简单模拟 ==> 超时！
     */
    public int arrayNesting(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (res > nums.length / 2) {
                return res;
            }
            int startIdx = i, idx = startIdx, val = Integer.MIN_VALUE,count=0;
            while (val != startIdx) {
                val = nums[idx];
                idx = val;
                count++;
            }
            res = Math.max(res, count);
        }
        return res;

    }
    /*
    思路2：简单模拟优化
    设置访问数组 对已经访问过的元素 不再一次循环中重复访问
     */
    public int arrayNesting2(int[] nums) {
        int res = -1,len = nums.length;
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            int count=0;
            while (!visited[i]) {
                visited[i] = true;
                i = nums[i];
                count++;
            }
            res = Math.max(res, count);
        }
        return res;

    }
}
