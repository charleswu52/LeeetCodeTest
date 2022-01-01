package leetcode_everyday._2021.Jul;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/25 8:08
 */
public class _25 {
    /**
     * <p> 每日一题：2021/7/25 </p>
     * <p> 1743. 从相邻元素对还原数组 </p>
     * <p> 难度: medium </p>
     *
     * <p>
     * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
     * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
     * <p>
     * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，
     * 也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
     * <p>
     * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：adjacentPairs = [[2,1],[3,4],[3,2]]
     * 输出：[1,2,3,4]
     * 解释：数组的所有相邻元素对都在 adjacentPairs 中。
     * 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
     *
     * </p>
     * <p>
     * 输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
     * 输出：[-2,4,1,-3]
     * 解释：数组中可能存在负数。
     * 另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
     * </p>
     *
     * <p> 范围 </p>
     * <p>
     * nums.length == n
     * adjacentPairs.length == n - 1
     * adjacentPairs[i].length == 2
     * 2 <= n <= 105
     * -105 <= nums[i], ui, vi <= 105
     * 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
     *
     * </p>
     */

    /*
    思路： 哈希表
    数组的首尾两个元素只与一个数字相邻，因此他们两个相邻的元素个数只有一个
    其余元素与它们相邻的元素个数均为两个，因此可以使用哈希表
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        int[] res = new int[n + 1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adj : adjacentPairs) {
            if (!map.containsKey(adj[0])) {
                map.put(adj[0], new ArrayList<Integer>() {{
                    add(adj[1]);
                }});
            } else {
                map.get(adj[0]).add(adj[1]);
            }
            if (!map.containsKey(adj[1])) {
                map.put(adj[1], new ArrayList<Integer>() {{
                    add(adj[0]);
                }});
            } else {
                map.get(adj[1]).add(adj[0]);
            }
        }
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = map.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (count >= 2) {
                break;
            }
            Map.Entry<Integer, List<Integer>> next = iterator.next();
            if (next.getValue().size() == 1) {
                if (count == 0) {
                    res[0] = next.getKey();
                    res[1] = next.getValue().get(0);
                } else {
                    res[n] = next.getKey();
                    res[n - 1] = next.getValue().get(0);
                }
                count++;
            }
        }
        for (int i = 2; i < n; i++) {
            List<Integer> list = map.get(res[i - 1]);
            if (list.get(0) != res[i - 2]) {
                res[i] = list.get(0);
            } else {
                res[i] = list.get(1);
            }
        }
        return res;


    }

    @Test
    public void test() {
        int[][] adjacentPairs = {{2, 1}, {3, 4}, {3, 2}};
        System.out.println(Arrays.toString(restoreArray(adjacentPairs)));
    }
}
