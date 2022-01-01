package leetcode_everyday._2021.Mar;

import java.util.Arrays;

/**
 * @author WuChao
 * @since 2021/3/3 上午8:59
 */
public class _3 {
    /**
     * 每日一题：2021/3/3
     * 338. 比特位计数
     * 难度: medium
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * <p>
     * 示例：
     * 输入: 2
     * 输出: [0,1,1]
     * <p>
     *
     * <p>
     * 数据范围：
     */

    /*
    暴力做法很直接
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 0; i <= num; i++) {
            String temp = Integer.toBinaryString(i);
            int count = 0;
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '1') {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }



    public void _21_3_3(){
        System.out.println(Arrays.toString(countBits(5)));
    }
}
