/**
 * ARFF File Creator
 */
package classifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
//import org.project.osman.process.OsmanReadability;

import Osman.OsmanReadability;

/**
 * Class to create ARFF files by reading textual data from a directory where
 * each sub directory is a class name (e.g. EGY) containing .txt files. It
 * is flexible to accept as many classes as needed.
 * The created ARFF file will be saved to the arff directory.
 * 
 * This need to be done to both Training and Testing data
 * 
 * @author Dr Mahmoud El-Haj @ Lancaster University
 *
 */
public class ARFFCreator {

	static PrintWriter writer;
	static OsmanReadability osman = new OsmanReadability();
	public static String[] UniqueMSA, UniqueDialect , Adverbials, Adverbs, ConjSub, Conjunctions, 
	Demonstratives, Modals, Negation, Particles, Prepositionals, 
	Prepositions, Pronouns, Quantitives, QuestionWords, Relatives, 
	stopwords, tags, EGY_SUB, GLF_SUB, NOR_SUB, LAV_SUB;

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		osman.loadData();
		UniqueMSA = readLines("Features/UniqueMSA.txt");//msa bivalency removed (removed any MSA words used in any of the other dialects)
		UniqueDialect = readLines("Features/UniqueDialects.txt");//dialect bivalency removed (remove bivalent words across dialects)
		Adverbials = readLines("Features/Adverbials.txt");//grammatical feature
		Adverbs = readLines("Features/Adverbs.txt");//grammatical feature
		ConjSub = readLines("Features/ConjSub.txt");//grammatical feature
		Conjunctions = readLines("Features/Conjunctions.txt");//grammatical feature
		Demonstratives = readLines("Features/Demonstratives.txt");//grammatical feature
		Modals = readLines("Features/Modals.txt");//grammatical feature
		Negation = readLines("Features/Negation.txt");//grammatical feature
		Particles = readLines("Features/Particles.txt");//grammatical feature
		Prepositionals = readLines("Features/Prepositionals.txt");//grammatical feature
		Prepositions = readLines("Features/Prepositions.txt");//grammatical feature
		Pronouns = readLines("Features/Pronouns.txt");//grammatical feature
		Quantitives = readLines("Features/Quantitives.txt");//grammatical feature
		QuestionWords = readLines("Features/QuestionWords.txt");//grammatical feature
		Relatives = readLines("Features/Relatives.txt");//grammatical feature
		stopwords = readLines("Features/stopwords.txt");
		tags = readLines("Features/QuestionWords.txt");//grammatical feature
		EGY_SUB = readLines("Features/SUB_Lists/EGY_SUB.txt");// SUB feature
		GLF_SUB = readLines("Features/SUB_Lists/GLF_SUB.txt");// SUB feature
		NOR_SUB = readLines("Features/SUB_Lists/NOR_SUB.txt");// SUB feature
		LAV_SUB = readLines("Features/SUB_Lists/LAV_SUB.txt");// SUB feature
		
		
		String arffDirectory = "arff";//where you want to save the ARFF File
		File arffDir = new File("arff");
		arffDir.mkdir();
		
		
		String datasetDirectory = "Training\\TrainingPOST";//where your dataset is (should contain sub-directories for each class 
														  //(label or attribute). And it need to be Part of Spech tagged using Stanford POStagger for Arabic.
														//You'll have to repeat this for your testing data Testing\\TestingPOST
		
		String[] classes = getClassNames(datasetDirectory);//get the classes (@attribute) names by simply reading the sub-directory names.
		System.out.println(Arrays.toString(classes));
		createArffHeader(arffDirectory+"/LRECTrainingData.arff", Arrays.toString(classes).trim(),"text String");//method to create the ARFF file, the third argument is the datatype you may need to change this manually at some point (e.g. "String, String, Int, Int")

