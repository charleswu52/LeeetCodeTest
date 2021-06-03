package chp16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/6/2 上午9:53
 */
public class _21 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.21. 交换和
     * 难度: medium
     * <p>
     * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
     * <p>
     * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
     * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
     *
     * <p>
     * 示例:
     * 输入：
     * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
     * 输出: [1, 3]
     * <p>
     * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
     * 输出: []
     * <p>
     * 数据范围：
     * 1 <= array1.length, array2.length <= 100000
     */

    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = Arrays.stream(array1).sum();
        int sum2 = Arrays.stream(array2).sum();

        double minusd = Math.abs(sum1 - sum2) / 2.0;
        if (minusd != (int) minusd) {
            return new int[]{};
        }
        int minus = (int) minusd;

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : array1) {
            set1.add(num);
        }
        for (int num : array2) {
            set2.add(num);
        }
        int len1 = set1.size();
        int len2 = set2.size();
        if (len1 <= len2) {
            for (Integer integer : set1) {
                if (minus == 0) {
                    if (set2.contains(integer)) {
                        return new int[]{integer, integer};
                    }
                } else {
                    int a = integer - minus, b = integer + minus;
                    if (set2.contains(a)) {
                        return new int[]{integer, a};
                    } else if (set2.contains(b)) {
                        return new int[]{integer, b};
                    }
                }
            }
        } else {
            for (Integer integer : set2) {
                if (minus == 0) {
                    if (set1.contains(integer)) {
                        return new int[]{integer, integer};
                    }
                } else {
                    int a = integer - minus, b = integer + minus;
                    if (set1.contains(a)) {
                        return new int[]{a,integer};
                    } else if (set1.contains(b)) {
                        return new int[]{b,integer};
                    }
                }
            }
        }
        return new int[]{};


    }

}
