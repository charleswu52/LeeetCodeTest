package leetcode_everyday._2022.Mar;

import org.junit.Test;

/**
 * @author WuChao
 * @create 2022/3/24 9:33
 */
public class _24_661 {
    /**
     * 每日一题：2022/3/24
     * <p>
     * 661. 图片平滑器
     * <p>
     * 难度：easy
     * <p>
     * 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
     * <p>
     * 每个单元格的 平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
     * <p>
     * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
     * <p>
     * 示例1
     * <p>
     * 输入:img = [[1,1,1],[1,0,1],[1,1,1]]
     * <p>
     * 输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
     * <p>
     * 解释:
     * <p>
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     * <p>
     * 范围
     * <p>
     * m == img.length
     * n == img[i].length
     * 1 <= m, n <= 200
     * 0 <= img[i][j] <= 255
     */

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};

    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = img[i][j];
                int count = 1;
                for (int dir[] : dirs) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        sum += img[nx][ny];
                        count++;
                    }
                }
                ans[i][j] = sum / count;

            }
        }
        return ans;

    }

    @Test
    public void test() {
        String a = "select a from sb";
        String[] split = a.split(" ");
        String b = "sb";
        int sum = a.indexOf(b);
        int idx = 0;
        if (sum == -1) {
            idx = -1;
        } else {
            for (int i = 0; i < split.length; i++) {
                if (sum == 0) {
                    idx = i;
                    break;
                }
                sum -= split[i].length();
                sum -= 1;
            }
        }

        System.out.println(idx);
    }
}
