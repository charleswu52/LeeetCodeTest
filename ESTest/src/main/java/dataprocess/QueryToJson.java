package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2021/7/21 11:56
 */
public class QueryToJson {
    public static void main(String[] args) {
//        String path = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\1\\ES数据\\influxdb-2021-03-30T11-06-23.613.log";
        String path = "/media/charles/Data/研究生学习/ES测试/ES实验/logs_trans/1/ES数据/influxdb-2021-03-30T11-06-23.613.log";
        File file = new File(path);
        BufferedReader reader = null;
//        String outPath = "E:\\研究生学习\\ES测试\\ES实验\\logs_trans\\queryFile\\";
        String outPath = "/media/charles/Data/研究生学习/ES测试/ES实验/logs_trans/queryFile/";
        int line = 1;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                JSONObject jsonObject = JSONObject.parseObject(temp);

                if (jsonObject.containsKey("query")) {
                    String query = (String) jsonObject.get("query");
                    sb.append(query + "\n");
                    query = query.replace("\"", "\\\"");

                    File newFile = new File(outPath + "query.json");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write("{\"index\":{\"_index\":\"log_query\",\"_type\":\"_doc\",\"_id\":" + (line++) + "}}\n");
                    bufferedWriter.write("{\"query\":\"" + query + "\"}\n");
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流

                    newFile = new File(outPath + "query.txt");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    // 获取该文件的缓冲输出流
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write(sb.toString());
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
