package leetcodetest.Jun;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2021/6/15 上午8:15
 */
public class _15 {
    /**
     * 每日一题：2021/6/15
     * 852. 山脉数组的峰顶索引
     * 难度: easy
     * <p>
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     * <p>
     *
     * <p>
     * 示例:
     * 输入：arr = [0,1,0]
     * 输出：1
     *
     * 输入：arr = [0,2,1,0]
     * 输出：1
     * <p>
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     * <p>
     * 数据范围：
     * 3 <= arr.length <= 104
     * 0 <= arr[i] <= 106
     * 题目数据保证 arr 是一个山脉数组
     *
     * 进阶：
     * 很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
     */

    /*
    题目解析：
    题目要求和示例都说的很明白，而且也解释说了数组保证是一个山脉数组，因此很容易使用遍历一次的方式得到
    但是进阶提出是否可以设置一个时间复杂度是O(log(n)) 的解决方案，很容易想到了是考察使用二分查找的方法
     */
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 2,ans=0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid+1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] arr = {3, 4, 5, 1};
        System.out.println(peakIndexInMountainArray(arr));
    }
}
