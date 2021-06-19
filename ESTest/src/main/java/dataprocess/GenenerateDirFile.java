package dataprocess;

import java.io.File;
import java.io.IOException;

/**
 * @author WuChao
 * @create 2021/6/19 9:48
 */
public class GenenerateDirFile {
    /**
     * 构造Delta压缩的 原始串文件
     */
    public static void main(String[] args) throws IOException {
        String[] fileNames = {
                "-379030056",
                "1795464790",
                "1322546210",
                "1322545378",
                "1795460877",
                "426208757",
                "1716040294",
                "-1481351056",
                "1795462616",
                "-1296842167",
                "1795461931",
                "1795460691",
                "-1296840304",
                "1795460824",
                "2086904729",
                "-379033988",
                "-1296842130",
                "1905983885",
                "-1760949986",
                "-379029934",
                "2086904704",
                "1322547084"
        };
        System.out.println(fileNames.length);
        String filePath = "E:\\WorkSpace\\Clion\\DeltaCompress\\input\\";
        for (String filename : fileNames) {
            File file1 = new File(filePath + filename + "_count.raw");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            File file2 = new File(filePath + filename + "_construct.raw");
            if (!file2.exists()) {
                file2.createNewFile();
            }
        }
    }
}
