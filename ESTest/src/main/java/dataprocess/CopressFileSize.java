package dataprocess;

import java.io.File;

/**
 * @author WuChao
 * @create 2021/7/18 16:52
 */
public class CopressFileSize {

    public static void main(String[] args) {
        String sourcePath = "E:\\levelFields_oneFile";
        String compressedPath = "E:\\levelFields_oneFile\\special";

        String logs = "E:\\研究生学习\\ES测试\\ES实验\\logs";

        String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16LevelCompress\\";

        String listFiles = "/home/charles/IdeaProject/lucene-main/lucene/demo/resources/input/datasource15";
        String listFiles2 = "/home/charles/IdeaProject/lucene-main/lucene/demo/resources/output/indexPath15";

        File dir = new File(listFiles2);
        File[] files = dir.listFiles();
        int sum = 0;
        for (int i = 0; i < files.length; i++) {
            sum += files[i].length();

        }
        System.out.println(sum);
    }

}
