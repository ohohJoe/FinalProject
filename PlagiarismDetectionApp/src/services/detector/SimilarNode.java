package services.detector;


/**
 * Created by Aditya on 11/12/2017.
 * Class Purpose: This class holds node information for two java files which are similar
 */
public class SimilarNode {
    // Information of the node from file 1
    private NodeInfo nodeInfo1;
    // Information of the node from file 2
    private NodeInfo nodeInfo2;

    SimilarNode(NodeInfo n1,NodeInfo n2){
        this.nodeInfo1 = n1;
        this.nodeInfo2 = n2;
    }
    public NodeInfo getNodeInfo1(){
        return nodeInfo1;
    }
    public NodeInfo getNodeInfo2(){
        return nodeInfo2;
    }
}
