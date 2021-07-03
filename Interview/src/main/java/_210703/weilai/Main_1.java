package _210703.weilai;

import java.util.HashSet;
import java.util.Set;

public class Main_1 {
    public String skipsame (String old) {
        // write code here
        StringBuffer sb = new StringBuffer();
        Set<Character> set = new HashSet<>();
        for (Character ch : old.toCharArray()) {
            if (!set.contains(ch)) {
                sb.append(ch);
                set.add(ch);
            }
        }
        return sb.toString();
    }
}
