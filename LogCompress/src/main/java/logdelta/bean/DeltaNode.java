package logdelta.bean;

import java.util.Objects;

/**
 * @author WuChao
 * @create 2021/12/19 下午12:35
 */
public class DeltaNode implements Comparable<DeltaNode> {
    // 定义搜索时使用的节点
    private int deltaID;
    private int position;
    private int type;
    private int value;

    public DeltaNode() {
    }

    public DeltaNode(int deltaID, int position, int type, int value) {
        this.deltaID = deltaID;
        this.position = position;
        this.type = type;
        this.value = value;
    }

    public int getDeltaID() {
        return deltaID;
    }

    public void setDeltaID(int deltaID) {
        this.deltaID = deltaID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeltaNode deltaNode = (DeltaNode) o;
        return deltaID == deltaNode.deltaID && position == deltaNode.position && type == deltaNode.type && value == deltaNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaID, position, type, value);
    }

    @Override
    public String toString() {
        return "DeltaNode{" +
                "deltaID=" + deltaID +
                ", position=" + position +
                ", type=" + type +
                ", value=" + value +
                '}';
    }

    // 按照 DeltaNode 的value 降序排列
    @Override
    public int compareTo(DeltaNode o) {
        return o.getValue() - this.getValue();
    }
}
