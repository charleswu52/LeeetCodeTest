package chp17;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @since 2021/6/5 上午9:34
 */
public class _7 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.07. 婴儿名字
     * 难度: medium
     * <p>
     * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，
     * 例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
     * 设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，
     * 则 John 与 Johnny 也相同，即它们有传递和对称性。
     * <p>
     * 在结果列表中，选择 字典序最小 的名字作为真实名字。
     *
     * <p>
     * 示例:
     * 输入：
     * names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
     * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * <p>
     * 输出：["John(27)","Chris(36)"]
     *
     * <p>
     * 数据范围：
     * names.length <= 100000
     */

    /*
   思路：并查集
   1. 首先通过哈希表建立并查集，哈希表的键值对都是字符串，然后将一个相连的并查集合并；
   2. 利用一个哈希表进行计数，计数的时候将值都累加到根元素。
   3. 最后进行输出，输出的时候从头遍历，如果当前元素是一个根元素，那就输出。
     */

    Map<String, String> map;
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> cnt = new HashMap<>();
        map = new HashMap<>();
        // 初始化并查集元素
        for (String name : names) {
            int i = 0;
            while (name.charAt(i) != '(') {
                i++;
            }
            map.put(name.substring(0, i), name.substring(0, i));
        }
        // 1. 联通的并查集
        for (String name : synonyms) {
            String[] temp = name.split(",");
            String x = temp[0].substring(1, temp[0].length());
            String y = temp[1].substring(0, temp[1].length() - 1);

            if (!map.containsKey(x)) {
                map.put(x, x);
            }
            if (!map.containsKey(y)) {
                map.put(y, y);
            }

            // 获得两个集合的根
            String fx = find(x);
            String fy = find(y);

            // 合并两个并查集
            if (!fx.equals(fy)) {
                if (fx.compareTo(fy) > 0) {
                    map.put(fx, fy);
                } else {
                    map.put(fy, fx);
                }
            }
        }

        // 2.计数
        for (String name : names) {
            int i = 0;
            while (name.charAt(i) != '(') {
                i++;
            }
            // 将所有数值都累加到根的位置
            String root = find(name.substring(0, i));
            cnt.put(root, cnt.getOrDefault(root, 0) + Integer.parseInt(name.substring(i + 1, name.length() - 1)));
        }
        List<String> res = new ArrayList<>();

        // 3.输出答案
        for (String name : names) {
            int i = 0;
            while (name.charAt(i) != '(') {
                i++;
            }
            String root = find(name.substring(0, i));
            // 只输出根
            if (!root.equals(name.substring(0, i))) {
                continue;

            }
            res.add(root + "(" + String.valueOf(cnt.get(root)) + ")");
        }
        return res.toArray(new String[res.size()]);

    }

    /**
     * 查并查集的根
     */
    public String find(String x) {
        if (!map.get(x).equals(x)) {
            map.put(x, find(map.get(x)));
        }
        return map.get(x);
    }

    @Test
    public void test() {
        String[] names = {"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"};
        String[] synonyms = {"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"};
        System.out.println(Arrays.toString(trulyMostPopular(names, synonyms)));
    }
}

