package leetcode_everyday.Feb;

public class _23 {
    /**
     * 每日一题：2021/2/23
     * 1052. 爱生气的书店老板
     * 难度: medium
     * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
     * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
     * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     * <p>
     * 示例：
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 输出：16
     * 解释：
     * 书店老板在最后 3 分钟保持冷静。
     * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     * <p>
     * 数据范围：
     * 1 <= X <= customers.length == grumpy.length <= 20000
     * 0 <= customers[i] <= 1000
     * 0 <= grumpy[i] <= 1
     */



    /*
    题解：使用带【秘密技巧】的滑动窗口
    使老板在窗口大小 X 的时间内不生气。我们使用「秘密技巧」的原则是：寻找一个时间长度为 X 的窗口，能留住更多的原本因为老板生气而被赶走顾客。
    使用「秘密技巧」能得到的最终的顾客数 = 所有不生气时间内的顾客总数 + 在窗口 X 内使用「秘密技巧」挽留住的原本因为生气而被赶走顾客数。

    分为两部分求解：
        1. 所有不生气时间内的顾客总数：使用 i 遍历[0, customers.length)，累加grumpy[i]==0时的customers[i]。
        2. 在窗口 X 内因为生气而被赶走的顾客数：使用大小为 X 的滑动窗口，计算滑动窗口内的grumpy[i]==1时的customers[i]，
            得到在滑动窗口内老板生气时对应的顾客数。
     */

    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int res1 = 0;   // 所有不生气时间内的顾客总数
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0) {
                res1 += customers[i];
            }
        }
        // 滑动窗口统计在X窗口内因为生气被赶走的顾客数量
        int res2 = 0; // 因为生气被赶走的顾客数量
        int left = 0,right=0;
        int count = 0;
        while (right < len) {
            count++;
            if (count == X) {
                int m = 0;
                for (int i = left; i <= right; i++) {
                    if (grumpy[i] == 1) {
                        m += customers[i];
                    }
                }
                res2 = Math.max(res2, m);
                left++;
                count--;
            }
            right++;
        }
        return res1 + res2;

    }




        public void _21_2_23() {
        int[] customers = {3,8,8,7,1};
        int[] grumpy = {1,1,1,1,1};
        int X = 3;
        System.out.println(maxSatisfied2(customers, grumpy, X));


    }
}
