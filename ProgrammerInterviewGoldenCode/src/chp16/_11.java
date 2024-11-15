package chp16;

/**
 * @author WuChao
 * @since 2021/5/30 上午9:58
 */
public class _11 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.11. 跳水板
     * 难度: easy
     * <p>
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
     * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     *
     * <p>
     * 示例:
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： [3,4,5,6]
     * 解释：
     * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
     * <p>
     * 数据范围：
     * 0 < shorter <= longer
     * 0 <= k <= 100000
     */

    /*
    思路1：直观朴素解法
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{k * shorter};
        }
        int[] res = new int[k+1];
        for (int i = 0, j = k; i <= k; i++, j--) {
            res[i] = i * longer + j * shorter;
        }
        return res;
    }

    /*
    思路2：数学分析
     */
    public int[] divingBoard2(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        if (shorter == longer) {
            return new int[]{k * longer};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            ans[i] = shorter * (k - i) + longer * (i);
        }
        return ans;
    }




    }
