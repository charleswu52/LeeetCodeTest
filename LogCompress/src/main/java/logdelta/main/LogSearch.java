package logdelta.main;

import logdelta.core.LogCompress;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 执行日志搜索 接受命令行传参
 *
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


    public static void main(String[] args) throws Exception {
        int type = 0;
        int L = -1;
        int threshold = 0;
        String deltaFileNames;
        List<String> deltaFiles;
        LogCompress search = new LogCompress();

        BufferedReader queryFile;
        List<String> queryStrings = new ArrayList<>();
        String tmpQuery, refFile, qryFile, deltaFile, gen;


        double expectationValue = 10;
        search.setDelFlag(0);
        search.setChangeFlag(0);

        long startTime, costTime;

        type = 3;
        printUsage();
        String str;
        Scanner scanner = new Scanner(System.in);
        String indexName, queryFiles;
        while ((str = scanner.next()) != null) {
            //cout<<str<<endl;
            String tmp = scanner.next();
            // TODO 搞清楚这个循环的意思
//            while (ss >> tmp) {
            switch (tmp.charAt(0)) {
                case 'l':
                    indexName = scanner.next();
                    startTime = System.currentTimeMillis();
                    search.loadInvertedLists(indexName);
                    search.loadDeltaIndex(indexName);
                    //if(search.getCompress())
                    //	search.loadcompress(indexname);
                    costTime = System.currentTimeMillis() - startTime;
                    System.out.println("total load cost: " + costTime + " ms");
                    break;
                case 's':
                    type = 3;
                    tmp = scanner.next();
                    // >> 这个循环的意思
//                        while (ss >> tmp) {
                    //cout<<tmp<<endl;
                    if (tmp.charAt(0) == '-') {
                        switch (tmp.charAt(1)) {
                            case 'b':
                                type = 1;
                                break;
                            case 'e':
                                type = 7;
                                // TODO atoi 搞清楚意思
//                                        threshold = atoi(tmp.c_str() + 3);
                                break;
                            default:
                                type = 3;
                        }
                    } else {
                        queryFiles = tmp;
                        queryFile = new BufferedReader(new InputStreamReader(new FileInputStream(queryFiles)));

                        while ((tmpQuery = queryFile.readLine()) != null) {
                            queryStrings.add(tmpQuery);
                        }
                        System.out.println("========= read querys is over ========= ");
                        queryFile.close();
                        System.out.println("Query number: " + queryStrings.size());
                        //cout<<type<<threshold<<endl;
                        search.search(type, queryStrings, threshold);
                        queryStrings.clear();
                    }
//                        }
                    break;
                case 'd': {
                    startTime = System.currentTimeMillis();
                    type = 3;
                    search.setDelFlag(1);
                    queryFiles = scanner.next();
                    queryFile = new BufferedReader(new InputStreamReader(new FileInputStream(queryFiles)));

                    while ((tmpQuery = queryFile.readLine()) != null) {
                        queryStrings.add(tmpQuery);
                    }
                    System.out.println("========= read query is over ========= ");
                    queryFile.close();
                    System.out.println("Query number: " + queryStrings.size());
                    //cout<<type<<threshold<<endl;
                    search.search(type, queryStrings, threshold);
                    queryStrings.clear();
                    costTime = System.currentTimeMillis() - startTime;
                    System.out.println("total delete cost: " + costTime + " ms");
                    search.setDelFlag(0);
                    break;
                }
                case 'c': {
                    type = 3;
                    search.setChangeFlag(1);
                    queryFiles = scanner.next();
                    queryFile = new BufferedReader(new InputStreamReader(new FileInputStream(queryFiles)));
                    startTime = System.currentTimeMillis();
                    while ((tmpQuery = queryFile.readLine()) != null) {
                        queryStrings.add(tmpQuery);
                    }
                    System.out.println("========= read querys is over ========= ");
                    queryFile.close();
                    System.out.println("Query number: " + queryStrings.size());
                    //cout<<type<<threshold<<endl;
                    search.search(type, queryStrings, threshold);
                    queryStrings.clear();
                    costTime = System.currentTimeMillis() - startTime;
                    System.out.println("total change cost: " + costTime + " ms");
                    search.setChangeFlag(0);
                    break;
                }
                case 'q':
                    System.exit(0);

                default:
                    printUsage();
            }

        }
//        }


    }

}
