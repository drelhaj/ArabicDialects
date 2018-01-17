package arabic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

/**
 * Merge training files for each class in one large .txt file.
 * 
 * @author Dr Mahmoud El-Haj @ Lancaster University
 *
 */
public class CombineTrainingFiles {

	static String content = ""; // this variable holds the textual contents of
								// each file.

	public static void main(String args[]) throws IOException {

		String dirFrom = "Training\\TrainingPlain"; // directory to where your classes
											// with separate text files are.
		String dirTo = "Training\\TrainingPlain"; // directory to where you want the
											// large file saved to.
		String[] classes = { "EGY", "LAV", "NOR", "GLF" };// loops through the
															// files of each
															// dialect (class)
															// and merge each
															// into one large
															// txt file
															// following the
															// class name (e.g.
															// EGY.txt)

		mergeFilesForClasses(dirFrom, dirTo, classes);
	}

	public static void mergeFilesForClasses(String dirFrom, String dirTo, String[] classes) throws IOException {

		File file2 = new File(dirTo);
		file2.mkdir();

		for (int w = 0; w < classes.length; w++) {
			dirFrom = dirFrom + File.separator + classes[w];

			PrintWriter out = new PrintWriter(dirTo + File.separator + classes[w] + ".txt");

			// loops through the text file in each class in the dirFrom adding
			// the content of each file to the out file.
			File folder = new File(dirFrom);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				File file = listOfFiles[i];
				if (file.isFile()) {

					content = FileUtils.readFileToString(file).replaceAll("[a-zA-Z]+", "").replace("/", "").trim()
							.replaceAll(" +", " ");// notice I'm removing
													// English characters and
													// multiple spaces.

					out.println(content);
					out.flush();
				}
			}
			out.flush();

			out.close();
		}
	}

}
