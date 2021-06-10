package dataprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/8 下午6:24
 */
public class DifferentString {
    public static void main(String[] args) throws Exception {
        String path = "/home/charles/WorkSpace/ES实验/logs_trans/allLogsFields/";
        File file = new File(path + "msg.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String temp = null;
        Set<String> strings = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;
        while ((temp = bufferedReader.readLine()) != null) {
//            if (temp.length() < 80) {
//                strings.add(temp);
//                maxLen = Math.max(maxLen, temp.length());
//            }
            if (temp.startsWith("select") || temp.startsWith("SELECT")) {
                strings.add(temp);
            }
        }

        bufferedReader.close();
        System.out.println("Count:" + strings.size());
//        for (String s : strings) {
//            System.out.println(s);
//        }
    }
}
