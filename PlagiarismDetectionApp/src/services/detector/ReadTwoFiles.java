package services.detector;

import java.io.IOException;
import java.util.List;

//Read file content into a string
public class ReadTwoFiles extends ReadFile {
    String originalFileData;
    String suspectedFileData;
    public ReadTwoFiles(String originalFilePath, String suspectedFilePath){
        this.originalFileData=this.readFileToString(originalFilePath);
        this.suspectedFileData=this.readFileToString(suspectedFilePath);
    }

    @Override
    public Report callPlagiarismDetector() {
        PlagiarismDetector plagiarismDetector = new PlagiarismDetector();
        plagiarismDetector.runPlagiarismDetection(this.originalFileData,this.suspectedFileData);
        return plagiarismDetector.getResult();
    }
}
