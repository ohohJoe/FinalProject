package services.detector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTwoDirs extends ReadFile {
    ArrayList originalFileData=new ArrayList(  );
    ArrayList suspectedFileData=new ArrayList(  );

    public ReadTwoDirs(String dir1,String dir2){
            this.ParseFilesInDir(originalFileData, dir1 );
            this.ParseFilesInDir( suspectedFileData,dir2 );
    }

    private void ParseFilesInDir(ArrayList fileDataList,String dirPath) {
        File root = new File(dirPath);
        File[] files = root.listFiles ( );
        String filePath = null;
        for (File f : files ) {
            filePath = f.getAbsolutePath();
            if(f.isFile()){
                fileDataList.add( this.readFileToString( filePath ) );
            }
        }
    }

    @Override
    public Report callPlagiarismDetector() {
        System.out.print(this.originalFileData);
        return null;
    }
}
