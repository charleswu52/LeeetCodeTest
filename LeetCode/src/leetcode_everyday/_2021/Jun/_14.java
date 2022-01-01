package leetcode_everyday._2021.Jun;

/**
 * @author WuChao
 * @create 2021/6/15 上午9:00
 */
public class _14 {
    /**
     * 每日一题：2021/6/14
     * 374. 猜数字大小
     * 难度: easy
     * <p>
     * 猜数字游戏的规则如下：
     *
     * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
     * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
     * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
     *
     * -1：我选出的数字比你猜的数字小 pick < num
     * 1：我选出的数字比你猜的数字大 pick > num
     * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
     * 返回我选出的数字。
     *
     * <p>
     * 示例:
     * 输入：n = 10, pick = 6
     * 输出：6
     *
     * 输入：n = 1, pick = 1
     * 输出：1
     *
     * 输入：n = 2, pick = 1
     * 输出：1
     * <p>
     * 数据范围：
     * 1 <= n <= 231 - 1
     * 1 <= pick <= n
     */

    /*
    思路：二分查找
     */
    public int guessNumber(int n) {
        int left = 1, right = n;
        int num = left + ((right - left) >> 1);
        while (guess(num) != 0) {
            if (guess(num) == 1) {
                left = num + 1;
            } else {
                right = num - 1;
            }
            num = left + ((right - left) >> 1);
        }
        return num;

    }

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is lower than the guess number
     *			      1 if num is higher than the guess number
     *               otherwise return 0
     * int guess(int num);
     * 没有具体实现，为了编译不报错写的
     */
    public int guess(int num){
        return -1;
    }
}
