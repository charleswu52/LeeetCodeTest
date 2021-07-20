package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * @author WuChao
 * @create 2021/6/17 23:07
 */
public class Construct16FilesBaseStr_count {
    public static void main(String[] args) throws Exception {

        String[] fileNames = {
                "influxdb-2021-03-25T21-58-56.307.log",
                "influxdb-2021-03-29T01-02-00.054.log",
                "influxdb-2021-03-29T04-42-17.999.log",
                "influxdb-2021-03-29T08-51-18.460.log",
                "influxdb-2021-03-29T13-51-28.643.log",
                "influxdb-2021-03-29T20-54-00.994.log",
                "influxdb-2021-03-30T02-12-08.451.log",
                "influxdb-2021-03-30T04-34-06.337.log",
                "influxdb-2021-03-30T06-23-09.552.log",
                "influxdb-2021-03-30T08-22-52.484.log",
                "influxdb-2021-03-30T11-06-23.613.log",
                "influxdb-2021-03-30T14-16-44.591.log",
                "influxdb-2021-03-30T20-57-00.672.log",
                "influxdb-2021-03-31T01-49-17.195.log",
                "influxdb-2021-03-31T03-39-56.185.log",
                "influxdb-2021-03-31T05-10-00.726.log"
        };

        for (int j = 0; j < fileNames.length; j++) {


            System.out.println(fileNames[j]);
            String path = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16LeveCut\\" + fileNames[j] + "\\";
            String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\all16BasicStr\\" + fileNames[j] + "\\";

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
                    int maxLen2 = Integer.MIN_VALUE;
                    String maxStr = "";
                    LinkedHashMap<String, Integer> stringCount = new LinkedHashMap<>();
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
                    String temp = null;
                    while ((temp = reader.readLine()) != null) {
                        JSONObject jsonObject = JSONObject.parseObject(temp);

//                        String t = (String) jsonObject.get("caller");
                        String t = temp.substring(50);
                        stringCount.put(t, stringCount.getOrDefault(t, 0) + 1);
                        if (stringCount.get(t) > maxLen2) {
                            maxStr = temp;
                            maxLen2 = stringCount.get(t);
                        }
                    }
                    reader.close();

//                    System.out.println(fileName);
//                    System.out.println(" " + maxLen2 + ":" + maxStr);



                    File newFile = new File(outPath + fileName + "_count.raw");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write(maxStr);
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//        Iterator<Map.Entry<String, String>> iterator = fileStr.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> next = iterator.next();
//            File newFile = new File(outPath +"-379033988出现次数最多的串.txt");
//            if (!newFile.exists()) {
//                newFile.createNewFile();
//            }
//            // 获取该文件的缓冲输出流
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
//            // 写入信息
//            bufferedWriter.write("fileName: "+next.getKey()+"\nBaseStr: " + next.getValue()+"\n");
//            bufferedWriter.flush();// 清空缓冲区
//            bufferedWriter.close();// 关闭输出流
//        }
        }


    }
}
