package graduate.influxdb;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/14 下午12:21
 */
public class AllLog2Json {
    // 将 16 个 InfluxDB 日志数据 生成导入到ES中的json文件
    public static void main(String[] args) {
        // InfluxDB 日志所在的文件夹
        String path = "/media/charles/Data/研究生学习/华为压缩索引检索项目/ES实验/logs";
        String outPath = "/media/charles/Data/研究生学习/华为压缩索引检索项目/毕设相关/实验数据/InfluxDB/";
        allLog2Json(path, outPath);
        printImport(outPath);

    }

    public static void allLog2Json(String path, String outPath) {
        // InfluxDB 日志所在的文件夹
        File file = new File(path);
        File[] tempList = file.listFiles();
        BufferedReader reader = null;
        int line = 0;
        for (int i = 0; i < tempList.length; i++) {
            try {
                String fileName = tempList[i].getName();
                System.out.println("当前处理文件：" + fileName);
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i]), StandardCharsets.UTF_16));//new BufferedReader(new FileReader());
                String temp = null;

                while ((temp = reader.readLine()) != null) {

                    File newFile = new File(outPath + fileName + ".json");
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }

                    // 获取该文件的缓冲输出流
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                    // 写入信息
                    bufferedWriter.write("{\"index\":{\"_index\":\"logs_influxdb\",\"_type\":\"_doc\",\"_id\":" + (++line) + "}}\n");
                    bufferedWriter.write(temp + "\n");
                    bufferedWriter.flush();// 清空缓冲区
                    bufferedWriter.close();// 关闭输出流

//                    break;
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("日志条目总数：" + line); //全部：2719173 小:165200

    }

    /*
    打印导入命令
     */
    public static void printImport(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getAbsolutePath();
//            System.out.println(fileName);
            String print = "curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/" +
                    "logs_influxdb" +
                    "/_bulk?pretty' --data-binary \"@" +
                    fileName +
                    "\"";
            System.out.println(print);
        }

    }
}
