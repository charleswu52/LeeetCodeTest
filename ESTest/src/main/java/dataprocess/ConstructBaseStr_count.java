package dataprocess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/6/17 23:07
 */
public class ConstructBaseStr_count {
    public static void main(String[] args) throws Exception {
        String path = "E:\\研究生学习\\ES测试\\levelFields";
        String outPath = "E:\\研究生学习\\ES测试\\output\\";

        File dir = new File(path);
        File[] files = dir.listFiles();
        BufferedReader reader = null;
        LinkedHashMap<String, String> fileStr = new LinkedHashMap<>();
        for (int i = 0; i < files.length; i++) {
            try {
                /*
                遍历所有串 找到出现次数最多的串作为基准串
                 */
                String fileName = files[i].getName();
                int maxLen = Integer.MIN_VALUE;
                String maxStr="";
                LinkedHashMap<String, Integer> stringCount = new LinkedHashMap<>();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF-8"));//new BufferedReader(new FileReader());
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    String t = temp.substring(50);
                    stringCount.put(t, stringCount.getOrDefault(t, 0) + 1);
                }
                reader.close();
                Iterator<Map.Entry<String, Integer>> iterator = stringCount.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Integer> next = iterator.next();
                    if (next.getValue() > maxLen) {
                        maxLen = next.getValue();
                        maxStr = next.getKey();
                    }
                }
//                System.out.println(maxLen);
                fileStr.put(fileName, maxStr);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Iterator<Map.Entry<String, String>> iterator = fileStr.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            File newFile = new File(outPath +"countStr.txt");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write("fileName: "+next.getKey()+"\nBaseStr: " + next.getValue()+"\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流
        }





    }
}
