
package arabic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class CombineFiles {
	static String dialect = "LAV";
	static String dirFrom = "E:\\arabicSharedTaskGit\\ArabicSharedTask\\newTestPost\\"+dialect+"_TEST_POST";
	static String dirTo = "E:\\arabicSharedTaskGit\\ArabicSharedTask\\newTestPost";
	
	static int wordCount = 0;
	static String content = "";
	static int fileCount = 0;
	static String fileName = "";
	static String fileNameTmp = "";

	public static void main(String[] args) throws IOException {
		File file2  = new File(dirTo);
		file2.mkdir();
		

		PrintWriter out = new PrintWriter(dirTo + File.separator + "test"+dialect+".txt");

		File folder = new File(dirFrom);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile()) {

				content = FileUtils.readFileToString(file);

				out.println(content.replace("EGY", "?").replace("GLF", "?").replace("LAV", "?").replace("MSA", "?").replace("NOR", "?"));
				out.flush();
			}
		}
		out.flush();

		out.close();

	}

}
