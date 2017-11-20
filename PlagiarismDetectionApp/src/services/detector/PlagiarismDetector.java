package services.detector;

import java.util.*;

import org.eclipse.jdt.core.dom.*;


/**
 * Created by Aditya on 11/12/2017.
 * Class Purpose: This class implements IPlagiarismDetector interface
 */
public class PlagiarismDetector implements IPlagiarismDetector {
    @Override
    // This method is called by the ReadTwoDirs and ReadTwoFiles class
    // Input : String Representation of the java files the needs comparison
    // Output: List Of Similar Nodes (Where each similar node contains info of  node from file 1 which is
    //         similar to node from file 2
    public void runPlagiarismDetection(String file1, String file2) {

        HashMap<ASTNode, NodeInfo> fileNodeMap1 = parseFile(file1);
        HashMap<ASTNode, NodeInfo> fileNodeMap2 = parseFile(file2);

        HashMap<ASTNode, NodeInfo> filteredNodeMap1 = filterLeafNodes(fileNodeMap1);
        HashMap<ASTNode, NodeInfo> filteredNodeMap2 = filterLeafNodes(fileNodeMap2);
        this._result.originNodeNum = filteredNodeMap1.size();
        this._result.suspectedNodeNum = filteredNodeMap2.size();
        List<SimilarNode> result = astCCImplmentation(filteredNodeMap1, filteredNodeMap2);
        this._result.similarNodeNum = result.size();
        for (SimilarNode similarNode : result) {
            this._result.addSimilarNode(similarNode);
        }
    }

    private HashMap<ASTNode, NodeInfo> filterLeafNodes(HashMap<ASTNode, NodeInfo> inputMap) {
        HashMap<ASTNode, NodeInfo> outputMap = new HashMap<ASTNode, NodeInfo>();
        for (Map.Entry<ASTNode, NodeInfo> entry : inputMap.entrySet()) {
            if (entry.getValue().subNodes != 0) {
                outputMap.put(entry.getKey(), entry.getValue());
            }
        }
        return outputMap;
    }

    // Input: Hashmap<ASTNode,NodeInfo>
    // Output : TreeMap<Integer,List<NodeInfo>>
    // this method extracts the nodeInfo from each entry in the input map and groups them by the node's no of subnodes
    // And returns a TreeMap where the key is the number of subnodes and value is the list of all nodeInfos which have the same
    // number of subnodes. The tree map is sorted in reverse order so the first entry in the treemap is the node with
    // maximum subnodes.
    private TreeMap<Integer, List<NodeInfo>> convertToTreeMap(HashMap<ASTNode, NodeInfo> hashMap) {
        TreeMap<Integer, List<NodeInfo>> map = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<ASTNode, NodeInfo> e : hashMap.entrySet()) {
            NodeInfo nodeInfo = e.getValue();
            if (map.containsKey(nodeInfo.subNodes)) {
                List<NodeInfo> list = map.get(nodeInfo.subNodes);
                list.add(nodeInfo);

            } else {

                // pick only no leaf nodes
                if (nodeInfo.subNodes > 0) {
                    List<NodeInfo> list = new ArrayList<>();
                    list.add(nodeInfo);
                    map.put(nodeInfo.subNodes, list);
                }
            }

        }
        return map;
    }

    // Input: Set of integers
    // Output: an integer array which contains all the elements in the input set
    private int[] getArray(Set<Integer> set) {
        int n = set.size();
        int[] array = new int[n];

        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    // Input : 2 Hashmaps which contain the node information from the two java files that needs comparison
    // Output: A List of similar nodes from both the files
    // This method is the implementation of the ASTCC Algorithm discussed in the following paper
    // http://ieeexplore.ieee.org/document/7424821/

    private List<SimilarNode> astCCImplmentation(HashMap<ASTNode, NodeInfo> fileNodeMap1, HashMap<ASTNode, NodeInfo> fileNodeMap2) {
        TreeMap<Integer, List<NodeInfo>> treeMap1 = convertToTreeMap(fileNodeMap1);
        TreeMap<Integer, List<NodeInfo>> treeMap2 = convertToTreeMap(fileNodeMap2);
        List<SimilarNode> result = new ArrayList<>();
        int[] array1 = getArray(treeMap1.keySet());
        int[] array2 = getArray(treeMap2.keySet());
        int i = 0;
        int j = 0;
        int sum = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] == array2[j]) {
                // obtain list of nodeInfos for both and compare them if match found then add it to report
                List<NodeInfo> list1 = treeMap1.get(array1[i]);
                List<NodeInfo> list2 = treeMap2.get(array2[j]);
                for (NodeInfo nodeInfo1 : list1) {
                    for (NodeInfo nodeInfo2 : list2) {
                        if ((nodeInfo1.hashValue == nodeInfo2.hashValue)  && (nodeInfo1.nodeType == nodeInfo2.nodeType)) {
                            result.add(new SimilarNode(nodeInfo1, nodeInfo2));

                        }
                    }
                }
                i++;
                j++;

            } else if (array1[i] > array2[j]) {
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            }
        }
        return result;
    }

    // Input : String representation of the java file
    // Output: HashMap containing node information of the nodes in the java file
    // This file takes the input and generates an AST Tree for the input file
    // Uses the visitor design pattern to collect information of all the nodes in the AST tree
    // The ASTStatsCollector objects vists all the nodes and collects the information in the Hashmap
    // which is finally returned.
    private HashMap<ASTNode, NodeInfo> parseFile(String file) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(file.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        ASTStatsCollector astStatsCollector = new ASTStatsCollector(cu);
        cu.accept(astStatsCollector);


        return astStatsCollector.nodeSubNodeMap;
    }

    @Override
    public Report getResult() {
        return this._result;
    }
    //read file to string

    private Report _result = new Report();
}
