package dataprocess;

import java.io.File;

/**
 * @author WuChao
 * @create 2021/6/23 18:29
 */
public class ScorePaper {
    /**
     * C语言助课
     *
     */
    public static void main(String[] args) {
        File dir = new File("E:\\Tim\\Save\\C语言平时测验2-冶金1904-08（主观题） - 考试数据包2021.06.23 173309");
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        System.out.println(files.length);
    }
}
