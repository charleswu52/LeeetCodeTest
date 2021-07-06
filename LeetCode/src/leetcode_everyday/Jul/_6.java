package leetcode_everyday.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/6 8:32
 */
public class _6 {
    /**
     * 每日一题：2021/7/6
     * 1418. 点菜展示表
     * 难度: medium
     * <p>
     * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
     * 其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
     *
     * 请你返回该餐厅的 点菜展示表 。
     * 在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
     * 接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
     *
     * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
     *
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：orders = [["David","3","Ceviche"],
     *                ["Corina","10","Beef Burrito"],
     *                ["David","3","Fried Chicken"],
     *                ["Carla","5","Water"],
     *                ["Carla","5","Ceviche"],
     *                ["Rous","3","Ceviche"]]
     * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],
     *       ["3","0","2","1","0"],
     *       ["5","0","1","0","1"],
     *       ["10","1","0","0","0"]]
     * 解释：
     * 点菜展示表如下所示：
     * Table,Beef Burrito,Ceviche,Fried Chicken,Water
     * 3    ,0           ,2      ,1            ,0
     * 5    ,0           ,1      ,0            ,1
     * 10   ,1           ,0      ,0            ,0
     * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
     * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
     * 餐桌 10：Corina 点了 "Beef Burrito"
     *
     * <p>
     * 限制：
     * 1 <= orders.length <= 5 * 10^4
     * orders[i].length == 3
     * 1 <= customerNamei.length, foodItemi.length <= 20
     * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
     * tableNumberi 是 1 到 500 范围内的整数。
     */

    /*
    思路： 哈希表
    使用TreeMap 就完事了
     */
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<Integer, TreeMap<String, Integer>> orderShow = new TreeMap<>(Integer::compareTo);
        TreeSet<String> foodset = new TreeSet<>(String::compareTo);
        for (List<String> order : orders) {
            int tableNo = Integer.parseInt(order.get(1));
            String food = order.get(2);
            foodset.add(food);
            if (!orderShow.containsKey(tableNo)) {
                orderShow.put(tableNo, new TreeMap<String, Integer>(String::compareTo) {
                    {
                        put(food, 1);
                    }
                });
            } else {
                TreeMap<String, Integer> treeMap = orderShow.get(tableNo);
                treeMap.put(food, treeMap.getOrDefault(food, 0) + 1);
                orderShow.put(tableNo, treeMap);
            }
        }
        List<String> firstLine = new ArrayList<>();
        firstLine.add("Table");
        firstLine.addAll(foodset);
        res.add(firstLine);
        for (Map.Entry<Integer, TreeMap<String, Integer>> entry : orderShow.entrySet()) {
            List<String> element = new ArrayList<>();
            element.add(entry.getKey().toString());
            TreeMap<String, Integer> value = entry.getValue();
            for (String food : foodset) {
                if (value.containsKey(food)) {
                    element.add(value.get(food).toString());
                } else {
                    element.add("0");
                }
            }
            res.add(element);
        }
        return res;
    }
}
