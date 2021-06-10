package dataprocess;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/9 下午3:03
 */
public class LevelCutFields2 {

    public static void main(String[] args) {
        String path = "/home/charles/WorkSpace/ES实验/logs_trans/readJson/";
        String outPath = "/home/charles/WorkSpace/ES实验/logs_trans/levelFields/";

        File file = new File(outPath);
        File[] listFiles = file.listFiles();
        Set<String> fileds = new HashSet<>();
        for (int i = 0; i < listFiles.length; i++) {

            System.out.println(listFiles[i].getName());

        }
    }
}
