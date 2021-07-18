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

        File dir = new File(compressedPath);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].length());

        }
    }

}
