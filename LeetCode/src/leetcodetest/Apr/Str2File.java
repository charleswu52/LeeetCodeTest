package leetcodetest.Apr;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author WuChao
 * @since 2021/4/6 上午11:31
 */
public class Str2File {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 87; i++) {
            stringBuilder.append("curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/plain/_bulk?pretty'" +
                    " --data-binary \"@/media/charles/Samsung_T5/es-rally/curl/tpcc_general_plain.log_split_13/" +
                    "tpcc_general_plain.log_" + i + ".json\"\n");

        }
        String str=stringBuilder.toString();
        FileWriter writer;
        try {
            writer = new FileWriter("/home/charles/Downloads/OtherCode/data/导入文件.txt");
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
