package leetcode_everyday.Sep;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/9/10 上午9:25
 */
public class _10_1894 {
    /**
     * 每日一题：2021/9/10
     * 1894. 找到需要补充粉笔的学生编号
     * 难度：medium
     * <p>
     * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，
     * 直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
     *
     * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，
     * 他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
     *
     * 请你返回需要 补充 粉笔的学生 编号 。
     * <p>
     * 示例 1：
     *
     * 输入：chalk = [5,1,5], k = 22
     * 输出：0
     * 解释：学生消耗粉笔情况如下：
     * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
     * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
     * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
     * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
     * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
     * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
     * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
     * <p>
     * chalk.length == n
     * 1 <= n <= 105
     * 1 <= chalk[i] <= 105
     * 1 <= k <= 109
     */

    /*
    思路1： 模拟(优化)

     */
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long total = 0;
        for (int num : chalk) {
            total += num;
        }
        k %= total;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (chalk[i] > k) {
                idx = i;
                break;
            }
            k -= chalk[i];
        }
        return idx;
    }

    /*
    思路2：前缀和 + 二分查找
    对思路1中的第二次遍历，也可以使用二分查找进行加速。
    对在数组chalk的遍历过程中，我们可以求出前缀和，记为数组pre，那么需要补充粉笔的学生编号i' 是最小的满足pre[i'] > k' 的下标i',可以通过
    二分查找在O(log n)的时间内找出
     */
    public int chalkReplacer2(int[] chalk, int k) {
        int n = chalk.length;
        if (chalk[0] > k) {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            chalk[i] += chalk[i - 1];
            if (chalk[i] > k) {
                return i;
            }
        }
        k %= chalk[n - 1];
        return binarySearch(chalk, k);

    }

    public int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


        @Test
    public void test() {
        int[] chalk = {3, 4, 1, 2};
        int k = 25;
        System.out.println(chalkReplacer(chalk, k));
    }
}
