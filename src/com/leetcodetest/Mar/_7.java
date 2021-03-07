package com.leetcodetest.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/3/7 8:34
 */
public class _7 {
    /**
     * 每日一题：2021/3/7
     * 131. 分割回文串
     * 难度: medium
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回 s 所有可能的分割方案。
     * <p>
     * 示例:
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     *
     * <p>
     * 数据范围：
     *
     */

    /*
    题解
    题意：把输入字符串分割成回文子串的所有可能的结果。
    思路：回溯法
    看到题目要求「所有可能的结果」，而不是「结果的个数」，一般情况下，我们就知道需要暴力搜索所有的可行解了，可以用「回溯法」。
    回溯法的整体思路是：搜索每一条路，每次回溯是对具体的一条路径而言的。对当前搜索路径下的的未探索区域进行搜索，则可能有两种情况：
        当前未搜索区域满足结束条件，则保存当前路径并退出当前搜索；
        当前未搜索区域需要继续搜索，则遍历当前所有可能的选择：如果该选择符合要求，则把当前选择加入当前的搜索路径中，并继续搜索新的未探索区域。
    上面说的未搜索区域是指搜索某条路径时的未搜索区域，并不是全局的未搜索区域
     */
    List<List<String>> res;
    List<String> path;
    int len;
    char[] arr;

    public List<List<String>> partition(String s) {
        //初始化
        this.res = new ArrayList<>();
        this.path = new ArrayList<>();
        this.len = s.length();
        this.arr = s.toCharArray();
        backtrack(0);

        return res;


    }


    /**
     * 回溯方法
     * @param index 起始位置
     */
    public void backtrack(int index) {

        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < len; i++) {
            if (isPalindrome(index, i)) {
                path.add(new String(arr, index, i + 1 - index));
                backtrack(i + 1);
                path.remove(path.size() - 1);
            }
        }
    }


    /**
     * 判断一个字符串是否是回文串
     * @param left
     * @param right
     * @return
     */
    public boolean isPalindrome(int left, int right){
        while(left < right){
            if(arr[left] != arr[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public void _21_3_7() {
        String s = "aab";
        System.out.println(partition(s));
    }
}
