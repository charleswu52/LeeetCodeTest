package leetcode_everyday._2022.Apr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/4/1 10:48
 */
public class _1_954 {
    /**
     * 每日一题：2022/4/1
     * <p>
     * 954. 二倍数对数组
     * <p>
     * 难度：medium
     * <p>
     * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足
     * “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
     * <p>
     * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。
     * 如果不存在美好子字符串，请你返回一个空字符串。
     * <p>
     * 示例
     * <p>
     * 输入：arr = [4,-2,2,-4]
     * <p>
     * 输出：true
     * <p>
     * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
     * <p>
     * 范围
     * <p>
     * 0 <= arr.length <= 3 * 10^4
     * arr.length 是偶数
     * -10^5 <= arr[i] <= 10^5
     */

    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if (map.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }
        List<Integer> vals = new ArrayList<>();
        for (int x : map.keySet()) {
            vals.add(x);
        }
        Collections.sort(vals, (o1, o2) -> Math.abs(o1) - Math.abs(o2));
        for (int x : vals) {
            if (map.getOrDefault(2 * x, 0) < map.get(x)) {
                return false;
            }
            map.put(2 * x, map.getOrDefault(2 * x, 0) - map.get(x));
        }
        return true;
    }
}
