package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/14 上午10:32
 */
public class _14_599 {
    /**
     * 每日一题：2022/3/15
     * <p>
     * 599. 两个列表的最小索引总和
     * <p>
     * 难度：medium
     * <p>
     * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     *
     * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
     * <p>
     * 示例1
     * <p>
     * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，
     * list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     *
     * 输出: ["Shogun"]
     *
     * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
     * <p>
     * 范围
     * <p>
     * 1 <= list1.length, list2.length <= 1000
     * 1 <= list1[i].length, list2[i].length <= 30
     * list1[i] 和 list2[i] 由空格' '和英文字母组成。
     * list1 的所有字符串都是 唯一 的。
     * list2 中的所有字符串都是 唯一 的。
     */


    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int sum = map.get(list2[i]) + i;
                if (sum < minIdx) {
                    minIdx = sum;
                    res.clear();
                    res.add(list2[i]);
                } else if (sum == minIdx) {
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);


    }
}
