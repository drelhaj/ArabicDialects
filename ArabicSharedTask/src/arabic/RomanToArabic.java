package arabic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RomanToArabic {

	static PrintWriter writer;
	static int egyCounter = 0,glfCounter = 0,lavCounter = 0,msaCounter = 0,norCounter = 0;
	public static void main(String[] args) throws IOException{
		
		createDirectories();

		String text = new String(Files.readAllBytes(Paths.get("task2-train.txt")), StandardCharsets.UTF_8);
		//System.out.println(text);
		String numericLabels = keepLabels(text);
		String textArabic = convertToArabic(numericLabels);
		//System.out.println(textArabic);
		
		String[] sentences = textArabic.split("\n");
		
		for(String sentence : sentences){
			System.out.println(sentence);
			if(sentence.trim().endsWith("1")){
			writer = new PrintWriter("EGY"+File.separator+(egyCounter++)+".txt", "UTF-8");
			writer.print(sentence.replace("\tQ", "").replace("\t1", "").trim());
			writer.flush();
			}
			if(sentence.trim().endsWith("2")){
			writer = new PrintWriter("GLF"+File.separator+(glfCounter++)+".txt", "UTF-8");			
			writer.print(sentence.replace("\tQ", "").replace("\t2", "").trim());
			writer.flush();
			}
			if(sentence.trim().endsWith("3")){
			writer = new PrintWriter("LAV"+File.separator+(lavCounter++)+".txt", "UTF-8");
			writer.print(sentence.replace("\tQ", "").replace("\t3", "").trim());
			writer.flush();
			}
			if(sentence.trim().endsWith("4")){
			writer = new PrintWriter("MSA"+File.separator+(msaCounter++)+".txt", "UTF-8");
			writer.print(sentence.replace("\tQ", "").replace("\t4", "").trim());
			writer.flush();
			}
			if(sentence.trim().endsWith("5")){
			writer = new PrintWriter("NOR"+File.separator+(norCounter++)+".txt", "UTF-8");
			writer.print(sentence.replace("\tQ", "").replace("\t5", "").trim());
			writer.flush();
			}
		}
	}
	
	
	
	public static void createDirectories(){
		
		/*1	EGY
		2	GLF
		3	LAV
		4	MSA
		5	NOR*/

		File egy = new File("EGY");
		egy.mkdir();
		File glf = new File("GLF");
		glf.mkdir();
		File lav= new File("LAV");
		lav.mkdir();
		File msa = new File("MSA");
		msa.mkdir();
		File nor = new File("NOR");
		nor.mkdir();
	}
	
	public static String keepLabels(String text){
		String updatedLabels="";
	updatedLabels = text
			.replace("EGY", "1")
			.replace("GLF", "2")
			.replace("LAV", "3")
			.replace("MSA", "4")
			.replace("NOR", "5");
		
		return updatedLabels;
	}
	
	
	
	
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
}
