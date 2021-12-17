package logparser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2021/12/16 上午9:58
 */
public class GenerateAllInfluxDB {
    public static void main(String[] args)throws Exception {
        String filesPath = "/media/charles/My Passport/Work/CompressData/InfluxDB/";
        String outputPath = "/media/charles/My Passport/Work/CompressData/exampleData/";
        generate(filesPath,outputPath);
    }

    /*
    将所有InfluxDB 文件合并成一个文件
     */
    public static void generate(String filesPath, String outputPath)throws Exception {
        File file = new File(filesPath);
        File[] files = file.listFiles();
        BufferedReader reader = null;
        String temp = null;
        File folder = new File(outputPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        int id = 0;
        for (File f : files) {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), StandardCharsets.UTF_16));
            while ((temp = reader.readLine()) != null) {
                System.out.println(++id);
//                String value = SplitJson.splitJson(temp);
                //
                File newFile = new File(outputPath + "InfluxDB_Origin.log");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write(temp + "\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流
            }
            reader.close();
        }
    }
}
