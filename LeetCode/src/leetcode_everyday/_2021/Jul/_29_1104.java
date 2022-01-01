package leetcode_everyday._2021.Jul;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/29 8:03
 */
public class _29_1104 {
    /**
     * 每日一题：2021/7/29
     * 1104. 二叉树寻路
     * 难度: medium
     * <p>
     * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     *
     * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     *
     * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     *
     *
     *
     * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     *
     *
     *
     * 示例 1：
     *
     * 输入：label = 14
     * 输出：[1,3,4,14]
     * 示例 2：
     *
     * 输入：label = 26
     * 输出：[1,2,6,10,26]
     *
     *
     * 提示：
     *
     * 1 <= label <= 10^6
     *
     * <p>
     * 示例：
     * 输入：nums = [0,1,0,1,0,1,99]
     * 输出：99
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     */

    /*
    思路：数学
    思考正常二叉树的标号，然后对偶数行的标号需要翻转

     */

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();

        // 根据标号求出其在第几层
        int depth = (int) (Math.log(label) / Math.log(2)) + 1;

        // 从下层到上层求路径
        while (depth > 1) {
            res.add(0, label);
            label = label / 2;
            depth--;
            // 下一层最右边和最左边的值（二叉树性质）
            int right = (int) (Math.pow(2, depth) - 1);
            int left = (int) (Math.pow(2, depth - 1));
            // 每上一层，翻转一次
            label = right - (label - left);

        }
        res.add(0, 1);// 最后根节点

        return res;


    }






}
