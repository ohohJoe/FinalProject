package services.detector;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store analysis result for AST Tree
 */

public class Report {
    // Store common nodes
    List similarNode = new ArrayList();
    // Store overall similiarity
    Integer percentageSimilarity;

    // Set summary similarity
    public void setSummarySimilarity(int percent) {
        this.percentageSimilarity = percent;
    }

    // Get summary similarity
    public long getSummarySimilarity() {
        if (this.similarNodeNum == 0 || this.suspectedNodeNum == 0 || this.originNodeNum == 0) {
            return 0;
        }
        double s = this.similarNodeNum;
        double l = this.originNodeNum - this.similarNodeNum;
        double r = this.suspectedNodeNum - this.similarNodeNum;
        double simliarity = 2.0 * s / (2 * s + l + r);
        // Out put same node number, left tree number, right tree number
        System.out.print(this.similarNodeNum + " " + this.originNodeNum + " " + this.suspectedNodeNum + " ");
        return Math.round(simliarity * 100);
    }

    // Add similar node
    public void addSimilarNode(SimilarNode node) {
        this.similarNode.add(node);
    }

    public int originNodeNum;
    public int suspectedNodeNum;
    public int similarNodeNum;
}
