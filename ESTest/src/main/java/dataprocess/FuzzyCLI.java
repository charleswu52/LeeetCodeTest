package dataprocess;

/**
 * @author WuChao
 * @since 2021/5/20 下午2:20
 */
public class FuzzyCLI {
    public static void main(String[] args) {
        String[] queryString = {"went",
                "to",
                "the",
                "barber",
                "shop",
                "to",
                "ask",
                "my",
                "friend",
                "a",
                "question",
                "about",
                "hair",
                "and",
                "styling",
                "while",
                "getting",
                "my",
                "hair",
                "cut"};
        for (int i = 0; i < 3; i++) {
            for (String q : queryString) {

                System.out.println("fuzzy" +
                        " -f " +
                        "/home/charles/CLionProjects/test/orchid_mytest/fst_file/fst2" +
                        " -z " +
                        q +
                        " -d " +
                        i);
            }
        }
    }
}
