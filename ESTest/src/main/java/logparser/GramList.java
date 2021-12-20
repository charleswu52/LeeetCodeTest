package logparser;

import javafx.util.Pair;
import logdelta.bean.BitSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/12/20 上午10:53
 */
public class GramList {
    private Map<Integer, List<Pair<Integer, BitSet>>> listMap;

    public GramList() {
        this.listMap = new HashMap<>();
    }

    public Map<Integer, List<Pair<Integer, BitSet>>> getListMap() {
        return listMap;
    }

    public void setListMap(Map<Integer, List<Pair<Integer, BitSet>>> listMap) {
        this.listMap = listMap;
    }

    @Override
    public String toString() {
        return "GramList{" +
                "listMap=" + listMap +
                '}';
    }
}
