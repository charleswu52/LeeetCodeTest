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

        File dir = new File(logs);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
            File myPath = new File(outPath + files[i].getName());
            if ( !myPath.exists()){//若此目录不存在，则创建之
                myPath.mkdir();

            }

        }
    }

}
