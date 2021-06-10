package chp17;

/**
 * @author WuChao
 * @create 2021/6/10 上午9:42
 */
public class _19 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.19. 消失的两个数字
     * 难度: hard
     * <p>
     * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
     * <p>
     * 以任意顺序返回这两个数字均可。
     * <p>
     * 示例:
     * 输入: [1]
     * 输出: [2,3]
     * <p>
     * 输入:
     * <p>
     * 数据范围：
     * nums.length <= 30000
     */

    /*
    题目解析：本题目的难点在于 规定了时间复杂度 O(n) 以及空间复杂度 O(1)
   与 17_4 题目类似 可结合两个题目一起看

   思路：分组异或
   对数组进行分组，一组包含缺失数字1，一组包含缺失数字2，这样题目要求就转换为前面那道题了

     */
    public int[] missingTwo(int[] nums) {
        int N = nums.length + 2;
        //1.用temp记录所缺两个数字的异或
        int temp = 0;
        for (int num : nums) temp ^= num;
        for (int i = 1; i <= N; i++) temp ^= i;
        //2.用j记录temp的32个位中其中一个值为1的位（两个数字的异或中某位为1，说明这两个数字的这个位的值不同）
        int j;
        for (j = 0; j < 32; j++) {
            if (((temp >> j) & 1) == 1) break;
        }

        //3.用num1，num2记录两个缺失的数，依据记录的j位进行分组异或，j位为1的数跟num1异或，j位为2的数跟num2异或
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if (((num >> j) & 1) == 1) num1 ^= num;
            else num2 ^= num;
        }
        for (int i = 1; i <= N; i++) {
            if (((i >> j) & 1) == 1) num1 ^= i;
            else num2 ^= i;
        }

        //4.返回结果
        return new int[]{num1, num2};
    }
}
