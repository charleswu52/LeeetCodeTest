package logdelta.main;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行日志搜索 接受命令行传参
 * @author WuChao
 * @create 2021/12/19 下午12:58
 */
public class LogSearch {
    private static final int MKEY = 4;
    public static char optArg;
    public static int optInd;

    public static void printUsage() {
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("                      LogSearch Version 1.0");
        System.out.println("                            Dec 2021");
        System.out.println("\n-----------------------------------------------------------------\n");

        System.out.println("Perform exact query, approximate query or regular query on compressed log data.");
        System.out.println("Commands:");
        System.out.println("l indexName\tload index for searching");
        System.out.println("s [-b] [-e:threshold] queryFile(default MIN_VERIFY algorithm of precise query)\t");
        System.out.println("  -b            BASIC algorithm of precise query");
        System.out.println("  -e:threshold  threshold value of edit distance (default value: 2)");

        System.out.println("q\t\tquit");
    }


    public static void main(String[] args) {

        int type = 0;
        int L = -1;
        int threshold = 0;
        String deltaFileNames;
        List<String> deltaFiles = new ArrayList<>();
        String fileName, indexName, queryFiles;


    }

}
