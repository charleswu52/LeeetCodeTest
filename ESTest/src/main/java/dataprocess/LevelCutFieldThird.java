package dataprocess;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/6/23 11:01
 */
public class LevelCutFieldThird {
    /**
     * 水平分割 三级
     */
    public static void main(String[] args) throws IOException {
        String path = "E:\\levelFields\\";
        String outPath = "E:\\levelFields\\third\\";
        File file = new File(path + "-379029934.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String temp = "";
        Set<String> set = new HashSet<>();
        while ((temp = reader.readLine()) != null) {
            LinkedHashMap<String, Object> jsonMap = JSONObject
                    .parseObject(temp.toString(), new TypeReference<LinkedHashMap<String, Object>>() {
                    });
            String pathValue = jsonMap.get("error").toString();
            System.out.println(pathValue);


//            if (pathValue.startsWith("SELECT last")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_1.txt", temp);
//            } else if (pathValue.startsWith("SELECT first")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_2.txt", temp);
//            } else if (pathValue.startsWith("SELECT sum")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_3.txt", temp);
//            } else if (pathValue.startsWith("SELECT count")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_4.txt", temp);
//            } else if (pathValue.startsWith("SELECT difference")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_5.txt", temp);
//            } else if (pathValue.startsWith("SHOW MEASUREMENTS")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_6.txt", temp);
//            } else if (pathValue.startsWith("SHOW DATABASES")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_7.txt", temp);
//            } else if (pathValue.startsWith("SHOW TAG")) {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_8.txt", temp);
//            } else {
//                writeFile(outPath + "\\-1760949986\\-1760949986_3_9.txt", temp);
//            }


        }
        reader.close();
        System.out.println(set.size());
//        for (String s : set) {
//            System.out.println(s);
//        }

    }

    public static void writeFile(String filename, String content) throws IOException {
        File newFile = new File(filename);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        bufferedWriter.write(content + "\n");
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }
}
