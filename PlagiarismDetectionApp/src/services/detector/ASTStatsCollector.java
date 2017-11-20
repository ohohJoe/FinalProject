package services.detector;

import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;

/**
 * Created by Aditya on 11/12/2017.
 * Class Purpose: It is the sublcass of the abstract class ASTVisitor and collects
 * Number of subnodes
 * Node's hashvalue
 * Node's start line number
 * Node's end line number
 * for each node
 */
public class ASTStatsCollector extends ASTVisitor {
    public HashMap<ASTNode, NodeInfo> nodeSubNodeMap = new HashMap<>();



    CompilationUnit cu;

    ASTStatsCollector(ASTNode cu) {
        this.cu = (CompilationUnit)cu;
    }

    //helper method which converts the node information into the required type
    private void computeNodeSubNodeCount(ASTNode node) {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.hashValue = node.getNodeType();
        nodeInfo.nodeType = node.getNodeType();
        nodeInfo.subNodes = 0;
        nodeInfo.startLineNumber = cu.getLineNumber(node.getStartPosition());
        nodeInfo.endLineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength());

        nodeSubNodeMap.put(node, nodeInfo);
        if (nodeSubNodeMap.containsKey(node.getParent())) {
            NodeInfo parentNodeInfo = nodeSubNodeMap.get(node.getParent());
            parentNodeInfo.subNodes += 1;
            nodeSubNodeMap.put(node.getParent(), parentNodeInfo);
        }
        ASTNode currentNode = node;
        //Recurseivly update the parents hashvalues (Need to revisit this ,may hit performance)
        while(currentNode.getParent()!=null){
            if(nodeSubNodeMap.containsKey(currentNode.getParent())){
                NodeInfo parentNodeInfo = nodeSubNodeMap.get(currentNode.getParent());
                if(nodeSubNodeMap.containsKey(currentNode)) {
                    parentNodeInfo.hashValue += nodeSubNodeMap.get(currentNode).hashValue;
                }
                nodeSubNodeMap.put(currentNode.getParent(),parentNodeInfo);
            }
            currentNode = currentNode.getParent();
        }
    }

    public boolean visit(MethodDeclaration node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(AssertStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(Block node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(BreakStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ConstructorInvocation node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ContinueStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(DoStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(EmptyStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(EnhancedForStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ExpressionStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ForStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(IfStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(LabeledStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ReturnStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(SuperConstructorInvocation node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(SwitchCase node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(SwitchStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(SynchronizedStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(ThrowStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(TryStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(TypeDeclarationStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }


    public boolean visit(WhileStatement node) {
        computeNodeSubNodeCount(node);
        return true;
    }

    public boolean visit(VariableDeclarationFragment node) {
        computeNodeSubNodeCount(node);
        return true;
    }
    
}
