package leetcode_everyday._2021.Nov;

import java.util.Arrays;
import java.util.Random;

/**
 * @author WuChao
 * @create 2021/11/22 9:09
 */
public class _22_384 {
    /**
     * 每日一题：2021/11/22
     * <p>
     * 384. 打乱数组
     * <p>
     * 难度：medium
     * <p>
     * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
     * <p>
     * 实现 Solution class:
     * <p>
     * Solution(int[] nums) 使用整数数组 nums 初始化对象
     * int[] reset() 重设数组到它的初始状态并返回
     * int[] shuffle() 返回数组随机打乱后的结果
     *
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入
     * ["Solution", "shuffle", "reset", "shuffle"]
     * [[[1, 2, 3]], [], [], []]
     * 输出
     * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
     * <p>
     * 解释
     * <p>
     * Solution solution = new Solution([1, 2, 3]);
     * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
     * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
     * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
     * <p>
     * 范围
     * <p>
     * 1 <= nums.length <= 200
     * -106 <= nums[i] <= 106
     * nums 中的所有元素都是 唯一的
     * 最多可以调用 5 * 10^4 次 reset 和 shuffle
     */


    /*
    思路：Fisher–Yates 洗牌算法
    等概率选择每个位置应该填哪个数。
    具体来说，我们先在0 ~ n-1中随机选一个坐标，将它作为第一个，和第一个交换位置； （每个数被选到的概率是 1/n)
    剩下的n-1个数里，继续随机一个1 ~ n-1的坐标，将它作为第二个，和第二个交换位置；(每个数被选到的概率为第一次没被选到且第二次被选到
        n-1/n * 1/n-1  = 1/n
    以此类推。
    每个数填到每个位置是等概率的，都是 1/n
     */
    class Solution {

        private int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;

        }

        public int[] reset() {
            return this.nums;
        }

        public int[] shuffle() {
            int[] temp = Arrays.copyOf(this.nums, this.nums.length);
            int n = temp.length;
            for (int i = n - 1; i >= 0; i--) {
                Random random = new Random();
                int rand = random.nextInt(i+1); // 获得[0,i+1) 区间的任意整数
                int t = temp[i];
                temp[i] = temp[rand];
                temp[rand] = t;
            }
            return temp;
        }
    }
}
