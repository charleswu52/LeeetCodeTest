package chp8;

/**
 * @author WuChao
 * @since 2021/5/20 上午9:24
 */
public class _10 {
    /**
     * 程序员面试金典(version 6) - 面试题 08.10. 颜色填充
     * 难度: easy
     * <p>
     * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
     * <p>
     * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
     * <p>
     * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
     * <p>
     * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
     *
     *
     * <p>
     * 示例:
     * 输入：
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出：[[2,2,2],[2,2,0],[2,0,1]]
     * 解释:
     * 初始坐标点位于图像的正中间，坐标 (sr,sc)=(1,1) 。
     * 初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成 2 。
     * 注意，右下角的像素没有更改为 2 ，因为它不属于初始坐标点的周围区域。
     * <p>
     * 数据范围：
     * image 和 image[0] 的长度均在范围 [1, 50] 内。
     * 初始坐标点 (sr,sc) 满足 0 <= sr < image.length 和 0 <= sc < image[0].length 。
     * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535] 内。
     */

    /*
    典型的深度遍历 DFS
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;

    }

    public void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // 边界条件 & 填充条件
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != oldColor || image[r][c] == newColor) {
            return;
        }
        image[r][c] = newColor;
        dfs(image, r - 1, c, oldColor, newColor); //向上填充
        dfs(image, r + 1, c, oldColor, newColor); //向下填充
        dfs(image, r, c - 1, oldColor, newColor); //向左填充
        dfs(image, r, c + 1, oldColor, newColor); //向右填充
    }
}
