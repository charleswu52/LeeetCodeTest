package logparser;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author WuChao
 * @create 2021/12/12 下午3:46
 */
public class SplitJson {
    public static void main(String[] args)throws Exception {
        // 读取 InfluxDB json 日志 并按照 schema 进行划分
        String filePath = "/media/charles/My Passport/Work/CompressData/exampleData/influxdb-2021-03-30T11-06-23.613.log"; // json 日志 文件
        String outputPath = "/media/charles/My Passport/Work/CompressData/exampleData/influxdbOut/";
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));

        String temp = null;
        int id = 0; // 标记是几行数据
        Set<String> stringSet = new HashSet<>();
        while ((temp = reader.readLine()) != null) {
            id++;
            String fieldValues = splitJson(temp);
            File newFile = new File(outputPath + "influxdb2_test.log");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write(fieldValues + "\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流


        }
        reader.close();


    }

    public static String splitJson(String log)throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(log, Feature.OrderedField); // 按照 json 原先的顺序解析
        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        StringBuilder fieldValues = new StringBuilder();
        int idx = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            String fieldVal = String.valueOf(next.getValue());
            if (idx == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
                fieldValues.append(date.parse(fieldVal).getTime()+" ");
            }else if (idx == 2) {
                fieldValues.append(fieldVal + ": ");
            } else {
                fieldValues.append(fieldVal + " ");
            }
            idx++;
        }

        return fieldValues.toString();
    }
}
