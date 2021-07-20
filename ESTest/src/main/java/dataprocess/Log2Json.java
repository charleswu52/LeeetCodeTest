package dataprocess;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @since 2021/4/9 下午3:06
 */
public class Log2Json {
    public static void main(String[] args) {
        String path = "/media/charles/Data/研究生学习/ES测试/ES实验/logs/";
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        String outPath = "/media/charles/Data/研究生学习/ES测试/ES实验/logs_trans/queryFile/logs/";
        int line = 1;
        for (int i = 0; i < tempList.length; i++) {
            try {
                System.out.println(tempList[i]);
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
                String temp=null;
                while ((temp = reader.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(temp);

                    if (jsonObject.containsKey("query")) {
                        String query = (String) jsonObject.get("query");
                        query = query.replace("\"", "\\\"");
//                        System.out.println(query);

                        File newFile =new File(outPath+"all.json");
                        if(!newFile.exists()){
                            newFile.createNewFile();
                        }
                        // 获取该文件的缓冲输出流
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile,true), StandardCharsets.UTF_8));
                        // 写入信息
                        bufferedWriter.write("{\"index\":{\"_index\":\"logs_query\",\"_type\":\"_doc\",\"_id\":" + (line++) + "}}\n");
                        bufferedWriter.write("{\"query\":\""+ query + "\"}\n");
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
}
