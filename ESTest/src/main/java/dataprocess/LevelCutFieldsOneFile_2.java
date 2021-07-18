package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/18 12:47
 */
public class LevelCutFieldsOneFile_2 {

    public static void main(String[] args)throws Exception {
        String path = "E:\\levelFields_oneFile\\再细分\\-379033965.txt";
        String outPath = "E:\\levelFields_oneFile\\再细分\\输出\\";

        File file = new File(path);
        BufferedReader reader = null;
        List<String> strings1 = new ArrayList<>();
        List<String> strings2 = new ArrayList<>();
        List<String> strings3 = new ArrayList<>();
        List<String> strings4 = new ArrayList<>();
        List<String> strings5 = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.parseObject(temp);

                String msg = (String) jsonObject.get("msg");
                if (msg.startsWith("show")) {
                    strings1.add(temp);
                } else if (msg.startsWith("select last")) {
                    strings2.add(temp);
                } else if (msg.startsWith("SELECT last")) {
                    strings3.add(temp);
                } else if (msg.startsWith("select")) {
                    strings4.add(temp);
                } else {
                    strings5.add(temp);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File newFile = new File(outPath +  "-379033965_1.txt");
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

        newFile = new File(outPath +  "-379033965_2.txt");
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

        newFile = new File(outPath +  "-379033965_3.txt");
        // 获取该文件的缓冲输出流
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
        // 写入信息
        for (String t : strings3) {
            bufferedWriter.write(t + "\n");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

        newFile = new File(outPath +  "-379033965_4.txt");
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

        newFile = new File(outPath +  "-379033965_5.txt");
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
    }
}
