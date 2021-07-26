package leetcode_hot100.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/26 10:40
 */
public class _406 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 406. 根据身高重建队列
     * 难度：medium
     * <p>
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
     * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     *
     * 请你重新构造并返回输入数组 people 所表示的队列。
     * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     *
     * <p>
     * 1 <= people.length <= 2000
     * 0 <= hi <= 106
     * 0 <= ki < people.length
     * 题目数据确保队列可以被重建
     *
     */


    /*
    思路：先排序
    先对输入数组排序，h升序，k降序;
    从头循环遍历 当前这个人就是剩下未安排的人中最矮的人，他的k值就代表他在剩余空位的索引值 如果有多个人高度相同，要按照k值从大到小领取索引值

    示例：
    [ 0, 1, 2, 3, 4, 5 ] [ 4, 4 ] 4
    [ 0, 1, 2, 3, 5 ]    [ 5, 2 ] 2
    [ 0, 1, 3, 5 ]       [ 5, 0 ] 0
    [ 1, 3, 5 ]          [ 6, 1 ] 3
    [ 1, 5 ]             [ 7, 1 ] 5
    [ 1 ]                [ 7, 0 ] 1

    [ [ 5, 0 ], [ 7, 0 ], [ 5, 2 ], [ 6, 1 ], [ 4, 4 ], [ 7, 1 ] ]
     */
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        int[][] res = new int[len][2];
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indexes.add(i);
        }
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        for (int[] arr : people) {
            int index = indexes.get(arr[1]); // 领取索引值
            indexes.remove(arr[1]);
            res[index] = arr;
        }
        return res;

    }
}
