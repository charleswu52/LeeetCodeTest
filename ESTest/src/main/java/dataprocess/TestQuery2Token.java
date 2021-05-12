package dataprocess;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuChao
 * @since 2021/4/29 下午4:51
 */
public class TestQuery2Token {
    // 将之前的query进行分词处理
    public static void main(String[] args)throws Exception {

        String[] fields = {"SELECT",
                "SELECT last(SL_1_Tag108) AS SL_1_Tag108",
                "and",
                "SHOW TAG VALUES ON",
                "fill(none)",
                "SZSSLKJYXGS_1OU7.autogen.",
                        "WHERE gatewayId = '2c938083783650af01786535768406ac'",
                "AND time >= '2021-03-29T08:36:32Z' AND time <= '2021-03-30T08:36:32Z'",
                "SELECT last(SL_1_Tag118) AS SL_1_Tag118 FROM SZSSLKJYXGS_1OU7.autogen.",
                "SHOW TAG VALUES ON _internal WITH KEY = hostname WHERE (_name = 'database') AND (_tagKey = 'hostname')",
                "WHERE gatewayId = '2c938083783650af01786535768406ac'",
                "last(SL_1_Tag18)",
                "difference(pointsWrittenOK) / 60",
                "gatewayId = '2c938083783650af01786535768406ac' AND time >= '2021-03-29T08:37:13Z' AND time <= '2021-03-30T08:37:13Z'",
                "count(C1_B1_G1_Tag3)",
                "046ed1449dec435d83f99f524b0b6b5f",
                "hostname",
                "autogen",
                "select DIFFERENCE(pointsWrittenOK)/60 from httpd where time > now() - 120s and bind='192.168.1.198:8635,172.16.11.6:8635'",
                "time <= '2021-03-30T08:38:48Z'"
        };
        for (String field : fields) {
            StandardAnalyzer analyzer = new StandardAnalyzer();
            TokenStream tokenStream = analyzer.tokenStream("", field);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            String trans = "";
            while (tokenStream.incrementToken()) {
                trans += charTermAttribute.toString() + " ";
//                                System.out.print(charTermAttribute.toString()+" ");
            }
            System.out.println(trans);
            tokenStream.close();
        }
    }

    public void cutQuery(String field)throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", field);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        String trans = "";
        int tokenLen=0;
        Set<String> set = new HashSet<>();
        while (tokenStream.incrementToken()) {
            trans += charTermAttribute.toString() + " ";
            set.add(charTermAttribute.toString());
            tokenLen++;
        }
        System.out.println("Query：\n" + trans + "\ntokenLength:" + tokenLen + ";tokenSetLen:" + set.size());
        tokenStream.close();
    }

    @Test
    public void test()throws Exception{
        String query =
                "as sl_1_tag108 from szsslkjyxgs_1ou7 autogen 68885b4c32ff4708a8a649c9b4baae59 where gatewayid 2c938083783650af01786535768406ac and time 2021 03 29t08 44\n";
        cutQuery(query);

    }
}
