package arabic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubtractLists {

	static String dirTo = "E:\\arabicSharedTaskGit\\ArabicSharedTask\\all";

	
	public static void main(String[] args) throws IOException{
		String dialect = "DIALECTS";
		String[] dialectArray = readLines("E:\\arabicSharedTaskGit\\ArabicSharedTask\\all\\all-"+dialect+"_FreqList2.txt");
		String[] dialectArray2 = readLines("E:\\arabicSharedTaskGit\\ArabicSharedTask\\all\\all-"+dialect+"_FreqList2.txt");

		String[] MSAArray = readLines("E:\\arabicSharedTaskGit\\ArabicSharedTask\\all\\all-MSA_FreqList2.txt");

		PrintWriter out = new PrintWriter(dirTo + File.separator + "all"+dialect+"_minusMSA.txt");

		
    final List<String> list =  new ArrayList<String>();
    Collections.addAll(list, MSAArray); 
    for(int i=0;i<dialectArray.length;i++){
    list.remove(dialectArray[i]);
    }
    MSAArray = list.toArray(new String[list.size()]);
    
    for(int i=0;i<MSAArray.length;i++){
    out.println(MSAArray[i]);
    out.flush();
    }
    out.flush();
    out.close();

/*	PrintWriter out2 = new PrintWriter(dirTo + File.separator + "all"+dialect+"_dialectal_MSA.txt");

    
    final List<String> list1 =  new ArrayList<String>();
    Collections.addAll(list1, dialectArray2); 
    for(int i=0;i<dialectArray.length;i++){
    list1.remove(dialectArray[i]);
    }
    dialectArray2 = list1.toArray(new String[list1.size()]);
    
    for(int i=0;i<dialectArray2.length;i++){
    	out2.println(dialectArray2[i]);
    	out2.flush();
    }
    out2.flush();
    out2.close();
     */  	
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
