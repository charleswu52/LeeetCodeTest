package graduate.logging;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/14 下午2:44
 */
public class Log2Json {
    static final int count = 100000;
    static String name = "documents-181998";

    // 将 ESRally中的 json数据转换为可以导入ES中的json 数据
    public static void main(String[] args) {
        String[] names1 = {"documents-201998", "documents-211998", "documents-221998", "documents-231998", "documents-241998"};
        String[] names = { "documents-211998"};
        for (String str : names) {
            String filePath = "/media/charles/Data/研究生学习/华为压缩索引检索项目/ESRally数据/logging/解压数据/" + str + ".json/" + str + ".json";
            String outPath = "/media/charles/Data/研究生学习/华为压缩索引检索项目/毕设相关/实验数据/logging/" + str + "/";
            transform(filePath, outPath,str);
            printImport(outPath,str);
        }

    }

    public static void transform(String filePath, String outPath,String str) {
        File file = new File(filePath);
        long line = 1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                long cnt = line / count;
                File newFile = new File(outPath + str + "_" + cnt + ".json");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }

                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                // 写入信息
                bufferedWriter.write("{\"index\":{\"_index\":\"" + str + "\",\"_type\":\"_doc\",\"_id\":" + (line++) + "}}\n");
                bufferedWriter.write(temp + "\n");
                bufferedWriter.flush();// 清空缓冲区
                bufferedWriter.close();// 关闭输出流
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前处理文件包含日志数目为：" + (line - 1));
    }

    /*
    打印导入命令
     */
    public static void printImport(String path,String str) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("当前处理的文件夹：" + path);
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getAbsolutePath();
//            System.out.println(fileName);
            String print = "curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/" +
                    str +
                    "/_bulk?pretty' --data-binary \"@" +
                    fileName +
                    "\"";
            System.out.println(print);
        }
        System.out.println("-------------------------------------");

    }
}
