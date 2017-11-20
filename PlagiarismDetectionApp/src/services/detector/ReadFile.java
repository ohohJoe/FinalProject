package services.detector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Interface for fetching list of files and call runPlagiarismDetector 
 * to evaluate each pair of files.
 */

public abstract class ReadFile {

	/**
	 * Performs plagiarism detection on the list of files
	 */
    abstract public Report callPlagiarismDetector();

	/**
	 * Read Program Data form file
	 */
	protected String readFileToString(String filepath) {
		StringBuilder fileData = new StringBuilder( 1000 );
		try {
			BufferedReader reader = new BufferedReader( new FileReader( filepath ) );
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read( buf )) != -1) {
				String readData = String.valueOf( buf, 0, numRead );
				fileData.append( readData );
				buf = new char[1024];
			}
			reader.close();

		} catch (IOException e) {
			System.out.println( e.getMessage() );
		}
		return fileData.toString();
	}
}
