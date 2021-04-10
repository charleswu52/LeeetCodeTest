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
        for (int i = 0; i <= 7; i++) {
            stringBuilder.append("curl -H \"Content-Type: application/json\" -XPOST 'localhost:9200/log8_1_ngram/_bulk?pretty'" +
                    " --data-binary \"@/home/charles/WorkSpace/ES实验/logs_trans/81/" +
                    "log8_1_ngram_" + i + ".json\"\n");

        }
        String str=stringBuilder.toString();
        FileWriter writer;
        try {
            writer = new FileWriter("/home/charles/WorkSpace/ES实验/log8_1_ngram索引导入命令.txt");
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
