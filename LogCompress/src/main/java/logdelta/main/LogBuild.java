package logdelta.main;

import logdelta.bean.BitSet;
import logdelta.core.LogCompress;
import logdelta.util.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/12/22 下午1:14
 */
public class LogBuild {
    public static void printUsage() {
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("                        LogBuild Version 1.0");
        System.out.println("                            Dec 2021");
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("Build the index of the input logfile.");
        System.out.println("Usage: \t" + "[-g gramLength] baseSequenceFile deltaDir [indexName]");
        System.out.println("\tFlag:");
        System.out.println("\t-g gramLength\tLength of grams in inverted index (default value: 5)");

    }

    public static void main(String[] args) throws Exception {

        int baseSize = 0;
        int gramLength = 0;
        int L = -19643;
        List<String> deltaFiles = new ArrayList<>();
        LogCompress search = new LogCompress();
        List<String> queryStrings;
        String tmpQuery;
        String deltaFileOut, deltaFileGaps, deltaFileErrorsgaps, deltaFileAlign, deltaNew;
        char next_option;
        Scanner scanner = new Scanner(System.in);
        if(args.length<2) printUsage();
        do {
            next_option = scanner.next().charAt(0);
            switch (next_option) {
                case 'b':
                    baseSize = Integer.parseInt(scanner.nextLine());
                    break;
                case 'g':
                    gramLength = Integer.parseInt(scanner.nextLine());
                    break;
                case 'h':
                    printUsage();
                    break;
                case '?':
                    System.out.println("Unknown option.\n");
                    printUsage();
                    break;
                case 'q':
                    break;
                default:
                    printUsage();
            }
        }while (next_option != -1);

        String logFile = "";
        System.out.println("text file: " + logFile);
        search.setmBaseFile(logFile);

        String deltaFileNames = "";
        System.out.println("delta dir: "+deltaFileNames);


        long startTime = System.currentTimeMillis();

        Tools.getFileInDir(deltaFileNames, deltaFiles);
        BitSet.setNum(deltaFiles.size()*2);


        String indexName = "";
        if(indexName.isEmpty()) indexName = logFile;

        System.out.println("index: "+indexName);

        if(gramLength == 0) gramLength = 5;
        if(L == -19643) L = 0;
        search.setmGramLength(gramLength);
        search.setmAnchorSpan((char) L);
        search.loadBaseString(logFile, baseSize*LogCompress.BASELENGTH);
        search.getConfiguration();
        //search.loadDelta(deltaFiles);
        search.buildInvertedLists();
        search.saveInvertedLists(indexName);
        //search.saveDeltaIndex(indexName);
        long buildCost = System.currentTimeMillis() - startTime;
        System.out.println("total build cost: "+buildCost+" ms");

    }
}
