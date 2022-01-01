package leetcode_everyday._2021.Jan;

public class _17 {
    /**
     * 每日一题：2021/1/17
     * 1232. 缀点成线
     * 难度： easy
     * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
     * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     * <p>
     * 示例：
     * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
     * 输出：false
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        double slope;
        if (coordinates[1][0] == coordinates[0][0]) {
            slope = Double.MAX_VALUE;
        }else {
            slope = (double) (coordinates[1][1] - coordinates[0][1]) /
                    (double) (coordinates[1][0] - coordinates[0][0]);
        }
        for (int i = 2; i < len; i++) {
            if (coordinates[i][0] == coordinates[i - 1][0]){
                if (slope != Double.MAX_VALUE) {
                    return false;
                }
            }else {
                double temp = (double) (coordinates[i][1] - coordinates[i - 1][1]) /
                        (double) (coordinates[i][0] - coordinates[i - 1][0]);
                if (temp != slope) {
                    return false;
                }
            }
        }
        return true;

    }
    public void _21_1_17(){
        int[][] coordinates = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {6, 7}};
        System.out.println(checkStraightLine(coordinates));

    }
}
