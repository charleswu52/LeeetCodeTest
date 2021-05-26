package chp10;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author WuChao
 * @since 2021/5/26 上午9:25
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 10.05. 稀疏数组搜索
     * 难度: medium
     * <p>
     * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
     *
     * 示例1:
     *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
     *  输出：-1
     *  说明: 不存在返回-1。
     *
     * 示例2:
     *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
     *  输出：4
     * <p>
     * 数据范围：
     * words的长度在[1, 1000000]之间
     */

    public int findString(String[] words, String s) {
        HashMap<String, Integer> store = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i] != "") {
                store.put(words[i], i);
            }
        }
        if (!store.containsKey(s)) {
            return -1;
        } else {
            return store.get(s);
        }

    }

    /*
    思路：有序则二分的思想
     */
    public int findString2(String[] words, String s) {
        int len = words.length;
        if (len == 0) {
            return -1;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 保存 mid 的值，用来恢复
            int temp = mid;
            while ( mid <= right && words[mid].equals("")) {
                mid++;
            }
            if (mid > right) {  // 说明右侧区间全为空串 ""
                right = temp - 1;
                continue;
            }
            if (words[mid].equals(s)) {
                return mid;
            } else if (words[mid].compareTo(s) > 0) {
                right = mid - 1;
            } else if (words[mid].compareTo(s) < 0) {
                left = mid + 1;
            }
        }
        if (left < len && words[left].equals(s)) {

            return left;
        } else {
            return -1;
        }
    }

    @Test
    public void test() {

        String[] strings = {"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        String s = "at";
        System.out.println(findString2(strings, s));
    }
}
