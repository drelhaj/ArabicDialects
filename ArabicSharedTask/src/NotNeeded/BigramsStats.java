package arabic;


	import java.util.*;


import java.io.*;

	/**
	 * Demonstrates simple method for collecting word bigrams.
	 * @author Bob Futrelle, Northeastern U. and bionlp.org
	 * @version  0.1 23 May 2003
	 *
	 * Technique is to tokenize each line and add to hash table,
	 * initting or upping counts as needed.
	 *
	 * Thanks to the SUN API, JavaNut books and Java Cookbook for quick refs
	 * as well as WCPE.org for musical work environment. 
	 *
	 * Bugs: Doesn't handle bigrams across end-of-line.
	 *
	 * PS: I accept Java style critiques, suggestions, etc.  ;-)
	 */
	 
	 public class BigramsStats {
	 
	 	Hashtable<Object, Object> table;
	 	String bigram;
	 	String[] bigramStrings;
	 	int[] bigramCounts;
	 	BufferedReader inFile;
	 	Enumeration<Object> elements, keys;
	 	BigramsCounts[] fullResults;
	 	 	
		static PrintWriter writer;

		
	 	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
			// print map output to file
			String outFile = "E:/workspace/Jordanian/all-BigramFreqNew.txt";
			writer = new PrintWriter(outFile, "UTF-8");
			
	 		BigramsStats stats = new BigramsStats();
	 		stats.run();
	 		
	 	} // main()

		void run() {
		
			openFile();
			collectStats();
			collectResults();
			dumpTable();
			//showAlphaResults();
			showFrequencyResults();
			
			} // run() 
	 
	 /** Hardcoded filename here - this example is on my Powerbook G4.
	 */
	 	void openFile() {
	 		// File is the 2 April 1053 Watson, Crick letter to Nature
	 		try{
	 		inFile = new BufferedReader(new FileReader("E:/workspace/Jordanian/allNew.txt"));
	 		}
	 		catch (Exception e) { System.out.println("fileread prob");}
	 		} // openFile()
	 
		void collectStats() {
		
			table = new Hashtable<Object, Object>();
			String line = "";
			StringTokenizer tokens;
			String token1 = "";
			String token2 = "";
		
			try{
				line = inFile.readLine();
			while(line != null)
				{
				tokens = new StringTokenizer(line.replaceAll("[\\p{P}\\p{Digit}]"," ").replaceAll("[^\\p{InArabic}]+"," ").replaceAll(" +", " ").trim());// only keep arabic text
				if (tokens.hasMoreTokens())
					{token1 = tokens.nextToken();
					}
				while(tokens.hasMoreTokens()) {
					token2 = tokens.nextToken();				
						bigram = token1 + " " + token2;
						Object item = table.get(bigram);
						if (item != null)
							((int[])item)[0]++;
						else {
							int[] count = {1};
							table.put(bigram,count);
							}
							
						token1 = token2; // step forward
						}
				// next
				line = inFile.readLine();
				}
			}
			catch (Exception e) {}
		 
		 System.out.println("Number of bigrams is: " + table.size());
		
		} // collectStats()
		
	/** Local class for bigram count pairs.
	*/	
	 class BigramsCounts{
	 	int count;
	 	String bigram;
	 	
	 	BigramsCounts(int count, String bigram) {
	 		this.count = count;
	 		this.bigram = bigram;
	 		}
	 	}
	 	 	
		void collectResults() {

			keys = table.keys();
			elements = table.elements();
			int index = 0;
			fullResults = new BigramsCounts[table.size()];
					
			while(keys.hasMoreElements()){
				elements.hasMoreElements();
				fullResults[index++] = 
					new BigramsCounts(
					((int[])elements.nextElement())[0],
					(String)keys.nextElement());
				}
			} // collectResults
		
		/** Dumps entire table.
		*/
		void dumpTable() {

			
		} // dumpTable()

		void showAlphaResults() {
		
			Comparator<Object> comp = new Comparator<Object>(){
				public int compare(Object o1, Object o2){
					return (((BigramsCounts)o1).bigram).compareTo(((BigramsCounts)o2).bigram);
					}
			}; // Comparator comp
		
			Arrays.sort(fullResults, comp);
			System.out.println("Results sorted alphabetically:");
			for(int i=0; i < fullResults.length; i++)
				System.out.println(fullResults[i].count + " " + fullResults[i].bigram);
			
		} // showAlphaResults()
		
		void showFrequencyResults() {
		
			Comparator<Object> comp = new Comparator<Object>(){
				public int compare(Object o1, Object o2){
					int i1 = ((BigramsCounts)o1).count;
					int i2 = ((BigramsCounts)o2).count;
					if(i1 == i2) return 0;
					return ((i1 > i2) ? -1 : +1);
					}				
			}; // Comparator comp
		
			Arrays.sort(fullResults, comp);
			System.out.println("Results sorted by bigram count:");
			for(int i=0; i < fullResults.length; i++){
				System.out.println(fullResults[i].count + "\t" + fullResults[i].bigram);
			writer.println(fullResults[i].count + "\t" + fullResults[i].bigram);
			writer.flush();
			}
			writer.close();

		} // showFrequencyResults()

	} // class SimpleBigramStats
