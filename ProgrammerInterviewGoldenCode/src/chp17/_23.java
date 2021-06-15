package chp17;

/**
 * @author WuChao
 * @create 2021/6/15 上午11:29
 */
public class _23 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.23. 最大黑方阵
     * 难度: medium
     * <p>
     * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
     * <p>
     * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
     * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
     * <p>
     * 示例:
     * 输入:
     * [
     * [1,0,1],
     * [0,0,1],
     * [0,0,1]
     * ]
     * 输出: [1,0,2]
     * 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
     * <p>
     * 输入:
     * [
     * [0,1,1],
     * [1,0,1],
     * [1,1,0]
     * ]
     * 输出: [0,0,1]
     *
     * <p>
     * 数据范围：
     * matrix.length == matrix[0].length <= 200
     */

    /*
    思路：动态规划 + 两个辅助数组
     */
    public int[] findSquare(int[][] matrix) {
        int R = matrix.length, C = matrix[0].length;
        //记录当前点[r,c]到右边，能探到的黑色点的距离
        int[][] maxRow = new int[R][C];
        //记录当前点[r,c]到下边，能探到的黑色点的距离
        int[][] maxCol = new int[R][C];

        // 从 右下角 的点开始遍历
        for (int r = R - 1; r >= 0; r--) {
            for (int c = C - 1; c >= 0; c--) {
                if (matrix[r][c] == 0) { //本身是黑色点，开始计算,只有本身一个黑色点的话，边长是0
                    maxRow[r][c] = 1;
                    if (c < C - 1) {
                        maxRow[r][c] += maxRow[r][c + 1];
                    }
                    maxCol[r][c] = 1;
                    if (r < R - 1) {
                        maxCol[r][c] += maxCol[r + 1][c];
                    }
                }
            }
        }
        int[] res = new int[3];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 0) {
                    //到下面和到右边所能探到的最大距离
                    int upper = Math.min(maxRow[r][c], maxCol[r][c]);
                    //小于当前结果集的边长的，没有意义
                    int bound = res[2];
                    for (int size = upper; size > bound; size--) {
                        if (maxRow[r + size - 1][c] >= size && maxCol[r][c + size - 1] >= size) {
                            res[0] = r;
                            res[1] = c;
                            res[2] = size;
                            break;//最大边结束了，break掉
                        }
                    }

                }
            }
        }
        // 评判一下
        int bountd = res[2];
        if (res[2] == 0) {
            return new int[]{};
        }
        return res;
    }
}
