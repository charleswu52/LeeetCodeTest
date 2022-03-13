package loggingprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/13 10:11
 */
public class CutFieldsWithoutToken2 {
    // 提取 timestamp  + request 不分词
    public static void main(String[] args) throws Exception {
        String filePath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\input\\";
        File file = new File(filePath);

        File[] fileList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "E:\\研究生学习\\华为压缩索引检索项目\\ESRally数据\\logging\\解压数据\\documents-191998.json\\output\\";

        for (int i = 0; i < fileList.length; i++) {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileList[i]), StandardCharsets.UTF_8));//new BufferedReader(new FileReader());
            String temp = null;
            int id = 0;
            while ((temp = reader.readLine()) != null) {
                id++;
                JSONObject jsonObject = JSONObject.parseObject(temp);
                Object request = jsonObject.get("request");
                Object timestamp = jsonObject.get("@timestamp");


                File newFile = new File(outPath + "timestamp_request.txt");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write("{\"@timestamp\":" + timestamp + ",\"request\":\"" + request + "\"}\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流

                System.out.println(id);

            }

        }
        reader.close();

    }
}
