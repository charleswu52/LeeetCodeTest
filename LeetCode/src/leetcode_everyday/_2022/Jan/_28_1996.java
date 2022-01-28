package leetcode_everyday._2022.Jan;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.util.*;

/**
 * @author WuChao
 * @create 2022/1/28 10:14
 */
public class _28_1996 {
    /**
     * 每日一题：2022/1/28
     * <p>
     * 1996. 游戏中弱角色的数量
     * <p>
     * 难度：medium
     * <p>
     * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，
     * 其中 properties[i] = [attack_i, defense_i] 表示游戏中第 i 个角色的属性。
     * <p>
     * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。
     * 更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attack_j > attack_i 且 defense_j > defense_i 。
     * <p>
     * 返回 弱角色 的数量。
     * <p>
     * 输入：properties = [[1,5],[10,4],[4,3]]
     * <p>
     * 输出：1
     * <p>
     * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
     *
     * <p>
     * 范围
     * <p>
     * 2 <= properties.length <= 10^5
     * properties[i].length == 2
     * 1 <= attack_i, defense_i <= 10^5
     */


    /*
    思路：排序
     */
    public int numberOfWeakCharacters(int[][] properties) {
        // 按照 攻击力降序排序 防御力升序排序
        // 遍历数组 判断防御力是否比之前的最大值小，如果是则该角色就是弱角色
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        int res = 0;
        int max = -1;
        int n = properties.length;
        for (int[] property : properties) {
            if (max > property[1]) {
                res++;
            }
            max = Math.max(max, property[1]);
        }
        return res;
    }
}
