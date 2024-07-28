package leetcode_everyday._2024.Jul;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charles
 * @create 2024/7/28
 * @description 每日一题
 */
public class _25_2844 {
    /**
     * 每日一题：2024/7/25
     * 2844. 生成特殊数字的最少操作
     * https://leetcode.cn/problems/minimum-operations-to-make-a-special-number/description/?envType=daily-question&envId=2024-07-24
     */

    public int minimumOperations(String num) {
        int min = num.length();
        if (num.contains("0")) {
            // 变成0 需要删除的位数
            min = Math.min(min, num.length() - 1);
        }
        // 变成25
        if (num.contains("2") && num.contains("5")) {
            min = Math.min(min, getDelCnt(num, '2', '5'));
        }
        // 变成50
        if (num.contains("5") && num.contains("0")) {
            min = Math.min(min, getDelCnt(num, '5', '0'));
        }
        // 变成75
        if (num.contains("7") && num.contains("5")) {
            min = Math.min(min, getDelCnt(num, '7', '5'));
        }
        // 变成00
        if (num.contains("0") && num.contains("0")) {
            min = Math.min(min, getDelCnt(num, '0', '0'));
        }

        return min;
    }

    public int getDelCnt(String num, char first, char second) {
        int del = 0;
        List<Integer> delIndex = new ArrayList<>();
        int fiveInx = -1;
        boolean findLas = false, findFirst = false;
        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) == second) {
                fiveInx = i;
                findLas = true;
                break;
            }
            if (num.charAt(i) != second) {
                delIndex.add(i);
                del++;
            }
        }
        for (int i = num.length() - 1; i >= 0; i--) {
            if (delIndex.contains(i) || i == fiveInx) {
                continue;
            }
            if (num.charAt(i) == first) {
                findFirst = true;
                break;
            } else {
                del++;
            }
        }
        if (findLas && findFirst) {
            return del;
        } else {
            return num.length();
        }
    }

    @Test
    public void  test() {
        System.out.println(minimumOperations("52"));

    }

}