package chp17;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author WuChao
 * @create 2021/6/10 上午8:34
 */
public class _18 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.18. 最短超串
     * 难度: medium
     * <p>
     * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
     * <p>
     * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
     * <p>
     * 示例:
     * 输入:
     * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
     * small = [1,5,9]
     * 输出: [7,10]
     * <p>
     * 输入:
     * big = [1,2,3]
     * small = [4]
     * 输出: []
     * <p>
     * 数据范围：
     * big.length <= 100000
     * 1 <= small.length <= 100000
     */

    /*
    题目解析：
    复习之前很久不做的滑动窗口
     */

    /*
    思路：滑动窗口

     */
    public int[] shortestSeq(int[] big, int[] small) {
        int n = big.length;
        if (n == 0) {
            return new int[]{};
        }
        int[] res={};
        HashMap<Integer, Integer> need = new HashMap<>();
        int minLen = n, diff = 0;
        for (int t : small) {
            need.put(t, need.getOrDefault(t, 0) + 1);
            diff++;
        }

        int l = 0, r = 0;   // 滑动窗口的左右边界
        while (r < n) {
            if (need.containsKey(big[r])) {
                if (need.get(big[r]) > 0) {
                    diff--;
                }
                need.put(big[r], need.get(big[r]) - 1);
            }
            while (diff == 0) {
                if (r - l < minLen) {
                    minLen = r - l;
                    res = new int[]{l, r};
                }
                if (need.containsKey(big[l])) {
                    need.put(big[l], need.get(big[l]) + 1);
                    if (need.get(big[l]) > 0) {
                        diff++;
                    }
                }
                l++;
            }
            r++;
        }
        return res;


    }

    @Test
    public void test() {
        int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] small = {1, 5, 9};
        System.out.println(Arrays.toString(shortestSeq(big, small)));

    }


}
