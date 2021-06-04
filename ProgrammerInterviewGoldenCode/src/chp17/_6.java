package chp17;

/**
 * @author WuChao
 * @since 2021/6/4 上午10:51
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.06. 2出现的次数
     * 难度: hard
     * <p>
     * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
     *
     * <p>
     * 示例:
     * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     * <p>
     * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
     * <p>
     * 输入: ["A","A"]
     * <p>
     * 输出: []
     * <p>
     * 数据范围：
     * n <= 10^9
     */

/*
思路：从个位开始依次向高位遍历, 用每一位的数字与遍历过的位数所存在的 2 的次数相乘, 再判断新的一位是否为 2, 如果为 2 或者大于 2 则增加相应记录数.
 */
    public int numberOf2sInRange(int n) {
        // capacity 当前位的小一位的容量
        // base 当前位的小一位中共出现了几次数字 2
        // res 结果
        // used 当面位之前的数字, 即当前位后面的数字
        int capacity = 1, base = 0, res = 0, used = 0;
        while (n > 0) {
            // 当前位
            int factor = n % 10;
            // 上一次的结果乘以当前位的倍数
            res += factor * base;
            if (factor > 2) {
                // 大于 2, 则多出现 10 ^ n 个 2
                res += capacity;
            } else if (factor == 2) {
                // 等于 2, 2 后面是几, 就多出现几次 + 1 次 2 (从 0 到 m, 所以需要 m + 1 次)
                res += used + 1;
            }
            // 当前位用完, 添加到已使用的数字中
            used += factor * capacity;
            // 当前位用完, 更新截止至当前位为止, 从 0 ~ n 个 9 中所出现的 2 的次数
            base = base * 10 + capacity;
            // 当前位用完, 更新容量
            capacity *= 10;
            // 当前位用完, 更新 n
            n /= 10;
        }
        return res;

    }
}
