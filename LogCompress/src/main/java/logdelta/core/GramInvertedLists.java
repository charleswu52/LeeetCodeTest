package logdelta.core;

import javafx.util.Pair;
import logdelta.bean.BitSet;
import logdelta.bean.Node;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/12/19 下午1:44
 */
public class GramInvertedLists {
    private Map<Integer, List<Pair<Integer, BitSet>>> gramListMap;

    public GramInvertedLists() {
        this.gramListMap = new HashMap<>();
    }


    public void show() {
        Iterator<Map.Entry<Integer, List<Pair<Integer, BitSet>>>> iterator = gramListMap.entrySet().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Pair<Integer, BitSet>>> next = iterator.next();
            Integer key = next.getKey();
            count++;
            System.out.println(count + " " + key + ": ");
            int size = next.getValue().size();
            for (int i = 0; i < size; i++) {
                System.out.print(next.getValue().get(i).getKey() + "->"
                        + next.getValue().get(i).getValue() + ",");
            }
            System.out.println();
        }
    }

    public void addGramStringPos(int gramCode, Pair<Integer, BitSet> pair) {
        if (!this.gramListMap.containsKey(gramCode)) {
            List<Pair<Integer, BitSet>> array = new ArrayList<>();
            array.add(pair);
            this.gramListMap.put(gramCode, array);
        } else {
            if (pair.getValue().empty()) {
                this.gramListMap.get(gramCode).add(pair);
            } else {
                gramListMap.get(gramCode).add(0, pair);
            }
        }
    }

    public void buildGramInvertedLists(String s, int gramLength, List<List<Node>> deltas) {
        if (s.isEmpty() || s.length() < gramLength) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < gramLength; i++) {
            stringBuffer.append('N');
        }
        int deltaNum = deltas.size();
        int gramNum = s.length() - gramLength + 1;
        List<BitSet> bitSetList = new ArrayList<>();
        for (int i = 0; i < gramNum; i++) {
            BitSet bitSet = new BitSet();
            bitSetList.add(bitSet);
        }
        for (int i = 0; i < deltas.size(); i++) {
            for (int j = 0; j < deltas.get(i).size(); j++) {
                bitSetList.get(deltas.get(i).get(j).getmLow() - gramLength).set(2 * i);
                if (deltas.get(i).get(j).getmHigh() + 1 < gramNum) {
                    bitSetList.get(deltas.get(i).get(j).getmHigh() + 1).set(2 * i + 1);
                }
            }
        }
        for (int i = 0; i < s.length() - gramLength + 1; i++) {
            String str = s.substring(i, gramLength);
            if (!str.equals(stringBuffer.toString())) {
                addGramStringPos(str.hashCode(), new Pair<>(i, bitSetList.get(i)));
            }
        }
        addHeadTailCount(deltaNum);

    }

    public void addHeadTailCount(int deltaNumber) {
        Iterator<Map.Entry<Integer, List<Pair<Integer, BitSet>>>> iterator = gramListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Pair<Integer, BitSet>>> next = iterator.next();
            Integer key = next.getKey();
            List<Pair<Integer, BitSet>> array = next.getValue();
            if (array == null) {
                array = new ArrayList<>();
            }
            int head = 0, tail = 0;
            int deltaID = 0;
            for(int i = 0; i < array.size(); i++){
                for(deltaID = 0; deltaID < deltaNumber; deltaID++){
                    if(array.get(i).getValue().test(deltaID * 2 + 1))
                        head++;
                    if(array.get(i).getValue().test(deltaID * 2))
                        tail++;
                }
            }
            array.add(new Pair<>(head, new BitSet()));
            array.add(new Pair<>(tail, new BitSet()));
        }
    }

    public void showInvertedListSize() {
        int num = 0;
        int arraySizeSum = 0;
        Iterator<Map.Entry<Integer, List<Pair<Integer, BitSet>>>> iterator =
                gramListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            num++;
            arraySizeSum += iterator.next().getValue().size() - 2;
        }
        System.out.println(num + " " + arraySizeSum);
    }

    public List<Pair<Integer, BitSet>> getArray(int gramCode) {
        return gramListMap.getOrDefault(gramCode, null);
    }

    public Map<Integer, List<Pair<Integer, BitSet>>> getGramListMap() {
        return gramListMap;
    }




}
