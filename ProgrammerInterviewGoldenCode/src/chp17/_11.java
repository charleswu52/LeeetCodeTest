package chp17;

import org.junit.Test;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/6/7 上午9:58
 */
public class _11 {
    /**
     * 程序员面试金典(version 6) -  面试题 17.11. 单词距离
     * 难度: medium
     * <p>
     * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     * <p>
     * 示例:
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * 输出：1
     * <p>
     * 数据范围：
     * words.length <= 100000
     */

    /*
    思路1： 哈希记录 搜索
     */
    public int findClosest(String[] words, String word1, String word2) {
        if (words.length < 1) {
            return -1;
        }

        Map<String, ArrayList<Integer>> store = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (store.containsKey(words[i])) {
//                ArrayList<Integer> integers = store.get(words[i]);
//                integers.add(i);
//                store.put(words[i], integers);
                store.get(words[i]).add(i);
            } else {
                int finalI = i;
                store.put(words[i], new ArrayList<Integer>(){{add(finalI);}});
            }
        }
        if (word1.equals(word2) && store.containsKey(word1)) {
            return 0;
        }
        ArrayList<Integer> list1 = null;
        ArrayList<Integer> list2 = null;
        if (store.containsKey(word1)) {
            list1 = store.get(word1);
        }
        if (store.containsKey(word2)) {
            list2 = store.get(word2);
        }
        if (list1 == null || list2 == null) {
            return -1;
        }
        int i = 0, j = 0;
        int res = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size()) {
            if (list2.get(j) >= list1.get(i)) {
                i++;
            } else {
                if (i == 0) {
                    res = Math.min(res, Math.abs(list2.get(j) - list1.get(i)));
                } else {
                    res = Math.min(res, Math.min(Math.abs(list2.get(j) - list1.get(i)), Math.abs(list2.get(j) - list1.get(i - 1))));
                }
                j++;
            }
        }
        if (i >= list1.size() && j < list2.size()) {
            res = Math.min(res, Math.abs(list2.get(j) - list1.get(i - 1)));
        } else if (i < list1.size() && j >= list2.size()) {
            res = Math.min(res, Math.abs(list2.get(j-1) - list1.get(i)));
        }
        return res;

    }


    /*
    思路2：一次遍历
     */
    public int findClosest2(String[] words, String word1, String word2) {
        boolean f1 = false, f2 = false;
        int i1 = 0, i2 = 0; // 分别记录word1 和 word2 的下标索引
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            if (cur.equals(word1)) {
                i1 = i;
                f1 = true;
            } else if (cur.equals(word2)) {
                i2 = i;
                f2 = true;
            }
            if (f1 && f2) {
                dist = Math.min(dist, Math.abs(i2 - i1));
            }

        }
        return dist;
    }



        @Test
    public void test() {
        String[] words = {"er","fvx","clt","z","ef","cl","t","c","vsf","b","pm","cg","duy","zpr","ph","ji","sgx","mdu","t","aer","g","f","s","cdz","t","a","qtf","h","n","kag","wu","b","kec","c","hb","nfa","mda","a","a","ao","fjf","dw","ve","ty","rn","f","j","fdl","oqt","zt","fv","i","fyk","mhi","hl","my","tfy","nen","w","o","ja","n","yrw","a","am","kd","w","iyx","qua","gq","dsb","cl","g","mbr","zh","szu","px","r","m","x","tbt","rks","q","t","q","ab","yx","w","z","pf","l","ely","nao","uj","krw","r","qvq","sb","gs","y","y","tlp","egm","pko","fy","yl","ew","y","pd","gzy","x","p","e","ato","pj","as","p","bp","v","vb","r","du","dlf","a","rr","x","i","k","v","hok","trp","d","xi","ro","g","yza","v","k","rq","acg","eqd","yqb","xx","rfh","imd","roq","zv","zqa","udh","m","m","psl","d","x","xh","te","tcy","mbv","tmt","v","g","u","t","nf","qg","ea","y","xga","g","o","o","b","cv","t","ij","jhw","zxx","o","w","a","oje","i","c","zhs","uc","j","cu","t","c","efm","y","p","dt","jf","n","zq","rv","a","c","akg","ut","ei","ex","xdo","jq","mhw","quj","fj","edr","wjl","er","bfw","q","ff","ss","q","zr","yqe","jgw","vn","c","n","w","ehu","l","hk","mhl","ej","gs","fwu","jih","b","f","w","rl","kpx","men","lyy","f","d","v","sts","vba","osx","ng","v","ir","jkh","kqk","vz","ued","tno","avk","jnb","s","ky","b","f","e","w","ui","cv","ru","wp","nqa","xue","ku","bs","i","ft","he","gt","k","juh","qn","ndn","l","nw","hzj","wz","ta","ky","yo","z","zul","gm","n","xer","rxu","gcq","p","vra","h","wvt","p","ypu","s","p","nh","r","b","pwh","yb","af","dz","vs","tu","hsq","u","ujm","gau","l","yl","hz","aof","z","maq","vo","v","wye","k","skw","ia","es","vt","nri","s","w","jel","bzb","x","spt","wj","z","so","t","cka","a","zc","dbh","o","ta","s","xe","j","edh","h","osx","a","up","jr","b","m","yb","zw","nzm","vu","e","mjr","jev","qi","h","mf","as","x","u","ogg","qcw","ti","ob","fek","lj","pwq","y","aa","n","t","vtk","dmy","cb","wg","ja","mq","y","j","u","ns","z","jm","ni","s","qjp","zf","aw","wfy","j","gh","s","gv","z","hw","xa","tx","bz","e","p","kg","hb","d","ah","yc","z","wri","nsu","alf","bg","nw","xzc","wx","c","z","oeq","k","s","dhl","l","n","dtg","t","u","gmf","xh","zxb","ujl","u","e","usv","bin","afb","m","eaq","ov","uv","ej","xc","rh","kh","a","uty","kai","nt","bap","i","nr","pmu","yj","m","g","mm","fin","bb","fi","m","cf","a","uq","o","u","w","bq","da","tpq","nmv","s","kka","ccj","so","zyt","t","ks","na","mq","rnj","pd","u","g","n","k","znr","ayf","z","wd","zgt","eis","cno","rr","ayc","kgi","l","l","z","ayn","yiz","a","ij","za","qhp","giz","gmx","zct","me","x","ogo","j","lmd","cse","ovt","ghb","quq","i","tx","bw","ool","vp","p","fec","ak","h","l","pv","n","o","va","l","ssr","r","f","wyj","u","zrp","ee","pm","kik","r","mhw","kxw","i","vy","eyo","nc","m","az","nn","y","r","nz","tuu","csi","juv","nl","nrr","k","gkt","aib","tfa","o","h","t","sv","t","ym","b","ouo","vol","a","qse","jr","qb","mp","z","ro","y","q","g","t","ck","jt","e","gnl","l","oh","psq","qc","c","enx","s","ac","c","x","e","e","c","gw","u","k","hfd","s","mcs","em","t","bo","e","b","o","too","ju","hk","zb","l","i","ng","u","qzr","vn","x","xgs","jzz","qo","v","cp","f","t","nfl","luh","wip","jy","han","pql","bvj","sn","n","pa","bky","v","eer","pi","r","piy","fdq","qa","rkk","kg","jdw","kim","h","ww","pat","bj","eo","f","sru","m","og","pw","hni","no","qac","od","ka","z","y","i","ab","rt","w","yha","gr","zb","ucb","yns","h","b","nam","j","w","nf","k","w","k","nq","cbz","dh","o","rm","sr","z","xw","r","yca","pe","gz","xj","a","a","l","hbl","kl","jt","nc","sox","lb","bj","zjx","gbn","eyl","t","kg","ux","ojn","bd","lo","jwk","bk","odz","co","hi","mou","sf","n","gl","b","ldo","x","irr","l","wmc","kp","dv","qzz","s","w","h","x","db","woo","i","w","jjv","iuv","v","mip","vu","wq","hfq","uor","y","z","lwk","c","nd","pun","oog","av","a","bdz","bp","lgr","jfv","rbt","pb","wpt","ns","d","jph","vlt","m","nyy","frs","we","o","p","foh","qr","apo","aeu","kgz","rt","xka","ua","c","na","exw","m","d","jgs","yo","le","nd","ow","met","ngt","zux","ap","yiv","yyc","zmr","cqv","gbw","fjo","p","q","m","jr","s","nm","qze","i","kv","b","wlm","vg","udm","uw","ku","kh","uv","ege","pj","t","j","f","zwy","dd","f","bvv","dp","hla","pav","du","g","e","s","e","zt","ns","s","cm","hwx","ro","i","l","u","mh","e","ook","mc","r","vo","g","q","ix","ys","og","o","dq","bsz","qwe","efp","xn","an","o","bn","s","df","oqb","x","a","lq","e","hr","cf","d","gde","rpd","xts","u","jc","xhv","fym","um","sr","ffd","ah","dw","t","pv","zey","v","yl","ma","d","zxq","u","t","i","xue","ky","mv","gop","x","s","kvt","yrc","v","qsi","zi","gze","kwl","ekk","f","bkt","i","tjm","jo","f","jkk","eoq","oem","qdo","a","qev","qr","zr","nlm","tgo","u","bb","gu","ak","s","wdh","zzm","mt","pec","n","fza","av","upl","qim","rv","wv","oo","p","ot","qij","q","a","vlh","es","qgs","b","ll","yw","r","i","xc","w","xnh","bjc","ur","yw","oo","npn","e","jar","qp","vxl","z","xq","wil","hh","prd","aa","yk","tw","h","usd","tps","cld","ppa","bu","bdz","j","bu","l","oq","pyi","pvg","t","v"};
        String word1 = "kim", word2 = "ww";
        System.out.println(findClosest(words, word1, word2));

    }
}
