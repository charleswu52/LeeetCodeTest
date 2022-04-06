package graduate.publiclog.proxifier;

import com.csvreader.CsvReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author WuChao
 * @create 2022/3/19 上午8:30
 */
public class SplitProxifierLog2Json {
    // 将 Proxifier log 日志 转化为 导入到 ES中的JSON文件
    static final int count = 100000; // 10w条数据一个文件
    static String name = "Proxifier";
    static int size = 500;

    // 将 ESRally中的 json数据转换为可以导入ES中的json 数据
    public static void main(String[] args) {
        String str = "proxifier";
        String filePath = "/media/charles/My Passport/Work/LogCompress/logparser/allresult/AEL/" + name + "/" + name + ".log_structured.csv";
//        String outPath = "H:\\Work\\LogCompress\\logparser\\allLogs2Json\\" + name + "\\";
        String jsonOutPutPath = "/media/charles/My Passport/Work/LogCompress/logparser/allLogs2Json/" + name + "/";
        String originOutPutPath = "";
        transform(filePath, jsonOutPutPath, name);
        printImport(jsonOutPutPath, str);
    }

    public static void transform(String filePath, String outPath, String name) {
        File file = new File(filePath);
        long line = 1;
        try {
            // 读取结果文件 并存到 Map 中
            CsvReader csvReader = new CsvReader(filePath);
            // 读取表头
            csvReader.readHeaders();
            while (csvReader.readRecord() && line <= size) {
                long cnt = line / count;
                // 读取一整行数据
                Integer id = Integer.parseInt(csvReader.get("LineId"));
                String time = csvReader.get("Time");
                String program = csvReader.get("Program");
                String content = csvReader.get("Content");
                String value1 = "{\"index\":{\"_index\":\"" + "proxifier_"+size + "\",\"_type\":\"_doc\",\"_id\":" + id + "}}\n";
                String value2 = "{\"time\":\"" + time + "\",\"program\":\"" + program + "\",\"content\":\"" + content + "\"}\n";
                // 写入文件
                File newFile = new File(outPath + name + "_" + size + "_"+ cnt + ".json");
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                // 获取该文件的缓冲输出流
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
                bufferedWriter.write(value1);
                bufferedWriter.write(value2);
                bufferedWriter.flush();
                bufferedWriter.close();
                line++;
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前处理文件包含日志数目为：" + (line - 1));
    }

    /*
    打印导入命令
     */
    public static void printImport(String path, String str) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("当前处理的文件夹：" + path);
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getAbsolutePath();
//            System.out.println(fileName);
            String print = "curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/" +
                    str +"_" + size +
                    "/_bulk?pretty' --data-binary \"@" +
                    fileName +
                    "\"";
            System.out.println(print);
        }
        System.out.println("-------------------------------------");

    }
    /*
     输出到查询测试文件
     */
    public static void verifyCluster(String resultFile, String outputPath) throws Exception {
        // 读取结果文件 并存到 Map 中
        CsvReader csvReader = new CsvReader(resultFile);
        // 读取表头
        csvReader.readHeaders();
        int line = 1;
        while (csvReader.readRecord() && line <= size) {
            // 读取一整行数据
            String content = tokenize(csvReader.get("Content"));
            String eventId = csvReader.get("EventId");
            File newFile = new File(outputPath + eventId + ".log");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            // 获取该文件的缓冲输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile, true), StandardCharsets.UTF_8));
            // 写入信息
            bufferedWriter.write(content + "\n");
            bufferedWriter.flush();// 清空缓冲区
            bufferedWriter.close();// 关闭输出流
            line++;
        }
        System.out.println("当前处理文件包含日志数目为：" + (line - 1));
        csvReader.close();
    }

    public static String tokenize(String str) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", str);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            stringBuilder.append(charTermAttribute + " ");
        }
        tokenStream.close();
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}

