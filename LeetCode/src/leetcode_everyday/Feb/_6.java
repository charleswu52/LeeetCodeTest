package leetcode_everyday.Feb;

import java.util.Arrays;

public class _6 {
    /**
     * 每日一题：2021/2/6
     * 1423. 可获得的最大点数
     * 难度: medium
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     * <p>
     * 示例：
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     * <p>
     * 数据范围：
     * 1 <= cardPoints.length <= 10^5
     * 1 <= cardPoints[i] <= 10^4
     * 1 <= k <= cardPoints.length
     */


    /*
    题目解析：
    对于卡牌，只能从头或尾取一卡牌，正好要拿好k张卡牌，因此剩下 n-k 张卡牌，是连在一起的，而且值是最小的
    所以从这个角度看，剩余的连续卡牌是滑动窗口，所有做的就是求这个滑动窗口的最小值
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    /*
    思路：滑动窗口
    记数组 cardPoints 的长度为 n，由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n−k 张卡牌。
    我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
    算法
    由于剩余卡牌是连续的，使用一个固定长度为 n-k 的滑动窗口对数组 cardPoints 进行遍历，求出滑动窗口最小值，然后用所有卡牌的点数之和减去该最小值，即得到了拿走卡牌点数之和的最大值。
     */
    public int maxScore2(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int size = len - k;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = size; i < len; i++) {
            sum += cardPoints[i] - cardPoints[i - size];
            minSum = Math.min(sum, minSum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }



    public void _21_2_6() {
        int[] cardPoints = {100, 40, 17, 9, 73, 75};
        int k = 3;
        System.out.println(maxScore2(cardPoints, k));
    }
}
