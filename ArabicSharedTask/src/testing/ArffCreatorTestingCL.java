/**
 * ARFF File Creator
 */
package testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class to create ARFF files by reading textual data from a directory where
 * each sub directory is a class name (e.g. Positive) containing .txt files. It
 * is flexible to accept as many classes as needed.
 * 
 * @author Mahmoud El-Haj @ Lancaster University
 *
 */
public class ArffCreatorTestingCL {

	static PrintWriter writer;
	static int counter = 0;

	public static void runArffCreator(String[] sentences) throws IOException, InterruptedException{
		
		//osman.loadData();
		
		String arffDirectory = "arff";//where you want to save the ARFF File
		File arffDir = new File("arff");
		arffDir.mkdir();
		
		createArffHeader(arffDirectory+"/ArffCompetetionCL.arff", "EGY, GLF, LAV, MSA, NOR","text String");//method to create the ARFF file, the third argument is the datatype you may need to change this manually at some point (e.g. "String, String, Int, Int")

		String[] instancesFeatures = readFiles(sentences);
		clean(instancesFeatures);

		printToARFF(instancesFeatures);


	writer.close();
	
		
	}
	
	
	
	/**
	 * print files contents and assign class (attribute) names to each line
	 * assuming directory names are the labels (e.g. put positive reviews in a
	 * directory called Positive and another for Negatives)
	 * 
	 * @param lines
	 */
	public static void printToARFF(String[] lines) {

		for (int x = 0; x < lines.length; x++) {
			writer.println(lines[x]);
			writer.flush();
		}

	}

	/**
	 * create ARFF File and its headers (usually called once)
	 * 
	 * @param arffFile
	 * @throws FileNotFoundException
	 */
	public static void createArffHeader(String arffFile, String classes, String classesTypes)
			throws FileNotFoundException {
		writer = new PrintWriter(arffFile);
		
		writer.println("@relation textClassifier");
		writer.print("\n");
		writer.println("@ATTRIBUTE class {"+classes.replace("[", "").replace("]", "").trim()+"}");
		writer.println("@ATTRIBUTE pureMSA NUMERIC");
		writer.println("@ATTRIBUTE pureEGY NUMERIC");
		writer.println("@ATTRIBUTE pureGLF NUMERIC");
		writer.println("@ATTRIBUTE pureLAV NUMERIC");
		writer.println("@ATTRIBUTE pureNOR NUMERIC");
		writer.println("@ATTRIBUTE MSAEGY NUMERIC");
		writer.println("@ATTRIBUTE MSAGLF NUMERIC");
		writer.println("@ATTRIBUTE MSALAV NUMERIC");
		writer.println("@ATTRIBUTE MSANOR NUMERIC");
		writer.print("\n");
		writer.println("@data");
		writer.flush();
	}
	
	
	
	/**
	 * get names of sub-directories (attributes/classes names). I treat each
	 * sub-directory as a class name (make sure no other sub directories
	 * presented) this makes the code flexible to accept more than 2 classes
	 * todo: isDirectory
	 * 
	 * @param datasetDir
	 * @return
	 */
	public static String[] getClassNames(String datasetDir) {
		String[] subdirectories = new File(datasetDir).list();
		return subdirectories;

	}
	
	
	/**
	 * get text files from classes directories and write contents to arff file
	 * 
	 * @param classDirectory
	 * @param className
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static String[] readFiles(String[] instances) throws IOException, InterruptedException {

		String[] pureMSA = readLines("keywords/allDIALECTS_minusMSA.txt");
		String[] pureEGY = readLines("keywords/allEGY_minusMSA.txt");
		String[] pureGLF = readLines("keywords/allGLF_minusMSA.txt");
		String[] pureLAV = readLines("keywords/allLAV_minusMSA.txt");
		String[] pureNOR = readLines("keywords/allNOR_minusMSA.txt");
		String[] MSAEGY = readLines("keywords/allEGY_dialectal_MSA.txt");
		String[] MSAGLF = readLines("keywords/allGLF_dialectal_MSA.txt");
		String[] MSALAV = readLines("keywords/allLAV_dialectal_MSA.txt");
		String[] MSANOR = readLines("keywords/allNOR_dialectal_MSA.txt");
		



		for (int i = 0; i < instances.length; i++) {

				//int wordCount = instances[i].trim().split("\\s+").length;

					
					int pureMSACount = countKeywords(instances[i], pureMSA);
					int pureEGYCount = countKeywords(instances[i], pureEGY);
					int pureGLFCount = countKeywords(instances[i], pureGLF);
					int pureLAVCount = countKeywords(instances[i], pureLAV);
					int pureNORCount = countKeywords(instances[i], pureNOR);
					int MSAEGYCount = countKeywords(instances[i], MSAEGY);
					int MSAGLFCount = countKeywords(instances[i], MSAGLF);
					int MSALAVCount = countKeywords(instances[i], MSALAV);
					int MSANORCount = countKeywords(instances[i], MSANOR);


					
					instances[i]="?" 
							+ "," + pureMSACount + "," + pureEGYCount 
							+ "," + pureGLFCount + "," + pureLAVCount + "," + pureNORCount
							+ "," + MSAEGYCount + "," + MSAGLFCount + "," + MSALAVCount + "," + MSANORCount ;

					System.out.println(++counter + " -> " +instances[i]);
					

			
		}
		return instances;
	}
	

	   
/**
* remove null entries from array		
* @param v
* @return
*/
public static String[] clean(final String[] v) {
 List<String> list = new ArrayList<String>(Arrays.asList(v));
 list.removeAll(Collections.singleton(null));
 return list.toArray(new String[list.size()]);
}


	//count frequency of a list
	public static String countFrequency(String text, String[] wordListArray){

		
		int matchesCount = 0;
		String tagsCounts = "";
				
				if(wordListArray!=null){
				if(wordListArray.length>0){

				for(int i=0;i<wordListArray.length;i++){
					
					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";
					
					//System.out.println(patternString);

					Pattern pattern = Pattern.compile(patternString);
					Matcher matcher = pattern.matcher(text);
					

					while (matcher.find()) {
						++matchesCount;
						}
					tagsCounts += "," + Integer.toString(matchesCount);
					matchesCount = 0;
				}
				}
				}
				

				
			  return tagsCounts;
			  
		}
	
	
	//count frequency of a list
	public static int countKeywords(String text, String[] wordListArray){

		
		int matchesCount = 0;
				
				if(wordListArray!=null){
				if(wordListArray.length>0){

				for(int i=0;i<wordListArray.length;i++){
					
					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";
					
					//System.out.println(patternString);

					Pattern pattern = Pattern.compile(patternString);
					Matcher matcher = pattern.matcher(text);
					

					while (matcher.find()) {
						++matchesCount;
						}
				}
				}
				}
				

				
			  return matchesCount;
			  
		}

	//count frequency of a list
	public static int countUniqueTags(String text, String[] wordListArray){

		
		int matchesCount = 0;
		int uniqueCount = 0;
				
				if(wordListArray!=null){
				if(wordListArray.length>0){

				for(int i=0;i<wordListArray.length;i++){
					
					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";
					
					//System.out.println(patternString);

					Pattern pattern = Pattern.compile(patternString);
					Matcher matcher = pattern.matcher(text);
					

					while (matcher.find()) {
						++matchesCount;
						}
					
					if(matchesCount>0)
						matchesCount = 1;
					
					uniqueCount += matchesCount;
					matchesCount = 0;
				}
				}
				}
				

				
			  return uniqueCount;
			  
		}

	
	
	//read text file contents into string array
	public static String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
	}
	
}
