package leetcodetest.Jun;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/26 8:56
 */
public class _26 {
    /**
     * 每日一题：2021/6/26
     * 773. 滑动谜题
     * 难度: hard
     * <p>
     * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
     * <p>
     * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
     * <p>
     * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
     * <p>
     * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
     *
     * <p>
     * 示例
     * 输入：board = [[4,1,2],[5,0,3]]
     * 输出：5
     * 解释：
     * 最少完成谜板的最少移动次数是 5 ，
     * 一种移动路径:
     * 尚未移动: [[4,1,2],[5,0,3]]
     * 移动 1 次: [[4,1,2],[0,5,3]]
     * 移动 2 次: [[0,1,2],[4,5,3]]
     * 移动 3 次: [[1,0,2],[4,5,3]]
     * 移动 4 次: [[1,2,0],[4,5,3]]
     * 移动 5 次: [[1,2,3],[4,5,0]]
     * <p>
     * 数据范围：
     * board 是一个如上所述的 2 x 3 的数组.
     * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
     */

    /*
    题目解析：
    与昨天题目类似属于同一种，也是采用BFS搜索的方法来做

    为了方便记录和搜索，要将状态保存在一个hash表中，但是hashMap 的存数组的话是没法取到其value的，因此可以将其转化为一个String进行存储。
    同时假设0存在每个位置上，那它可以进行交换的位置也都存起来，在搜索的时候进行
     */
    // 为了方便进行搜索，将二维数组每个位置可以交换的位置进行记录
    public static int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        // 将初始状态转为 String 进行存储
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                stringBuilder.append(num);
            }
        }
        String initialState = stringBuilder.toString();
        if ("123450".equals(initialState)) {
            return 0;
        }
        int res = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String state = queue.poll();
                for (String cur : changeStae(state)) {
                    if (!visited.contains(cur)) {
                        if ("123450".equals(cur)) {
                            return res;
                        }
                        queue.offer(cur);
                        visited.add(cur);
                    }
                }
            }
        }
        return -1;


    }

    public static List<String> changeStae(String state) {
        List<String> res = new ArrayList<>();
        char[] chars = state.toCharArray();
        int idx = state.indexOf('0');
        for (int changeIdx : neighbors[idx]) {
            swap(chars, idx, changeIdx);
            res.add(new String(chars));
            swap(chars, idx, changeIdx);
        }
        return res;
    }

    public static void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }


    @Test
    public void test() {
        Map<Integer[], Integer> map = new HashMap<>();
        Integer[] s = {1, 2};
        Integer[] t = {1, 2};
        System.out.println(s);
        System.out.println(t);
        map.put(s, 1);
        map.put(t, 1);
        System.out.println(map.size());

        Integer integer = map.get(new Integer[]{1, 2});
        System.out.println(integer);
//        if ()) {
//            System.out.println("exist");
//
//        } else {
//            System.out.println("not exist");
//        }


    }
}
