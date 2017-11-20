package services.detector;

import java.util.List;

/**
 * Interface for creating ASTNodes for the given files and calling methods to compare them and get results.
 */

public interface IPlagiarismDetector {
	/**
     * Take the two filePaths and calls the transformFile method on each file
     */
    void runPlagiarismDetection(String filePath1, String filePath2);

    /**
     * Generate ASTNode for the given file
     */
//    ASTNode transforFile(String filePath);

    /**
     * Traverse AST to collect information from ASTNodes
     */
//    Report collectStatistics(ASTNode n1, ASTNode n2);

    /**
     * Get the list of report objects
     */
    Report getResult();
}
