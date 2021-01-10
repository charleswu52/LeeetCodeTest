package com.leetcodetest.Jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _5 {
    /**
     * 每日一题：2021/1/5
     *
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     */

    public static  List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = s.length();
        if (len < 2) {
            return res;
        }
        // 也是双指针的思路写法一般
        for (int i = 0; i < len - 1;) {
            List<Integer> temp = new ArrayList<Integer>();
            int j;
            for ( j = i + 1; j < len; j++) {
                if (s.charAt(j) != s.charAt(i)) {
                    if (j - i > 2) {
                        temp.add(i);
                        temp.add(j-1);
                        res.add(temp);
                    }
                    i = j;
                    break;
                }
            }
            if (j == len) {
                if (j-i>2){
                    temp.clear();
                    temp.add(i);
                    temp.add(j-1);
                    res.add(temp);
                }
                break;
            }
        }
        return res;
    }
    // 双指针的更简单的写法
    public static  List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = s.length();
        int num = 1;
        for (int i = 0; i < len; i++) {
            if (i == len - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    res.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            }else {
                num++;
            }
        }
        return res;
    }

    public static void _21_1_5() {
        String s = "abbxxxxzyy";
        List<List<Integer>> res = largeGroupPositions2(s);
        for (int i = 0; i < res.size();i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

}
