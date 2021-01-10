package com.leetcodetest.Jan;

public  class _1 {
    /**
     * 每日一题：2021/1/1
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
     * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     *
     * 思路1： 暴力思考
     * 抛开花坛数较少的情况，花的种植位置就三种：在两端、中间和尾端。[00] [000] [00] => [10] [010] [01]
     * 分析每个位置是否满足这三种情况，对应的把符合条件的位置种上花；
     * 此外，这三种位置都要求花坛数都是大于2的，对于1个花坛，同时[0] 和 n=1才能符合题目要求
     *
     * 思路2：贪心
     * 从贪心角度考虑，应该在不打破种植规则的情况下种入尽可能多的花，然后判断可以种入的花的最多数量是否大于或等于 n。
     * 分析以下几种情况：
     * 假设花坛的下标 i 和下标 j 处都种植了花，其中 j−i≥2，且在下标 [i+1,j−1] 范围内没有种植花，则只有当 j−i≥4 时
     * 才可以在下标 i 和下标 j 之间种植更多的花，且可以种植花的下标范围是 [i+2,j-2]。可以种植花的位置数是 p=j−i−3，
     * 当 p 是奇数时最多可以在该范围内种植 (p+1)/2 朵花，当 p 是偶数时最多可以在该范围内种植 p/2 朵花。
     * 由于当 p 是偶数时，在整数除法的规则下 p/2 和 (p+1)/2 相等，因此无论 p 是奇数还是偶数，都是一个公式，(p+1)/2,
     * 即最多可以在该范围内种植 (j-i-2)/2 朵花。
     * 假设花坛的下标 l 处是最左边的已经种植的花，下标 r 处是最右边的已经种植的花（即对于任意 k<l 或 k>r 都有 flowerbed[k]=0），
     * 如何计算在下标 l 左边最多可以种植多少朵花以及在下标 r 右边最多可以种植多少朵花？
     * 下标 l 左边有 l 个位置，当 l<2 时无法在下标 l 左边种植花，当 l≥2 时可以在下标范围 [0,l−2] 范围内种植花，
     * 可以种植花的位置数是 l−1，最多可以种植 l/2 朵花
     * 令 m 为数组 flowerbed 的长度，下标 r 右边有 m−r−1 个位置，可以种植花的位置数是 m−r−2，最多可以种植 (m-r-1)/2 朵花。
     * 如果花坛上没有任何花朵，则有 m 个位置可以种植花，最多可以种植 (m+1)/2 朵花。
     *
     * 根据上述计算方法，计算花坛中可以种入的花的最多数量，判断是否大于或等于 n 即可。具体做法如下。
     *  维护 prev 表示上一朵已经种植的花的下标位置，初始时 prev=−1，表示尚未遇到任何已经种植的花。
     *  从左往右遍历数组 flowerbed，当遇到 flowerbed[i]=1 时根据 prev 和 i 的值计算上一个区间内可以种植花的最多数量，
     *  然后令 prev=i，继续遍历数组 flowerbed 剩下的元素。
     *  遍历数组 flowerbed 结束后，根据数组 prev 和长度 m 的值计算最后一个区间内可以种植花的最多数量。
     *  判断整个花坛内可以种入的花的最多数量是否大于或等于 n。
     */

    /**
     * 暴力算法实现
     * @param flowerbed
     * @param n
     * @return
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        int count = 0;
        int len = flowerbed.length;
        if (len == 0) {
            return false;
        }
        if (len == 1) {
            if (flowerbed[0] == 0 && n == 1) {
                return true;
            } else {
                return false;
            }
        }
        for (int i = 0; i < len; i++) {
            if (i == 0 && i + 1 < len && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                count++;
            } else if (i > 0 && i + 1 < len && flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                count++;
            } else if (i == len - 1 && i - 1 >= 0 && flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                flowerbed[i] = 1;
                count++;
            }
        }
        if (count >= n) {
            return true;
        }
        return false;
    }

    /**
     * 贪心思路的实现
     * @param flowerbed
     * @param n
     * @return
     */
    public static boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int count = 0;
        int len = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                }else {
                    count += (i - prev - 2) / 2;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (len + 1) / 2;
        } else {
            count += (len - prev - 1) / 2;
        }
        return count >= n;
    }

        /**
         * 测试函数
         */
    public static void _21_1_1(){
        int[] flowerbed = {1,0,0,0,1};
        int n = 2;
        System.out.println(canPlaceFlowers2(flowerbed, n));
    }


    /**
     * 额外的题目
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < len; j++) {
                    if (nums[j]!=0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                    }
                }
            }
        }
    }
}
