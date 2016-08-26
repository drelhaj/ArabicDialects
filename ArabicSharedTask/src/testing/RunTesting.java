package testing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class RunTesting {

	static MaxentTagger tagger;

	
	
	public static void main(String[] args) throws Exception{
		
		tagger =  new MaxentTagger("stanford-postagger-full-2011-04-20/models/arabic-accurate.tagger");

		
		String text = new String(Files.readAllBytes(Paths.get("testing.txt")), StandardCharsets.UTF_8);
		//System.out.println(text);
		String textArabic = convertToArabic(text);
		//System.out.println(textArabic);
		//System.out.println(textArabic);
		String[] sentences = textArabic.split("\n");
		
		String[] sentPos = new String[sentences.length];
		
		
for(int i = 0; i<sentences.length;i++){
			
	sentPos[i] = tagArbText(sentences[i]);

		//System.out.println(sentPos[i]);
		}

	ArffCreatorTestingCL.runArffCreator(sentences);
	CompetetionClassifier.mainClass();


	}



	
	
	
	//convert Arabizi to Arabic
	public static String convertToArabic(String text){
		String convertedText="";
		
		convertedText = text
				.replace("$","ش")
				.replace("*","ذ")
				.replace("'","ء")
				.replace("|","آ")
				.replace("}","ئ")
				.replace(">","أ")
				.replace("<","إ")
				.replace("A","ا")
				.replace("b","ب")
				.replace("d","د")
				.replace("D","ض")
				.replace("E","ع")
				.replace("f","ف")
				.replace("g","غ")
				.replace("H","ح")
				.replace("h","ه")
				.replace("j","ج")
				.replace("k","ك")
				.replace("l","ل")
				.replace("m","م")
				.replace("n","ن")
				.replace("p","ة")
				.replace("r","ر")
				.replace("s","س")
				.replace("S","ص")
				.replace("t","ت")
				.replace("T","ط")
				.replace("v","ث")
				.replace("w","و")
				.replace("x","خ")
				.replace("Y","ى")
				.replace("y","ي")
				.replace("z","ز")
				.replace("q","ق")
				.replace("Z","ظ")
				.replace("&","ؤ")
				

;
		return convertedText;
	}


	
	public static String tagArbText(String text) throws ClassNotFoundException, IOException{

		String tagged = tagger.tagString(text);
		
		return tagged;
		
	}

}
