package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuChao
 * @create 2021/7/18 12:47
 */
public class LevelCutFieldsOneFile_3 {

    public static void main(String[] args)throws Exception {
        String path = "E:\\levelFields_oneFile\\再细分\\-1760949986.txt";
        String outPath = "E:\\levelFields_oneFile\\再细分\\输出\\";

        File file = new File(path);
        BufferedReader reader = null;
        List<String> strings1 = new ArrayList<>();
        List<String> strings2 = new ArrayList<>();
        List<String> strings3 = new ArrayList<>();
        List<String> strings4 = new ArrayList<>();
        List<String> strings5 = new ArrayList<>();
        List<String> strings6 = new ArrayList<>();
        Set<String> set = new HashSet<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.parseObject(temp);

                String query = (String) jsonObject.get("query");
//                set.add(query.substring(0, 15));
                if (query.startsWith("SHOW")) {
                    strings1.add(temp);
                } else if (query.startsWith("SELECT last")) {
                    strings2.add(temp);
                } else if (query.startsWith("SELECT diff")) {
                    strings3.add(temp);
                } else if (query.startsWith("SELECT sum")) {
                    strings4.add(temp);
                } else if (query.startsWith("SELECT count")) {
                    strings5.add(temp);
                } else {
                    strings6.add(temp);

                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        File newFile = new File(outPath +  "-1760949986_1.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings1) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

        newFile = new File(outPath +  "-1760949986_2.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings2) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

        newFile = new File(outPath +  "-1760949986_3.txt");
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings3) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

        newFile = new File(outPath +  "-1760949986_4.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings4) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

        newFile = new File(outPath +  "-1760949986_5.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings5) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流


        newFile = new File(outPath +  "-1760949986_6.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings6) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流


    }
}
