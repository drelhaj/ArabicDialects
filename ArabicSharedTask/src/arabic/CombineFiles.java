
package arabic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

/**
 * The class merge separate text files into one large .txt file. This combines
 * files in a single directory if you want to combine file for multiple
 * directories use CombineTrainingFiles class
 * 
 * @author Dr Mahmoud El-Haj @ Lancaster University
 *
 */
public class CombineFiles {

	static String content = ""; // this variable holds the textual contents of
								// each file.

	public static void main(String args[]) throws IOException {

		String dialect = "EGY"; // this is the name of the created large file (I named them after dialects but you are free to change it), it's also the name of the subdriectory in the dirFrom below.
		String dirFrom = "Training\\TrainingPlain" + File.separator + dialect;// directory to where your separate text files are. In my case I'm merging all Egyptian dialect text files into one large EGY.txt.
		String dirTo = "Training\\TrainingPlain"; // directory to where you want the large file saved to.

		mergeFiles(dialect, dirFrom, dirTo);
	}

	public static void mergeFiles(String dialect, String dirFrom, String dirTo) throws IOException {

		File file2 = new File(dirTo);
		file2.mkdir();

		PrintWriter out = new PrintWriter(dirTo + File.separator + dialect + ".txt");

		File folder = new File(dirFrom);
		File[] listOfFiles = folder.listFiles();

		// loops through each text file in dirFrom adding the content of each
		// file to the out file.
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile()) {

				content = FileUtils.readFileToString(file);

				out.println(content);
				out.flush();
			}
		}
		out.flush();
		out.close();

	}

}
