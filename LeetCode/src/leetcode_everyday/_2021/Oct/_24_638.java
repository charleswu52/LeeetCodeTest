package leetcode_everyday._2021.Oct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/10/24 9:55
 */
public class _24_638 {
    /**
     * 每日一题：2021/10/24
     * <p>
     * 638. 大礼包
     * <p>
     * 难度：medium
     * <p>
     * 在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
     * <p>
     * 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，
     * 其中 needs[i] 是需要购买第 i 件物品的数量。
     * <p>
     * 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，
     * 且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
     * <p>
     * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。
     * 任意大礼包可无限次购买。
     *
     *
     *
     * <p>
     * 示例1：
     * <p>
     * 输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
     * <p>
     * 输出：14
     * <p>
     * 解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
     * 大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
     * 大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
     * 需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
     * <p>
     * 范围
     * <p>
     * n == price.length
     * n == needs.length
     * 1 <= n <= 6
     * 0 <= price[i] <= 10
     * 0 <= needs[i] <= 10
     * 1 <= special.length <= 100
     * special[i].length == n + 1
     * 0 <= special[i][j] <= 50
     */


    /*
    思路：记忆化搜索
    详细参看题解：https://leetcode-cn.com/problems/shopping-offers/solution/da-li-bao-by-leetcode-solution-p1ww/
     */

    Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        // 首先过滤掉不需要计算的大礼包，只保留需要计算的大礼包
        List<List<Integer>> filterSpecial = new ArrayList<>();
        for (List<Integer> sp : special) {
            int totalCount = 0, totalPrice = 0;
            for (int i = 0; i < n; i++) {
                totalCount += sp.get(i);
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalCount > 0 && totalPrice > sp.get(n)) {
                filterSpecial.add(sp);
            }
        }
        return dfs(price, special, needs, filterSpecial, n);

    }

    // 记忆化搜索计算满足购物清单所需花费的最低价格
    public int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
        if (!memo.containsKey(curNeeds)) {
            int minPrice = 0;
            for (int i = 0; i < n; i++) {
                minPrice += curNeeds.get(i) * price.get(i); // 不购买大礼包的情况
            }
            for (List<Integer> curSpecial : filterSpecial) {
                int specialPrice = curSpecial.get(n);
                List<Integer> nxtNeeds = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (curSpecial.get(i) > curNeeds.get(i)) {
                        break;
                    }
                    nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                if (nxtNeeds.size() == n) {
                    minPrice = Math.min(minPrice, dfs(price, special, nxtNeeds, filterSpecial, n) + specialPrice);
                }
            }
            memo.put(curNeeds, minPrice);

        }
        return memo.get(curNeeds);
    }
}
