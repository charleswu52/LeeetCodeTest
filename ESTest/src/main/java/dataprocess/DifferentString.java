package dataprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/8 下午6:24
 */
public class DifferentString {
    public static void main(String[] args) throws Exception {
        String path = "/home/charles/WorkSpace/ES实验/logs_trans/oneLogFields/";
        File file = new File(path + "path.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String temp = null;
        Set<String> strings = new HashSet<>();
        HashMap<String, Integer> strCount = new HashMap<>();
        String maxStr = "";
        int maxLen = Integer.MIN_VALUE;
        while ((temp = bufferedReader.readLine()) != null) {
            strCount.put(temp, strCount.getOrDefault(temp, 0) + 1);
        }

        Iterator<Map.Entry<String, Integer>> iterator = strCount.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
//            if (next.getValue() >= 400 ) {
//                System.out.println("query: " + next.getKey() + " ;result_size:" + next.getValue());
//            }
            if (next.getValue() > maxLen) {
                maxLen = next.getValue();
                maxStr = next.getKey();
            }
        }
        if (strCount.containsKey("/tsdb/b836e78214e0486a9fd1c2543f3124e2in13/data/jufengshukong/monitor/8772/index/1/L1-00000002.tsi")) {
            System.out.println("有");

        }else {
            System.out.println("无");
        }


        bufferedReader.close();
        System.out.println("Count: " + strCount.size());
        System.out.println("maxLen: " + maxLen);
        System.out.println("maxLenStr: " + maxStr);


    }
}
