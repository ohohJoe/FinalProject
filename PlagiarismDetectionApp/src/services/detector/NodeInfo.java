package services.detector;

import java.io.Serializable;

/**
 * Created by Aditya on 11/12/2017.
 * Class Purpose: This class holds statistics related to each node
 */
public class NodeInfo implements Comparable<NodeInfo>{
    // no of subnodes directly under it
    public int subNodes;
    // nodeType + the nodeTypes of nodes direct children
    public int hashValue;
    // start line number of the node
    public int startLineNumber;
    // end line number of the node
    public int endLineNumber;
    // nodes type
    public int nodeType;

    @Override
    public int compareTo(NodeInfo o) {
        if(this.subNodes > o.subNodes){
            return -1;
        }
        if(this.subNodes < o.subNodes){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("No of subnodes " + subNodes);
        sb.append("\n");
        sb.append("hashvalue " + hashValue);
        sb.append("\n");
        sb.append("nodeType " + nodeType);
        sb.append("\n");
        sb.append("startLineNumber " + startLineNumber);
        sb.append("\n");
        sb.append("endLineNumber " + endLineNumber);
        return sb.toString();
    }
}
