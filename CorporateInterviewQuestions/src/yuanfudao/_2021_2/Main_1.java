package yuanfudao._2021_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/6/30 10:52
 */
public class Main_1 {
    /**
     * [编程题]完全二叉树的边界
     * 小猿给定了一棵完全二叉树，树中结点都是正整数，请问该完全二叉树的边界结点从根结点开始以逆时针的顺序排序后形成的序列是什么？
     * 边界结点定义为每层最左边的结点、叶子结点和每层最右边的结点。（同一个结点只能计入一次）
     * <p>
     * 输入描述:
     * 第一行输入一个正整数 N，表示为完全二叉树的结点个数（1 ≤ N ≤ 106）。
     * 第二行输入 N 个正整数，表示为该完全二叉树的层序遍历序列。
     * <p>
     * 输出描述:
     * 输出完全二叉树的边界结点从根结点开始以逆时针的顺序排序后形成的序列，以空格分隔。
     * <p>
     * 输入例子1:
     * 5
     * 1 2 3 4 5
     * <p>
     * 输出例子1:
     * 1 2 4 5 3
     * <p>
     * 例子说明1:
     * 二叉树如下：
     * 1
     * /   \
     * 2      3
     * /    \
     * 4       5
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine();
        String[] fields = str.split(" ");
        int layer;
        int len = fields.length + 1;
        double ant = Math.log10(len) / Math.log10(2);
        if (ant == (int) ant) {
            layer = (int) ant;
        } else {
            layer = (int) ant + 1;
        }
        List<String> strings = new ArrayList<>();
        for (int i = 0, l = 0; l < layer; i += Math.pow(2, l), l++) {
            strings.add(fields[i]);
        }
        for (int i = layer - 1; i > -1; i--) {
            for (int j = (int) (Math.pow(2, i)); j < ((int) Math.pow(2, i + 1)-1) && j < n; j++) {
                strings.add(fields[j]);
            }
        }
        for (int i = 0; i < strings.size(); i++) {
            if (i != strings.size() - 1) {
                System.out.print(strings.get(i) + " ");
            } else {
                System.out.println(strings.get(i));
            }
        }
    }
}
