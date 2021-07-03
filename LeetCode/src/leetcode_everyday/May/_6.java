package leetcode_everyday.May;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/5/6 上午8:04
 */
public class _6 {
    /**
     * 每日一题：2021/5/6
     * 1720. 解码异或后的数组
     * 难度: easy
     * 未知 整数数组 arr 由 n 个非负整数组成。
     * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。
     * 例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     *
     *
     * <p>
     * 示例：
     * 输入：encoded = [1,2,3], first = 1
     * 输出：[1,0,2,1]
     * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
     *
     *
     * <p>
     * 数据范围：
     * 2 <= n <= 10^4
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 105
     * 0 <= first <= 105
     */

    public int[] decode(int[] encoded, int first) {
        int size = encoded.length;
        int[] res = new int[size + 1];
        res[0] = first;
        for (int i = 0, j = 1; i < size; i++, j++) {
            res[j] = encoded[i] ^ res[i];
        }
        return res;
    }

    @Test
    public void test() {
        int[] encode = {6,2,7,3};
        int first = 4;
        System.out.println(Arrays.toString(decode(encode, first)));

    }
}
