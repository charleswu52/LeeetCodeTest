import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/28 下午2:03
 */
public class _57_2 {
    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * 难度: easy
     * <p>
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * <p>
     * 例如：
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     * <p>
     * 数据范围：
     * 1 <= target <= 10^5
     */

    /**
     * 方法1，使用滑动窗口的方式，累计窗口的值，大于等于目标值的时候向左减小窗口，小于目标值的时候向右增加窗口
     * 此外，在等于目标值的时候要把序列添加到结果序列集中
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        // 使用滑动窗口的思路
        int i = 1, j = 2, sum = 3;// 初始化左右边界和sum值
        List<int[]> res = new ArrayList<>();
        while (i < j) {
            if (sum == target) {
                int[] ans = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    ans[k - i] = k;
                }
                res.add(ans);
            }

            if (sum >= target) { // 向左减小滑动窗口
                sum -= i;
                i++;
            } else { //向右增大滑动窗口
                j++;
                sum += j;
            }
        }
        return res.toArray(new int[0][]);
    }

}