		for (int x = 0; x < classes.length; x++) {
			System.out.println(classes[x]);
			String[] classData = readFiles(datasetDirectory, classes[x]);
			clean(classData);
			printToARFF(classData);
		}

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
		writer.println("@ATTRIBUTE UniqueMSACount NUMERIC");
		writer.println("@ATTRIBUTE UniqueDialectCount NUMERIC"); 
		writer.println("@ATTRIBUTE AdverbialsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE AdverbsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE ConjSubCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE ConjunctionsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE DemonstrativesCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE ModalsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE NegationCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE ParticlesCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE PrepositionalsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE PrepositionsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE PronounsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE QuantitivesCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE QuestionWordsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE RelativesCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE stopwordsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE tagsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE UniquetagsCount NUMERIC");//Grammatical feature
		writer.println("@ATTRIBUTE typeToken NUMERIC");// Stylistic feature
		writer.println("@ATTRIBUTE readability NUMERIC");// Stylistic feature
		writer.println("@ATTRIBUTE EGY_SUB NUMERIC");// SUB feature
		writer.println("@ATTRIBUTE GLF_SUB NUMERIC");// SUB feature
		writer.println("@ATTRIBUTE NOR_SUB NUMERIC");// SUB feature
		writer.println("@ATTRIBUTE LAV_SUB NUMERIC");// SUB feature


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
	public static String[] readFiles(String classDirectory, String className) throws IOException, InterruptedException {


		File folder = new File(classDirectory + File.separator + className);
		File[] listOfFiles = folder.listFiles();
		String[] lines = new String[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];

			if (file.isFile() && file.getName().endsWith(".txt") ||  file.getName().endsWith(".post")) {
				String content = FileUtils.readFileToString(file);
				

					int UniqueMSACount = countKeywords(content.trim(), UniqueMSA);
					int UniqueDialectCount = countKeywords(content.trim(), UniqueDialect);
					int AdverbialsCount = countKeywords(content.trim(), Adverbials);
					int AdverbsCount = countKeywords(content.trim(), Adverbs);
					int ConjSubCount = countKeywords(content.trim(), ConjSub);
					int ConjunctionsCount = countKeywords(content.trim(), Conjunctions);
					int DemonstrativesCount = countKeywords(content.trim(), Demonstratives);
					int ModalsCount = countKeywords(content.trim(), Modals);
					int NegationCount = countKeywords(content.trim(), Negation);
					int ParticlesCount = countKeywords(content.trim(), Particles);
					int PrepositionalsCount = countKeywords(content.trim(), Prepositionals);
					int PrepositionsCount = countKeywords(content.trim(), Prepositions);
					int PronounsCount = countKeywords(content.trim(), Pronouns);
					int QuantitivesCount = countKeywords(content.trim(), Quantitives);
					int QuestionWordsCount = countKeywords(content.trim(), QuestionWords);
					int RelativesCount = countKeywords(content.trim(), Relatives);					
					int stopwordsCount = countKeywords(content.trim(), stopwords);
					int tagsCount = countKeywords(content.trim(), tags);
					int UniquetagsCount = countUniqueTags(content.trim(), tags);
					int distinct = countDistinctWords(content.trim());
					int wordCount = content.trim().split("\\s+").length;
					double typeToken = (double) distinct/(double) wordCount;
					double osmanScore = osman.calculateOsman(content.trim());
					
					
					int EGY_SUBCount = countKeywords(content.trim(), EGY_SUB);
					int GLF_SUBCount = countKeywords(content.trim(), GLF_SUB);
					int NOR_SUBCount = countKeywords(content.trim(), NOR_SUB);
					int LAV_SUBCount = countKeywords(content.trim(), LAV_SUB);
					
					
					
					lines[i]=className + "," + UniqueMSACount + "," +  UniqueDialectCount + "," + AdverbialsCount+ "," + AdverbsCount+ "," +
					ConjSubCount+ "," + ConjunctionsCount+ "," + DemonstrativesCount+ "," + ModalsCount+ "," + NegationCount+ "," + ParticlesCount+ "," +
					PrepositionalsCount+ "," + PrepositionsCount+ "," + PronounsCount+ "," + QuantitivesCount+ "," +
					QuestionWordsCount+ "," + RelativesCount+ "," + stopwordsCount+ "," + tagsCount+ "," + UniquetagsCount+ "," + typeToken + "," + osmanScore + "," + 
					EGY_SUBCount+ "," + GLF_SUBCount+ "," + NOR_SUBCount+ "," + LAV_SUBCount;
					
					System.out.println(lines[i]);				
			}
		}
		return lines;
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

	// count frequency of a list
	public static String countFrequency(String text, String[] wordListArray) {

		int matchesCount = 0;
		String tagsCounts = "";

		if (wordListArray != null) {
			if (wordListArray.length > 0) {

				for (int i = 0; i < wordListArray.length; i++) {

					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";

					// System.out.println(patternString);

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
	
	
	// count frequency of a list
	public static int countKeywords(String text, String[] wordListArray) {

		int matchesCount = 0;

		if (wordListArray != null) {
			if (wordListArray.length > 0) {

				for (int i = 0; i < wordListArray.length; i++) {

					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";

					// System.out.println(patternString);

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

	// count frequency of a list
	public static int countUniqueTags(String text, String[] wordListArray) {

		int matchesCount = 0;
		int uniqueCount = 0;

		if (wordListArray != null) {
			if (wordListArray.length > 0) {

				for (int i = 0; i < wordListArray.length; i++) {

					String patternString = "\\b(" + wordListArray[i].trim() + ")\\b";

					// System.out.println(patternString);

					Pattern pattern = Pattern.compile(patternString);
					Matcher matcher = pattern.matcher(text);

					while (matcher.find()) {
						++matchesCount;
					}

					if (matchesCount > 0)
						matchesCount = 1;

					uniqueCount += matchesCount;
					matchesCount = 0;
				}
			}
		}

		return uniqueCount;

	}
	
	// read text file contents into string array
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

	
	// Count unique words
	public static int countDistinctWords(String str) {
		Set<String> noOWoInString = new HashSet<String>();
		String[] words = str.split(" ");
		// noOWoInString.addAll(words);
		for (String wrd : words) {
			noOWoInString.add(wrd);
		}
		return noOWoInString.size();
	}

}
