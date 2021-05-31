package chp16;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/31 上午10:59
 */
public class _16 {
    /**
     * 程序员面试金典(version 6) -  面试题 16.16. 部分排序
     * 难度: medium
     * <p>
     * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     * <p>
     * 示例:
     * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
     * 输出： [3,9]
     * <p>
     * 数据范围：
     * 0 <= len(array) <= 1000000
     */

    /*
    题目解析：
    注意题目中没用表达，默认要求是按升序排列的

     */
    /*
    思路1：复制原数组，然后排序，比较不相同的数字存在的区间

     */
    public int[] subSort(int[] array) {
        int[] clone = array.clone();
        Arrays.sort(clone);
        int[] ans = new int[2];
        int left = -1,right = -1;
        for (int i = 0; i < array.length; i++) {
            if (clone[i] != array[i]) {
                if (left == -1) {
                    left = i;
                }
                right = i;
            }
        }
        ans[0] = left;
        ans[1] = right;
        return ans;
    }

    /*
    思路2： 一次遍历，双指针
     */
    public int[] subSort2(int[] array) {
        int m = -1, n = -1;
        int[] ans = new int[2];
        int length = array.length;
        if (length > 1) {
            //从左向右遍历，如果当前元素比它之前的最大的元素小，说明不是升序的，更新n为当前元素索引，继续遍历直到末尾
            //从右向左遍历，如果当前元素比它之后的最小的元素大，说明不是降序的，更新m为当前元素索引，继续遍历直到开始
            int max = array[0];
            int min = array[length - 1];
            for (int begin = 0; begin < length; begin++) {
                int end = length - 1 - begin;
                if (array[begin] < max) {
                    n = begin;
                } else {
                    max = array[begin];
                }
                if (array[end] > min) {
                    m = end;
                } else {
                    min = array[end];
                }
            }
        }
        ans[0] = m;
        ans[1] = n;
        return ans;

    }

    /*
    思路2：还是一次遍历，解释好于上面的代码，思路一致
    对于元素 a[i] 来说，如果它左边存在大于 a[i] 的元素，那么 a[i] 是一定要参与到排序里去的。
    或者说如果它右边存在小于 a[i] 的元素，那么 a[i] 也是要参与到排序里去的

    所以我们只需要寻找最靠右的那个数（满足左边存在大于它的数），和最靠左的那个数（满足右边存在小于它的数），那么这两个数之间就是要排序的区间了。
    为什么最靠右的那个（满足左边存在大于它的数）数一定能保证右边没有更小的数了呢？因为如果右边还有更小的数，那么那个更小的数才是更靠右的啊，这就矛盾了。

    所以我们只需要从左到右扫描一遍，用一个变量维护一下最大值就行了，然后反向再遍历一遍，维护一个最小值。
     */

    public int[] subSort3(int[] array) {
        int n = array.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int left = -1, right = -1;
        for (int i = 0; i < n; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = array[i];
            }
        }
        for (int i = n-1; i >=0; i--) {
            if (array[i] > min) {
                left = i;
            } else {
                min = array[i];
            }
        }

        return new int[]{left, right};
    }



    }
