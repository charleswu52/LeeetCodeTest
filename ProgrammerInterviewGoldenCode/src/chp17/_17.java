package chp17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/6/9 上午10:12
 */
public class _17 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.17. 多次搜索
     * 难度: medium
     * <p>
     *  给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
     *  输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
     * <p>
     * 示例:
     * 输入：
     * big = "mississippi"
     * smalls = ["is","ppi","hi","sis","i","ssippi"]
     * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
     * <p>
     * 数据范围：
     * 0 <= len(big) <= 1000
     * 0 <= len(smalls[i]) <= 1000
     * smalls的总字符数不会超过 100000。
     * 你可以认为smalls中没有重复字符串。
     * 所有出现的字符均为英文小写字母。
     */

    public int[][] multiSearch(String big, String[] smalls) {
        if ("".equals(big) ||(smalls.length == 1 && "".equals(smalls[0]))) {
            int[][] ans = new int[smalls.length][];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = new int[]{};
            }
            return ans;
        }

        List<List<Integer>> res = new ArrayList<>();
        for (String small : smalls) {
            boolean contains = big.contains(small);
            List<Integer> integers = new ArrayList<>();
            if (contains) {
                String[] split = big.split(small);
                int idx = 0;
                if (big.startsWith(small)) {
                    integers.add(0);
                    idx += small.length();
                }
                for (String t : split) {
                    idx += t.length();
                    if (idx < big.length()) {
                        integers.add(idx);
                    }
                    idx += small.length();
                }
                res.add(integers);
            } else {
                res.add(integers);
            }

        }
        int[][] ans = new int[res.size()][];
        int i = 0;
        for (List<Integer> list : res) {
            ans[i++] = list.stream().mapToInt(Integer::valueOf).toArray();
        }
        return ans;

    }

    @Test
    public void test() {
        String big = "mississippi";
        String[] smalls = {"is","ppi","hi","sis","i","ssippi"};
        System.out.println(Arrays.deepToString(multiSearch(big, smalls)));
    }
}
