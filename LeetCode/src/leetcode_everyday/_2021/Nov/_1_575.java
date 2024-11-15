package leetcode_everyday._2021.Nov;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/11/1 8:53
 */
public class _1_575 {
    /**
     * 每日一题：2021/11/1
     * <p>
     * 575. 分糖果
     * <p>
     * 难度：easy
     * <p>
     * 给定一个 偶数 长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
     * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     *
     * <p>
     * 示例1：
     * <p>
     * 输入: candies = [1,1,2,2,3,3]
     *
     * 输出: 3
     *
     * 解析: 一共有三种种类的糖果，每一种都有两个。
     *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
     * <p>
     * 输入: candies = [1,1,2,3]
     *
     * 输出: 2
     *
     * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
     *
     * 范围
     * <p>
     * 数组的长度为[2, 10,000]，并且确定为偶数。
     * 数组中数字的大小在范围[-100,000, 100,000]内。
     */

    /*
    思路1：贪心
    使用 哈希表 或者 set集合 存储 糖果的种类数量
     */
    public int distributeCandies(int[] candyType) {
//        HashMap<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int type : candyType) {
//            map.put(type, map.getOrDefault(type, 0) + 1);
            set.add(type);
        }
        int len = candyType.length / 2;
//        int size = map.size();
        int size = set.size();
        return Math.min(size, len);



    }
}
