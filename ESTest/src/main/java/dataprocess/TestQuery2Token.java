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

        String[] fields = {
                "DB1.DBW2",
                       " (C1_B1_G1_Tag3)",
                "SHOW TAG VALUES ON",
                "pointswrittenok",
                "database",
                "SL_1_Tag114",
               " count",
                "difference",
                "hostname",
                "autogen",
                "AND time>='2021-03-29T08:36:32Z' AND time <='2021-03-30T08:36:32Z\n"
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
        String s = "SHOW TAG VALUES ON _internal WITH KEY = hostname WHERE (_name = 'database') AND (_tagKey = 'hostname')";
        cutQuery(s);

    }
}
