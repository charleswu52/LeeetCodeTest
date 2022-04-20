package leetcode_everyday._2022.Apr;

/**
 * @author WuChao
 * @create 2022/4/20 8:50
 */
public class _20_388 {
    /**
     * 每日一题：2022/4/20
     * <p>
     * 388. 文件的最长绝对路径
     * <p>
     * 难度：medium
     * <p>
     * 在文本格式中，如下所示(⟶表示制表符)：
     * <p>
     * dir
     * ⟶ subdir1
     * ⟶ ⟶ file1.ext
     * ⟶ ⟶ subsubdir1
     * ⟶ subdir2
     * ⟶ ⟶ subsubdir2
     * ⟶ ⟶ ⟶ file2.ext
     * 如果是代码表示，上面的文件系统可以写为
     * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 。
     * '\n' 和 '\t' 分别是换行符和制表符。
     * <p>
     * 文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 '/' 连接。
     * 上面例子中，指向 file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。
     * 每个目录名由字母、数字和/或空格组成，每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。
     * <p>
     * 给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。
     * <p>
     * 示例
     * <p>
     * 输入：input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
     * <p>
     * 输出：20
     * <p>
     * 解释：只有一个文件，绝对路径为 "dir/subdir2/file.ext" ，路径长度 20
     * <p>
     * 范围
     * <p>
     * 1 <= input.length <= 104
     * input 可能包含小写或大写的英文字母，一个换行符 '\n'，一个制表符 '\t'，一个点 '.'，一个空格 ' '，和数字。
     */

    /*
    思路： 动态规划
    步骤1：利用String的split方法分割元素。
    步骤2：在第一步所得的元素基础上从左向右遍历，用一个pahLens数组记录所遍历的元素的路径长度。
    其中pahLens[ i ]表示level为i的元素的路径长度（存在多个同level元素时，取最后面的元素）。这就是动态规划的思想。时间O（n）。
     */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        String[] strings = input.split("\n");
        int[] pathLens = new int[strings.length + 1];
        pathLens[0] = -1;
        int ans = 0;
        for (String s : strings) {
            int level = s.lastIndexOf('\t') + 1 + 1; // 层次计算
            int nameLen = s.length() - (level - 1); // 计算名字长度
            //word的父文件夹必定目前是level-1层次的最后一个，因此pathLens[level-1]就是父文件夹路径长度
            //这个word必然是目前本层次的最后一个，因此需要刷新pathLens[level],+1是因为要加一个'\'
            pathLens[level] = pathLens[level - 1] + 1 + nameLen;
            //如果是文件，还需要用路径长度刷新ans
            if (s.contains(".")) ans = Math.max(ans, pathLens[level]);
        }
        return ans;


    }
}
