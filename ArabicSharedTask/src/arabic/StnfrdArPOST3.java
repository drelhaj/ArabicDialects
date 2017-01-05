package arabic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class StnfrdArPOST3 {

static PrintWriter w1;
static int counter = 0;
static String folderName = "NOR";
static File dir = new File("E:/git/OsmanReadability/arff/data");
static MaxentTagger tagger;
static String fileName = "";




	public static void main(String[] args) throws ClassNotFoundException, IOException{
		tagger =  new MaxentTagger("E:/workspace/jarfiles/stanford-postagger-full-2011-04-20/models/arabic-accurate.tagger");

		
		String postDIR = "E:/git/OsmanReadability/arff/data";
		new File(postDIR).mkdir();

	
		
		
		if(dir.isDirectory()){
			  for (File child : dir.listFiles()) {
				try{
					fileName = child.getName();
				  if(!(fileName.indexOf("txt")<=0)){
		    String textFile = dir+"\\"+fileName;
		    //test to see if the file is readable (digital PDF)
		    System.out.println(textFile);
		    String text = new String(Files.readAllBytes(Paths.get(textFile)), StandardCharsets.UTF_8);
		    
			String[] sentences = text.split("\n");
			
			w1 = new PrintWriter(postDIR+"\\"+fileName+".post");
			
			for(String sent : sentences){
			String textTagged = tagArbText(sent);
			 w1.println(textTagged);
			 System.out.println(textTagged);
			 w1.flush();
			}
	 }
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			  }
		}
		
	}

	
	public static String tagArbText(String text) throws ClassNotFoundException, IOException{

		String tagged = tagger.tagString(text);
		
		return tagged;
		
	}
}
