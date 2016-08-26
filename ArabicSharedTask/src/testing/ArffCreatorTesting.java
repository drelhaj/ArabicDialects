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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.io.FileUtils;
//import org.project.osman.process.OsmanReadability;

/**
 * Class to create ARFF files by reading textual data from a directory where
 * each sub directory is a class name (e.g. Positive) containing .txt files. It
 * is flexible to accept as many classes as needed.
 * 
 * @author Mahmoud El-Haj @ Lancaster University
 *
 */
public class ArffCreatorTesting {/*

	static PrintWriter writer;
	static OsmanReadability osman = new OsmanReadability();


	public static void runArffCreator(String[] sentences) throws IOException, InterruptedException{
		
		//osman.loadData();
		
		String arffDirectory = "E:/arabicSharedTaskGit/ArabicSharedTask/arff";//where you want to save the ARFF File
		File arffDir = new File("E:/arabicSharedTaskGit/ArabicSharedTask/arff");
		arffDir.mkdir();
		
		createArffHeader(arffDirectory+"/ArffCompetetion.arff", "EGY, GLF, LAV, MSA, NOR","text String");//method to create the ARFF file, the third argument is the datatype you may need to change this manually at some point (e.g. "String, String, Int, Int")

		String[] instancesFeatures = readFiles(sentences);
		clean(instancesFeatures);

		printToARFF(instancesFeatures);


	writer.close();
	
		
	}
	
	
	
	*//**
	 * print files contents and assign class (attribute) names to each line
	 * assuming directory names are the labels (e.g. put positive reviews in a
	 * directory called Positive and another for Negatives)
	 * 
	 * @param lines
	 *//*
	public static void printToARFF(String[] lines) {

		for (int x = 0; x < lines.length; x++) {
			writer.println(lines[x]);
			writer.flush();
		}

	}

	*//**
	 * create ARFF File and its headers (usually called once)
	 * 
	 * @param arffFile
	 * @throws FileNotFoundException
	 *//*
	public static void createArffHeader(String arffFile, String classes, String classesTypes)
			throws FileNotFoundException {
		writer = new PrintWriter(arffFile);
		
		writer.println("@relation textClassifier");
		writer.print("\n");
		writer.println("@ATTRIBUTE class {"+classes.replace("[", "").replace("]", "").trim()+"}");
		writer.println("@ATTRIBUTE uniqueTags  NUMERIC");
		writer.println("@ATTRIBUTE /CD NUMERIC");
		writer.println("@ATTRIBUTE /DT NUMERIC");
		writer.println("@ATTRIBUTE /DTJJ NUMERIC");
		writer.println("@ATTRIBUTE /DTJJR NUMERIC");
		writer.println("@ATTRIBUTE /DTNN NUMERIC");
		writer.println("@ATTRIBUTE /DTNNP NUMERIC");
		writer.println("@ATTRIBUTE /DTNNS NUMERIC");
		writer.println("@ATTRIBUTE /IN NUMERIC");
		writer.println("@ATTRIBUTE /JJ NUMERIC");
		writer.println("@ATTRIBUTE /NN NUMERIC");
		writer.println("@ATTRIBUTE /NNP NUMERIC");
		writer.println("@ATTRIBUTE /NNS NUMERIC");
		writer.println("@ATTRIBUTE /NOUN NUMERIC");
		writer.println("@ATTRIBUTE /PRP NUMERIC");
		writer.println("@ATTRIBUTE /RB NUMERIC");
		writer.println("@ATTRIBUTE /RP NUMERIC");
		writer.println("@ATTRIBUTE /UH NUMERIC");
		writer.println("@ATTRIBUTE /VBD NUMERIC");
		writer.println("@ATTRIBUTE /VBN NUMERIC");
		writer.println("@ATTRIBUTE /VBP NUMERIC");
		writer.println("@ATTRIBUTE /WP NUMERIC");
		writer.println("@ATTRIBUTE /WRB NUMERIC");
		writer.println("@ATTRIBUTE ارتفاع NUMERIC");
		writer.println("@ATTRIBUTE استخدم NUMERIC");
		writer.println("@ATTRIBUTE استعملوا NUMERIC");
		writer.println("@ATTRIBUTE اسمه NUMERIC");
		writer.println("@ATTRIBUTE ال NUMERIC");
		writer.println("@ATTRIBUTE الاتفاق NUMERIC");
		writer.println("@ATTRIBUTE الاجتماع NUMERIC");
		writer.println("@ATTRIBUTE الاحتلال NUMERIC");
		writer.println("@ATTRIBUTE الانتخابات NUMERIC");
		writer.println("@ATTRIBUTE الانتفاضة NUMERIC");
		writer.println("@ATTRIBUTE الإجراءات NUMERIC");
		writer.println("@ATTRIBUTE الإخوان NUMERIC");
		writer.println("@ATTRIBUTE الإسرائيلي NUMERIC");
		writer.println("@ATTRIBUTE الإسرائيلية NUMERIC");
		writer.println("@ATTRIBUTE الإسرائيليين NUMERIC");
		writer.println("@ATTRIBUTE الإسكندرية NUMERIC");
		writer.println("@ATTRIBUTE الإعلام NUMERIC");
		writer.println("@ATTRIBUTE الإعلان NUMERIC");
		writer.println("@ATTRIBUTE الإنتاجية NUMERIC");
		writer.println("@ATTRIBUTE الإنسان NUMERIC");
		writer.println("@ATTRIBUTE الأثناء NUMERIC");
		writer.println("@ATTRIBUTE الأخيرة NUMERIC");
		writer.println("@ATTRIBUTE الأراضي NUMERIC");
		writer.println("@ATTRIBUTE الأردن NUMERIC");
		writer.println("@ATTRIBUTE الأردني NUMERIC");
		writer.println("@ATTRIBUTE الأردنية NUMERIC");
		writer.println("@ATTRIBUTE الأسبوع NUMERIC");
		writer.println("@ATTRIBUTE الأسد NUMERIC");
		writer.println("@ATTRIBUTE الأسف NUMERIC");
		writer.println("@ATTRIBUTE الأشخاص NUMERIC");
		writer.println("@ATTRIBUTE الأطفال NUMERIC");
		writer.println("@ATTRIBUTE الأطلسي NUMERIC");
		writer.println("@ATTRIBUTE الأعلى NUMERIC");
		writer.println("@ATTRIBUTE الأغلبية NUMERIC");
		writer.println("@ATTRIBUTE الألبان NUMERIC");
		writer.println("@ATTRIBUTE الألتراس NUMERIC");
		writer.println("@ATTRIBUTE الأمة NUMERIC");
		writer.println("@ATTRIBUTE الأمر NUMERIC");
		writer.println("@ATTRIBUTE الأمريكي NUMERIC");
		writer.println("@ATTRIBUTE الأمريكية NUMERIC");
		writer.println("@ATTRIBUTE الأمريكيين NUMERIC");
		writer.println("@ATTRIBUTE الأمن NUMERIC");
		writer.println("@ATTRIBUTE الأمنية NUMERIC");
		writer.println("@ATTRIBUTE الأمور NUMERIC");
		writer.println("@ATTRIBUTE الأمير NUMERIC");
		writer.println("@ATTRIBUTE الأميركي NUMERIC");
		writer.println("@ATTRIBUTE الأميركية NUMERIC");
		writer.println("@ATTRIBUTE الأميركيين NUMERIC");
		writer.println("@ATTRIBUTE الأمين NUMERIC");
		writer.println("@ATTRIBUTE الأنباء NUMERIC");
		writer.println("@ATTRIBUTE الأهلي NUMERIC");
		writer.println("@ATTRIBUTE الأوروبي NUMERIC");
		writer.println("@ATTRIBUTE الأوسط NUMERIC");
		writer.println("@ATTRIBUTE الأول NUMERIC");
		writer.println("@ATTRIBUTE الآن NUMERIC");
		writer.println("@ATTRIBUTE البحث NUMERIC");
		writer.println("@ATTRIBUTE البرلمان NUMERIC");
		writer.println("@ATTRIBUTE البعض NUMERIC");
		writer.println("@ATTRIBUTE البلاد NUMERIC");
		writer.println("@ATTRIBUTE البيت NUMERIC");
		writer.println("@ATTRIBUTE التأسيسية NUMERIC");
		writer.println("@ATTRIBUTE التحرير NUMERIC");
		writer.println("@ATTRIBUTE التدخل NUMERIC");
		writer.println("@ATTRIBUTE التشريع NUMERIC");
		writer.println("@ATTRIBUTE التشريعية NUMERIC");
		writer.println("@ATTRIBUTE التقرير NUMERIC");
		writer.println("@ATTRIBUTE التمويل NUMERIC");
		writer.println("@ATTRIBUTE التنمية NUMERIC");
		writer.println("@ATTRIBUTE التي NUMERIC");
		writer.println("@ATTRIBUTE الثورة NUMERIC");
		writer.println("@ATTRIBUTE الجامعة NUMERIC");
		writer.println("@ATTRIBUTE الجانبين NUMERIC");
		writer.println("@ATTRIBUTE الجبهة NUMERIC");
		writer.println("@ATTRIBUTE الجريمة NUMERIC");
		writer.println("@ATTRIBUTE الجزائر NUMERIC");
		writer.println("@ATTRIBUTE الجزائري NUMERIC");
		writer.println("@ATTRIBUTE الجزائرية NUMERIC");
		writer.println("@ATTRIBUTE الجزيرة NUMERIC");
		writer.println("@ATTRIBUTE الجماعة NUMERIC");
		writer.println("@ATTRIBUTE الجمعية NUMERIC");
		writer.println("@ATTRIBUTE الجمهورية NUMERIC");
		writer.println("@ATTRIBUTE الحاجة NUMERIC");
		writer.println("@ATTRIBUTE الحرب NUMERIC");
		writer.println("@ATTRIBUTE الحرية NUMERIC");
		writer.println("@ATTRIBUTE الحزب NUMERIC");
		writer.println("@ATTRIBUTE الحقوق NUMERIC");
		writer.println("@ATTRIBUTE الحقيقة NUMERIC");
		writer.println("@ATTRIBUTE الحكومة NUMERIC");
		writer.println("@ATTRIBUTE الحكومية NUMERIC");
		writer.println("@ATTRIBUTE الحلقة NUMERIC");
		writer.println("@ATTRIBUTE الحمد NUMERIC");
		writer.println("@ATTRIBUTE الخارجية NUMERIC");
		writer.println("@ATTRIBUTE الخبير NUMERIC");
		writer.println("@ATTRIBUTE الخدمة NUMERIC");
		writer.println("@ATTRIBUTE الخليج NUMERIC");
		writer.println("@ATTRIBUTE الداخلي NUMERIC");
		writer.println("@ATTRIBUTE الدستور NUMERIC");
		writer.println("@ATTRIBUTE الدستوري NUMERIC");
		writer.println("@ATTRIBUTE الدستورية NUMERIC");
		writer.println("@ATTRIBUTE الدكتور NUMERIC");
		writer.println("@ATTRIBUTE الدنيا NUMERIC");
		writer.println("@ATTRIBUTE الدول NUMERIC");
		writer.println("@ATTRIBUTE الدولة NUMERIC");
		writer.println("@ATTRIBUTE الدولية NUMERIC");
		writer.println("@ATTRIBUTE الديار NUMERIC");
		writer.println("@ATTRIBUTE الديمقراطية NUMERIC");
		writer.println("@ATTRIBUTE الذي NUMERIC");
		writer.println("@ATTRIBUTE الذين NUMERIC");
		writer.println("@ATTRIBUTE الرجل NUMERIC");
		writer.println("@ATTRIBUTE الرزاق NUMERIC");
		writer.println("@ATTRIBUTE الرئاسة NUMERIC");
		writer.println("@ATTRIBUTE الرئاسية NUMERIC");
		writer.println("@ATTRIBUTE الرئيس NUMERIC");
		writer.println("@ATTRIBUTE الزميل NUMERIC");
		writer.println("@ATTRIBUTE السابق NUMERIC");
		writer.println("@ATTRIBUTE السابقة NUMERIC");
		writer.println("@ATTRIBUTE الستار NUMERIC");
		writer.println("@ATTRIBUTE السعودية NUMERIC");
		writer.println("@ATTRIBUTE السلام NUMERIC");
		writer.println("@ATTRIBUTE السلطة NUMERIC");
		writer.println("@ATTRIBUTE السنة NUMERIC");
		writer.println("@ATTRIBUTE السوري NUMERIC");
		writer.println("@ATTRIBUTE السوريين NUMERIC");
		writer.println("@ATTRIBUTE السياسة NUMERIC");
		writer.println("@ATTRIBUTE السياسي NUMERIC");
		writer.println("@ATTRIBUTE السياسية NUMERIC");
		writer.println("@ATTRIBUTE السيد NUMERIC");
		writer.println("@ATTRIBUTE الشاب NUMERIC");
		writer.println("@ATTRIBUTE الشارع NUMERIC");
		writer.println("@ATTRIBUTE الشرطة NUMERIC");
		writer.println("@ATTRIBUTE الشرق NUMERIC");
		writer.println("@ATTRIBUTE الشعب NUMERIC");
		writer.println("@ATTRIBUTE الشغل NUMERIC");
		writer.println("@ATTRIBUTE الشوارع NUMERIC");
		writer.println("@ATTRIBUTE الشيء NUMERIC");
		writer.println("@ATTRIBUTE الصحيفة NUMERIC");
		writer.println("@ATTRIBUTE الصناعة NUMERIC");
		writer.println("@ATTRIBUTE الصناعي NUMERIC");
		writer.println("@ATTRIBUTE الصورة NUMERIC");
		writer.println("@ATTRIBUTE الطائرات NUMERIC");
		writer.println("@ATTRIBUTE الطبقة NUMERIC");
		writer.println("@ATTRIBUTE العاصمة NUMERIC");
		writer.println("@ATTRIBUTE العالم NUMERIC");
		writer.println("@ATTRIBUTE العالمية NUMERIC");
		writer.println("@ATTRIBUTE العام NUMERIC");
		writer.println("@ATTRIBUTE العامة NUMERIC");
		writer.println("@ATTRIBUTE العدالة NUMERIC");
		writer.println("@ATTRIBUTE العراق NUMERIC");
		writer.println("@ATTRIBUTE العراقي NUMERIC");
		writer.println("@ATTRIBUTE العربي NUMERIC");
		writer.println("@ATTRIBUTE العربية NUMERIC");
		writer.println("@ATTRIBUTE العسكري NUMERIC");
		writer.println("@ATTRIBUTE العسكرية NUMERIC");
		writer.println("@ATTRIBUTE العضو NUMERIC");
		writer.println("@ATTRIBUTE العقد NUMERIC");
		writer.println("@ATTRIBUTE العلاج NUMERIC");
		writer.println("@ATTRIBUTE العلاقات NUMERIC");
		writer.println("@ATTRIBUTE العلمية NUMERIC");
		writer.println("@ATTRIBUTE العليا NUMERIC");
		writer.println("@ATTRIBUTE العمليات NUMERIC");
		writer.println("@ATTRIBUTE العملية NUMERIC");
		writer.println("@ATTRIBUTE العنصرية NUMERIC");
		writer.println("@ATTRIBUTE الغالبية NUMERIC");
		writer.println("@ATTRIBUTE الفترة NUMERIC");
		writer.println("@ATTRIBUTE الفساد NUMERIC");
		writer.println("@ATTRIBUTE الفلسطيني NUMERIC");
		writer.println("@ATTRIBUTE الفلسطينية NUMERIC");
		writer.println("@ATTRIBUTE الفلسطينيون NUMERIC");
		writer.println("@ATTRIBUTE الفلسطينيين NUMERIC");
		writer.println("@ATTRIBUTE الفنان NUMERIC");
		writer.println("@ATTRIBUTE القادم NUMERIC");
		writer.println("@ATTRIBUTE القانون NUMERIC");
		writer.println("@ATTRIBUTE القاهرة NUMERIC");
		writer.println("@ATTRIBUTE القرار NUMERIC");
		writer.println("@ATTRIBUTE القصف NUMERIC");
		writer.println("@ATTRIBUTE القضاء NUMERIC");
		writer.println("@ATTRIBUTE القضية NUMERIC");
		writer.println("@ATTRIBUTE القمة NUMERIC");
		writer.println("@ATTRIBUTE القمح NUMERIC");
		writer.println("@ATTRIBUTE القوات NUMERIC");
		writer.println("@ATTRIBUTE القوى NUMERIC");
		writer.println("@ATTRIBUTE القيادة NUMERIC");
		writer.println("@ATTRIBUTE الكثير NUMERIC");
		writer.println("@ATTRIBUTE الكرام NUMERIC");
		writer.println("@ATTRIBUTE الكلام NUMERIC");
		writer.println("@ATTRIBUTE الكلية NUMERIC");
		writer.println("@ATTRIBUTE الكويت NUMERIC");
		writer.println("@ATTRIBUTE الكويتي NUMERIC");
		writer.println("@ATTRIBUTE الكويتية NUMERIC");
		writer.println("@ATTRIBUTE اللاجئين NUMERIC");
		writer.println("@ATTRIBUTE اللبناني NUMERIC");
		writer.println("@ATTRIBUTE اللبنانية NUMERIC");
		writer.println("@ATTRIBUTE اللحظة NUMERIC");
		writer.println("@ATTRIBUTE الله NUMERIC");
		writer.println("@ATTRIBUTE اللي NUMERIC");
		writer.println("@ATTRIBUTE الليل NUMERIC");
		writer.println("@ATTRIBUTE الماضي NUMERIC");
		writer.println("@ATTRIBUTE المالكي NUMERIC");
		writer.println("@ATTRIBUTE المالية NUMERIC");
		writer.println("@ATTRIBUTE المائة NUMERIC");
		writer.println("@ATTRIBUTE المبادرة NUMERIC");
		writer.println("@ATTRIBUTE المتحدة NUMERIC");
		writer.println("@ATTRIBUTE المتظاهرين NUMERIC");
		writer.println("@ATTRIBUTE المجتمع NUMERIC");
		writer.println("@ATTRIBUTE المجلس NUMERIC");
		writer.println("@ATTRIBUTE المحتلة NUMERIC");
		writer.println("@ATTRIBUTE المحكمة NUMERIC");
		writer.println("@ATTRIBUTE المدارس NUMERIC");
		writer.println("@ATTRIBUTE المدرسة NUMERIC");
		writer.println("@ATTRIBUTE المرسوم NUMERIC");
		writer.println("@ATTRIBUTE المرشح NUMERIC");
		writer.println("@ATTRIBUTE المرض NUMERIC");
		writer.println("@ATTRIBUTE المسألة NUMERIC");
		writer.println("@ATTRIBUTE المسلحة NUMERIC");
		writer.println("@ATTRIBUTE المسلمين NUMERIC");
		writer.println("@ATTRIBUTE المشاهدين NUMERIC");
		writer.println("@ATTRIBUTE المشكل NUMERIC");
		writer.println("@ATTRIBUTE المصري NUMERIC");
		writer.println("@ATTRIBUTE المصرية NUMERIC");
		writer.println("@ATTRIBUTE المصريين NUMERIC");
		writer.println("@ATTRIBUTE المعارضة NUMERIC");
		writer.println("@ATTRIBUTE المغرب NUMERIC");
		writer.println("@ATTRIBUTE المغربي NUMERIC");
		writer.println("@ATTRIBUTE المغربية NUMERIC");
		writer.println("@ATTRIBUTE المقاتلين NUMERIC");
		writer.println("@ATTRIBUTE المقبل NUMERIC");
		writer.println("@ATTRIBUTE المقدونية NUMERIC");
		writer.println("@ATTRIBUTE المنطقة NUMERIC");
		writer.println("@ATTRIBUTE المواجهة NUMERIC");
		writer.println("@ATTRIBUTE الموجودة NUMERIC");
		writer.println("@ATTRIBUTE الموسيقى NUMERIC");
		writer.println("@ATTRIBUTE الموضوع NUMERIC");
		writer.println("@ATTRIBUTE المؤتمر NUMERIC");
		writer.println("@ATTRIBUTE المؤسسات NUMERIC");
		writer.println("@ATTRIBUTE النادي NUMERIC");
		writer.println("@ATTRIBUTE الناس NUMERIC");
		writer.println("@ATTRIBUTE النائب NUMERIC");
		writer.println("@ATTRIBUTE النجم NUMERIC");
		writer.println("@ATTRIBUTE النظام NUMERIC");
		writer.println("@ATTRIBUTE النقابات NUMERIC");
		writer.println("@ATTRIBUTE النقابي NUMERIC");
		writer.println("@ATTRIBUTE الهاشمي NUMERIC");
		writer.println("@ATTRIBUTE الهجوم NUMERIC");
		writer.println("@ATTRIBUTE الوزراء NUMERIC");
		writer.println("@ATTRIBUTE الوضع NUMERIC");
		writer.println("@ATTRIBUTE الوطني NUMERIC");
		writer.println("@ATTRIBUTE الوقت NUMERIC");
		writer.println("@ATTRIBUTE الولايات NUMERIC");
		writer.println("@ATTRIBUTE اليوم NUMERIC");
		writer.println("@ATTRIBUTE انتخابات NUMERIC");
		writer.println("@ATTRIBUTE إحنا NUMERIC");
		writer.println("@ATTRIBUTE إذا NUMERIC");
		writer.println("@ATTRIBUTE إذن NUMERIC");
		writer.println("@ATTRIBUTE إسرائيل NUMERIC");
		writer.println("@ATTRIBUTE إشي NUMERIC");
		writer.println("@ATTRIBUTE إلى NUMERIC");
		writer.println("@ATTRIBUTE إليها NUMERIC");
		writer.println("@ATTRIBUTE إمبيسي NUMERIC");
		writer.println("@ATTRIBUTE إن NUMERIC");
		writer.println("@ATTRIBUTE إنك NUMERIC");
		writer.println("@ATTRIBUTE إنما NUMERIC");
		writer.println("@ATTRIBUTE إننا NUMERIC");
		writer.println("@ATTRIBUTE إنه NUMERIC");
		writer.println("@ATTRIBUTE إنها NUMERIC");
		writer.println("@ATTRIBUTE إيزي NUMERIC");
		writer.println("@ATTRIBUTE إيه NUMERIC");
		writer.println("@ATTRIBUTE أبدا NUMERIC");
		writer.println("@ATTRIBUTE أبناء NUMERIC");
		writer.println("@ATTRIBUTE أثر NUMERIC");
		writer.println("@ATTRIBUTE أثناء NUMERIC");
		writer.println("@ATTRIBUTE أحد NUMERIC");
		writer.println("@ATTRIBUTE أحسن NUMERIC");
		writer.println("@ATTRIBUTE أحمد NUMERIC");
		writer.println("@ATTRIBUTE أحيانا NUMERIC");
		writer.println("@ATTRIBUTE أخذ NUMERIC");
		writer.println("@ATTRIBUTE أرييل NUMERIC");
		writer.println("@ATTRIBUTE أساسا NUMERIC");
		writer.println("@ATTRIBUTE أسأل NUMERIC");
		writer.println("@ATTRIBUTE أستاذ NUMERIC");
		writer.println("@ATTRIBUTE أسعار NUMERIC");
		writer.println("@ATTRIBUTE أسلحة NUMERIC");
		writer.println("@ATTRIBUTE أشار NUMERIC");
		writer.println("@ATTRIBUTE أصبحت NUMERIC");
		writer.println("@ATTRIBUTE أصلا NUMERIC");
		writer.println("@ATTRIBUTE أعتقد NUMERIC");
		writer.println("@ATTRIBUTE أعلن NUMERIC");
		writer.println("@ATTRIBUTE أعمل NUMERIC");
		writer.println("@ATTRIBUTE أغنية NUMERIC");
		writer.println("@ATTRIBUTE أقول NUMERIC");
		writer.println("@ATTRIBUTE أكثر NUMERIC");
		writer.println("@ATTRIBUTE أكيد NUMERIC");
		writer.println("@ATTRIBUTE أل NUMERIC");
		writer.println("@ATTRIBUTE ألا NUMERIC");
		writer.println("@ATTRIBUTE أماكن NUMERIC");
		writer.println("@ATTRIBUTE أمس NUMERIC");
		writer.println("@ATTRIBUTE أمن NUMERIC");
		writer.println("@ATTRIBUTE أن NUMERIC");
		writer.println("@ATTRIBUTE أنا NUMERIC");
		writer.println("@ATTRIBUTE أنت NUMERIC");
		writer.println("@ATTRIBUTE أنها NUMERIC");
		writer.println("@ATTRIBUTE أه NUMERIC");
		writer.println("@ATTRIBUTE أهلا NUMERIC");
		writer.println("@ATTRIBUTE أهم NUMERIC");
		writer.println("@ATTRIBUTE أهمية NUMERIC");
		writer.println("@ATTRIBUTE أو NUMERIC");
		writer.println("@ATTRIBUTE أوباما NUMERIC");
		writer.println("@ATTRIBUTE أول NUMERIC");
		writer.println("@ATTRIBUTE أي NUMERIC");
		writer.println("@ATTRIBUTE أيام NUMERIC");
		writer.println("@ATTRIBUTE أيضا NUMERIC");
		writer.println("@ATTRIBUTE بالتأكيد NUMERIC");
		writer.println("@ATTRIBUTE بالزاف NUMERIC");
		writer.println("@ATTRIBUTE بالضبط NUMERIC");
		writer.println("@ATTRIBUTE بالضرورة NUMERIC");
		writer.println("@ATTRIBUTE بالعكس NUMERIC");
		writer.println("@ATTRIBUTE بالفعل NUMERIC");
		writer.println("@ATTRIBUTE باللي NUMERIC");
		writer.println("@ATTRIBUTE بأن NUMERIC");
		writer.println("@ATTRIBUTE بأنه NUMERIC");
		writer.println("@ATTRIBUTE بتاع NUMERIC");
		writer.println("@ATTRIBUTE بحاجة NUMERIC");
		writer.println("@ATTRIBUTE بدأ NUMERIC");
		writer.println("@ATTRIBUTE بدأت NUMERIC");
		writer.println("@ATTRIBUTE بدنا NUMERIC");
		writer.println("@ATTRIBUTE بدوا NUMERIC");
		writer.println("@ATTRIBUTE بدي NUMERIC");
		writer.println("@ATTRIBUTE برشا NUMERIC");
		writer.println("@ATTRIBUTE برضه NUMERIC");
		writer.println("@ATTRIBUTE برلمان NUMERIC");
		writer.println("@ATTRIBUTE برنامج NUMERIC");
		writer.println("@ATTRIBUTE بس NUMERIC");
		writer.println("@ATTRIBUTE بسبب NUMERIC");
		writer.println("@ATTRIBUTE بسوريا NUMERIC");
		writer.println("@ATTRIBUTE بش NUMERIC");
		writer.println("@ATTRIBUTE بشار NUMERIC");
		writer.println("@ATTRIBUTE بشدة NUMERIC");
		writer.println("@ATTRIBUTE بشكل NUMERIC");
		writer.println("@ATTRIBUTE بشمال NUMERIC");
		writer.println("@ATTRIBUTE بشن NUMERIC");
		writer.println("@ATTRIBUTE بشوية NUMERIC");
		writer.println("@ATTRIBUTE بشي NUMERIC");
		writer.println("@ATTRIBUTE بشيء NUMERIC");
		writer.println("@ATTRIBUTE بطبيعة NUMERIC");
		writer.println("@ATTRIBUTE بطريقة NUMERIC");
		writer.println("@ATTRIBUTE بعدها NUMERIC");
		writer.println("@ATTRIBUTE بعدين NUMERIC");
		writer.println("@ATTRIBUTE بعض NUMERIC");
		writer.println("@ATTRIBUTE بعضهم NUMERIC");
		writer.println("@ATTRIBUTE بقى NUMERIC");
		writer.println("@ATTRIBUTE بكثير NUMERIC");
		writer.println("@ATTRIBUTE بكم NUMERIC");
		writer.println("@ATTRIBUTE بن NUMERIC");
		writer.println("@ATTRIBUTE بيحكي NUMERIC");
		writer.println("@ATTRIBUTE بيريز NUMERIC");
		writer.println("@ATTRIBUTE بيش NUMERIC");
		writer.println("@ATTRIBUTE بيشتغل NUMERIC");
		writer.println("@ATTRIBUTE بيصير NUMERIC");
		writer.println("@ATTRIBUTE بيقول NUMERIC");
		writer.println("@ATTRIBUTE بيكون NUMERIC");
		writer.println("@ATTRIBUTE بين NUMERIC");
		writer.println("@ATTRIBUTE تحدث NUMERIC");
		writer.println("@ATTRIBUTE تدخل NUMERIC");
		writer.println("@ATTRIBUTE تذهب NUMERIC");
		writer.println("@ATTRIBUTE ترجع NUMERIC");
		writer.println("@ATTRIBUTE ترى NUMERIC");
		writer.println("@ATTRIBUTE تعتقد NUMERIC");
		writer.println("@ATTRIBUTE تعديل NUMERIC");
		writer.println("@ATTRIBUTE تعرف NUMERIC");
		writer.println("@ATTRIBUTE تقريبا NUMERIC");
		writer.println("@ATTRIBUTE تقنية NUMERIC");
		writer.println("@ATTRIBUTE تكون NUMERIC");
		writer.println("@ATTRIBUTE تلك NUMERIC");
		writer.println("@ATTRIBUTE تمشي NUMERIC");
		writer.println("@ATTRIBUTE توجد NUMERIC");
		writer.println("@ATTRIBUTE توفير NUMERIC");
		writer.println("@ATTRIBUTE تونس NUMERIC");
		writer.println("@ATTRIBUTE تيتوفو NUMERIC");
		writer.println("@ATTRIBUTE ثاني NUMERIC");
		writer.println("@ATTRIBUTE ثلاثة NUMERIC");
		writer.println("@ATTRIBUTE ثورة NUMERIC");
		writer.println("@ATTRIBUTE جبل NUMERIC");
		writer.println("@ATTRIBUTE جدا NUMERIC");
		writer.println("@ATTRIBUTE جزء NUMERIC");
		writer.println("@ATTRIBUTE جزيلا NUMERIC");
		writer.println("@ATTRIBUTE جماعة NUMERIC");
		writer.println("@ATTRIBUTE جمال NUMERIC");
		writer.println("@ATTRIBUTE جنوب NUMERIC");
		writer.println("@ATTRIBUTE جورج NUMERIC");
		writer.println("@ATTRIBUTE حاجات NUMERIC");
		writer.println("@ATTRIBUTE حاجة NUMERIC");
		writer.println("@ATTRIBUTE حتى NUMERIC");
		writer.println("@ATTRIBUTE حد NUMERIC");
		writer.println("@ATTRIBUTE حدا NUMERIC");
		writer.println("@ATTRIBUTE حدث NUMERIC");
		writer.println("@ATTRIBUTE حدش NUMERIC");
		writer.println("@ATTRIBUTE حزب NUMERIC");
		writer.println("@ATTRIBUTE حسني NUMERIC");
		writer.println("@ATTRIBUTE حصل NUMERIC");
		writer.println("@ATTRIBUTE حقيقة NUMERIC");
		writer.println("@ATTRIBUTE حقيقي NUMERIC");
		writer.println("@ATTRIBUTE حقيقية NUMERIC");
		writer.println("@ATTRIBUTE حكومة NUMERIC");
		writer.println("@ATTRIBUTE حل NUMERIC");
		writer.println("@ATTRIBUTE حلو NUMERIC");
		writer.println("@ATTRIBUTE حول NUMERIC");
		writer.println("@ATTRIBUTE حيث NUMERIC");
		writer.println("@ATTRIBUTE خروج NUMERIC");
		writer.println("@ATTRIBUTE خلاص NUMERIC");
		writer.println("@ATTRIBUTE خلال NUMERIC");
		writer.println("@ATTRIBUTE خليك NUMERIC");
		writer.println("@ATTRIBUTE دا NUMERIC");
		writer.println("@ATTRIBUTE دائما NUMERIC");
		writer.println("@ATTRIBUTE دبي NUMERIC");
		writer.println("@ATTRIBUTE درهم NUMERIC");
		writer.println("@ATTRIBUTE درويش NUMERIC");
		writer.println("@ATTRIBUTE دستور NUMERIC");
		writer.println("@ATTRIBUTE دفع NUMERIC");
		writer.println("@ATTRIBUTE دكتور NUMERIC");
		writer.println("@ATTRIBUTE دمشق NUMERIC");
		writer.println("@ATTRIBUTE ده NUMERIC");
		writer.println("@ATTRIBUTE دولة NUMERIC");
		writer.println("@ATTRIBUTE دي NUMERIC");
		writer.println("@ATTRIBUTE ديال NUMERIC");
		writer.println("@ATTRIBUTE دياله NUMERIC");
		writer.println("@ATTRIBUTE ديالها NUMERIC");
		writer.println("@ATTRIBUTE دية NUMERIC");
		writer.println("@ATTRIBUTE ديك NUMERIC");
		writer.println("@ATTRIBUTE ديني NUMERIC");
		writer.println("@ATTRIBUTE ذلك NUMERIC");
		writer.println("@ATTRIBUTE راح NUMERIC");
		writer.println("@ATTRIBUTE راحت NUMERIC");
		writer.println("@ATTRIBUTE رب NUMERIC");
		writer.println("@ATTRIBUTE ربما NUMERIC");
		writer.println("@ATTRIBUTE رح NUMERIC");
		writer.println("@ATTRIBUTE رشيد NUMERIC");
		writer.println("@ATTRIBUTE رشيم NUMERIC");
		writer.println("@ATTRIBUTE رغم NUMERIC");
		writer.println("@ATTRIBUTE رئيس NUMERIC");
		writer.println("@ATTRIBUTE زي NUMERIC");
		writer.println("@ATTRIBUTE زيارة NUMERIC");
		writer.println("@ATTRIBUTE زيت NUMERIC");
		writer.println("@ATTRIBUTE ساكن NUMERIC");
		writer.println("@ATTRIBUTE ستكون NUMERIC");
		writer.println("@ATTRIBUTE سد NUMERIC");
		writer.println("@ATTRIBUTE سنة NUMERIC");
		writer.println("@ATTRIBUTE سنين NUMERIC");
		writer.println("@ATTRIBUTE سوريا NUMERIC");
		writer.println("@ATTRIBUTE سيادة NUMERIC");
		writer.println("@ATTRIBUTE سياسة NUMERIC");
		writer.println("@ATTRIBUTE سياسية NUMERIC");
		writer.println("@ATTRIBUTE سيد NUMERIC");
		writer.println("@ATTRIBUTE سير NUMERIC");
		writer.println("@ATTRIBUTE سينغ NUMERIC");
		writer.println("@ATTRIBUTE شاء NUMERIC");
		writer.println("@ATTRIBUTE شارون NUMERIC");
		writer.println("@ATTRIBUTE شبه NUMERIC");
		writer.println("@ATTRIBUTE شدة NUMERIC");
		writer.println("@ATTRIBUTE شركات NUMERIC");
		writer.println("@ATTRIBUTE شرم NUMERIC");
		writer.println("@ATTRIBUTE شغل NUMERIC");
		writer.println("@ATTRIBUTE شغلة NUMERIC");
		writer.println("@ATTRIBUTE شلون NUMERIC");
		writer.println("@ATTRIBUTE شنو NUMERIC");
		writer.println("@ATTRIBUTE شو NUMERIC");
		writer.println("@ATTRIBUTE شوف NUMERIC");
		writer.println("@ATTRIBUTE شوفوا NUMERIC");
		writer.println("@ATTRIBUTE شوي NUMERIC");
		writer.println("@ATTRIBUTE شوية NUMERIC");
		writer.println("@ATTRIBUTE شي NUMERIC");
		writer.println("@ATTRIBUTE شيء NUMERIC");
		writer.println("@ATTRIBUTE صار NUMERIC");
		writer.println("@ATTRIBUTE صحيح NUMERIC");
		writer.println("@ATTRIBUTE صحيفة NUMERIC");
		writer.println("@ATTRIBUTE صدر NUMERIC");
		writer.println("@ATTRIBUTE صدفة NUMERIC");
		writer.println("@ATTRIBUTE صرنا NUMERIC");
		writer.println("@ATTRIBUTE صعوبات NUMERIC");
		writer.println("@ATTRIBUTE صغير NUMERIC");
		writer.println("@ATTRIBUTE صناعة NUMERIC");
		writer.println("@ATTRIBUTE صناعية NUMERIC");
		writer.println("@ATTRIBUTE صور NUMERIC");
		writer.println("@ATTRIBUTE صورة NUMERIC");
		writer.println("@ATTRIBUTE ضيفنا NUMERIC");
		writer.println("@ATTRIBUTE طاولة NUMERIC");
		writer.println("@ATTRIBUTE طبعا NUMERIC");
		writer.println("@ATTRIBUTE طبيعي NUMERIC");
		writer.println("@ATTRIBUTE طلال NUMERIC");
		writer.println("@ATTRIBUTE طلعت NUMERIC");
		writer.println("@ATTRIBUTE ظل NUMERIC");
		writer.println("@ATTRIBUTE عام NUMERIC");
		writer.println("@ATTRIBUTE عايز NUMERIC");
		writer.println("@ATTRIBUTE عدد NUMERIC");
		writer.println("@ATTRIBUTE عرفات NUMERIC");
		writer.println("@ATTRIBUTE عسكرية NUMERIC");
		writer.println("@ATTRIBUTE على NUMERIC");
		writer.println("@ATTRIBUTE عليش NUMERIC");
		writer.println("@ATTRIBUTE عليك NUMERIC");
		writer.println("@ATTRIBUTE علينا NUMERIC");
		writer.println("@ATTRIBUTE عليه NUMERIC");
		writer.println("@ATTRIBUTE عم NUMERIC");
		writer.println("@ATTRIBUTE عمان NUMERIC");
		writer.println("@ATTRIBUTE عمل NUMERIC");
		writer.println("@ATTRIBUTE عملك NUMERIC");
		writer.println("@ATTRIBUTE عملية NUMERIC");
		writer.println("@ATTRIBUTE عن NUMERIC");
		writer.println("@ATTRIBUTE عنا NUMERIC");
		writer.println("@ATTRIBUTE عندك NUMERIC");
		writer.println("@ATTRIBUTE عندكم NUMERIC");
		writer.println("@ATTRIBUTE عندنا NUMERIC");
		writer.println("@ATTRIBUTE عنده NUMERIC");
		writer.println("@ATTRIBUTE عندها NUMERIC");
		writer.println("@ATTRIBUTE عندهم NUMERIC");
		writer.println("@ATTRIBUTE عندوش NUMERIC");
		writer.println("@ATTRIBUTE عندي NUMERIC");
		writer.println("@ATTRIBUTE عنديش NUMERIC");
		writer.println("@ATTRIBUTE عنها NUMERIC");
		writer.println("@ATTRIBUTE غالي NUMERIC");
		writer.println("@ATTRIBUTE غزة NUMERIC");
		writer.println("@ATTRIBUTE غير NUMERIC");
		writer.println("@ATTRIBUTE ف NUMERIC");
		writer.println("@ATTRIBUTE فاصل NUMERIC");
		writer.println("@ATTRIBUTE فبراير NUMERIC");
		writer.println("@ATTRIBUTE فرض NUMERIC");
		writer.println("@ATTRIBUTE فرنسا NUMERIC");
		writer.println("@ATTRIBUTE فعلا NUMERIC");
		writer.println("@ATTRIBUTE فقد NUMERIC");
		writer.println("@ATTRIBUTE فكرة NUMERIC");
		writer.println("@ATTRIBUTE فلسطين NUMERIC");
		writer.println("@ATTRIBUTE فلسطيني NUMERIC");
		writer.println("@ATTRIBUTE فلوس NUMERIC");
		writer.println("@ATTRIBUTE فليفل NUMERIC");
		writer.println("@ATTRIBUTE فما NUMERIC");
		writer.println("@ATTRIBUTE فهي NUMERIC");
		writer.println("@ATTRIBUTE في NUMERIC");
		writer.println("@ATTRIBUTE فيش NUMERIC");
		writer.println("@ATTRIBUTE فيك NUMERIC");
		writer.println("@ATTRIBUTE فينا NUMERIC");
		writer.println("@ATTRIBUTE فيه NUMERIC");
		writer.println("@ATTRIBUTE فيها NUMERIC");
		writer.println("@ATTRIBUTE قاسمي NUMERIC");
		writer.println("@ATTRIBUTE قانون NUMERIC");
		writer.println("@ATTRIBUTE قبل NUMERIC");
		writer.println("@ATTRIBUTE قتلوا NUMERIC");
		writer.println("@ATTRIBUTE قد NUMERIC");
		writer.println("@ATTRIBUTE قديم NUMERIC");
		writer.println("@ATTRIBUTE قرار NUMERIC");
		writer.println("@ATTRIBUTE قرارا NUMERIC");
		writer.println("@ATTRIBUTE قرر NUMERIC");
		writer.println("@ATTRIBUTE قصة NUMERIC");
		writer.println("@ATTRIBUTE قضية NUMERIC");
		writer.println("@ATTRIBUTE قطر NUMERIC");
		writer.println("@ATTRIBUTE قلت NUMERIC");
		writer.println("@ATTRIBUTE قول NUMERIC");
		writer.println("@ATTRIBUTE قوي NUMERIC");
		writer.println("@ATTRIBUTE كامل NUMERIC");
		writer.println("@ATTRIBUTE كان NUMERIC");
		writer.println("@ATTRIBUTE كانت NUMERIC");
		writer.println("@ATTRIBUTE كانش NUMERIC");
		writer.println("@ATTRIBUTE كثير NUMERIC");
		writer.println("@ATTRIBUTE كثيرة NUMERIC");
		writer.println("@ATTRIBUTE كده NUMERIC");
		writer.println("@ATTRIBUTE كذا NUMERIC");
		writer.println("@ATTRIBUTE كذلك NUMERIC");
		writer.println("@ATTRIBUTE كردستان NUMERIC");
		writer.println("@ATTRIBUTE كل NUMERIC");
		writer.println("@ATTRIBUTE كلام NUMERIC");
		writer.println("@ATTRIBUTE كلنا NUMERIC");
		writer.println("@ATTRIBUTE كلية NUMERIC");
		writer.println("@ATTRIBUTE كما NUMERIC");
		writer.println("@ATTRIBUTE كمان NUMERIC");
		writer.println("@ATTRIBUTE كنا NUMERIC");
		writer.println("@ATTRIBUTE كنت NUMERIC");
		writer.println("@ATTRIBUTE كي NUMERIC");
		writer.println("@ATTRIBUTE كيف NUMERIC");
		writer.println("@ATTRIBUTE كيم NUMERIC");
		writer.println("@ATTRIBUTE كين NUMERIC");
		writer.println("@ATTRIBUTE لا NUMERIC");
		writer.println("@ATTRIBUTE لاحق NUMERIC");
		writer.println("@ATTRIBUTE لازم NUMERIC");
		writer.println("@ATTRIBUTE لإسرائيل NUMERIC");
		writer.println("@ATTRIBUTE لأن NUMERIC");
		writer.println("@ATTRIBUTE لأنه NUMERIC");
		writer.println("@ATTRIBUTE لدراسة NUMERIC");
		writer.println("@ATTRIBUTE لدى NUMERIC");
		writer.println("@ATTRIBUTE لديها NUMERIC");
		writer.println("@ATTRIBUTE لقاء NUMERIC");
		writer.println("@ATTRIBUTE لقد NUMERIC");
		writer.println("@ATTRIBUTE لك NUMERIC");
		writer.println("@ATTRIBUTE لكان NUMERIC");
		writer.println("@ATTRIBUTE لكن NUMERIC");
		writer.println("@ATTRIBUTE للرئيس NUMERIC");
		writer.println("@ATTRIBUTE للفنون NUMERIC");
		writer.println("@ATTRIBUTE للقوات NUMERIC");
		writer.println("@ATTRIBUTE لم NUMERIC");
		writer.println("@ATTRIBUTE لما NUMERIC");
		writer.println("@ATTRIBUTE لماذا NUMERIC");
		writer.println("@ATTRIBUTE لنا NUMERIC");
		writer.println("@ATTRIBUTE لها NUMERIC");
		writer.println("@ATTRIBUTE لو NUMERIC");
		writer.println("@ATTRIBUTE ليس NUMERIC");
		writer.println("@ATTRIBUTE ليست NUMERIC");
		writer.println("@ATTRIBUTE ليش NUMERIC");
		writer.println("@ATTRIBUTE ما NUMERIC");
		writer.println("@ATTRIBUTE مبادرة NUMERIC");
		writer.println("@ATTRIBUTE مبارك NUMERIC");
		writer.println("@ATTRIBUTE مثل NUMERIC");
		writer.println("@ATTRIBUTE مثلا NUMERIC");
		writer.println("@ATTRIBUTE مثلما NUMERIC");
		writer.println("@ATTRIBUTE مجتمع NUMERIC");
		writer.println("@ATTRIBUTE مجرد NUMERIC");
		writer.println("@ATTRIBUTE مجلس NUMERIC");
		writer.println("@ATTRIBUTE محاولة NUMERIC");
		writer.println("@ATTRIBUTE محمد NUMERIC");
		writer.println("@ATTRIBUTE مرسوم NUMERIC");
		writer.println("@ATTRIBUTE مرسي NUMERIC");
		writer.println("@ATTRIBUTE مرشح NUMERIC");
		writer.println("@ATTRIBUTE مرض NUMERIC");
		writer.println("@ATTRIBUTE مسألة NUMERIC");
		writer.println("@ATTRIBUTE مش NUMERIC");
		writer.println("@ATTRIBUTE مشاهدينا NUMERIC");
		writer.println("@ATTRIBUTE مشكل NUMERIC");
		writer.println("@ATTRIBUTE مشي NUMERIC");
		writer.println("@ATTRIBUTE مصر NUMERIC");
		writer.println("@ATTRIBUTE مصري NUMERIC");
		writer.println("@ATTRIBUTE مصرية NUMERIC");
		writer.println("@ATTRIBUTE مع NUMERIC");
		writer.println("@ATTRIBUTE معظم NUMERIC");
		writer.println("@ATTRIBUTE معك NUMERIC");
		writer.println("@ATTRIBUTE معناها NUMERIC");
		writer.println("@ATTRIBUTE مفاوضات NUMERIC");
		writer.println("@ATTRIBUTE مقدونيا NUMERIC");
		writer.println("@ATTRIBUTE مكان NUMERIC");
		writer.println("@ATTRIBUTE مكة NUMERIC");
		writer.println("@ATTRIBUTE ممكن NUMERIC");
		writer.println("@ATTRIBUTE من NUMERIC");
		writer.println("@ATTRIBUTE منظمة NUMERIC");
		writer.println("@ATTRIBUTE منك NUMERIC");
		writer.println("@ATTRIBUTE مني NUMERIC");
		writer.println("@ATTRIBUTE مهم NUMERIC");
		writer.println("@ATTRIBUTE موجود NUMERIC");
		writer.println("@ATTRIBUTE موجودة NUMERIC");
		writer.println("@ATTRIBUTE موجودين NUMERIC");
		writer.println("@ATTRIBUTE موضوع NUMERIC");
		writer.println("@ATTRIBUTE مؤتمر NUMERIC");
		writer.println("@ATTRIBUTE مؤسسة NUMERIC");
		writer.println("@ATTRIBUTE ميدان NUMERIC");
		writer.println("@ATTRIBUTE مئات NUMERIC");
		writer.println("@ATTRIBUTE ناس NUMERIC");
		writer.println("@ATTRIBUTE نائب NUMERIC");
		writer.println("@ATTRIBUTE نتكلم NUMERIC");
		writer.println("@ATTRIBUTE نجم NUMERIC");
		writer.println("@ATTRIBUTE نحاول NUMERIC");
		writer.println("@ATTRIBUTE نحب NUMERIC");
		writer.println("@ATTRIBUTE نحكي NUMERIC");
		writer.println("@ATTRIBUTE نحن NUMERIC");
		writer.println("@ATTRIBUTE نحنا NUMERIC");
		writer.println("@ATTRIBUTE نحو NUMERIC");
		writer.println("@ATTRIBUTE نخرج NUMERIC");
		writer.println("@ATTRIBUTE نشوف NUMERIC");
		writer.println("@ATTRIBUTE نص NUMERIC");
		writer.println("@ATTRIBUTE نعرف NUMERIC");
		writer.println("@ATTRIBUTE نعلم NUMERIC");
		writer.println("@ATTRIBUTE نعم NUMERIC");
		writer.println("@ATTRIBUTE نفسه NUMERIC");
		writer.println("@ATTRIBUTE نفسها NUMERIC");
		writer.println("@ATTRIBUTE نقابية NUMERIC");
		writer.println("@ATTRIBUTE نقدر NUMERIC");
		writer.println("@ATTRIBUTE نقول NUMERIC");
		writer.println("@ATTRIBUTE نمشي NUMERIC");
		writer.println("@ATTRIBUTE نوع NUMERIC");
		writer.println("@ATTRIBUTE ها NUMERIC");
		writer.println("@ATTRIBUTE هادي NUMERIC");
		writer.println("@ATTRIBUTE هاي NUMERIC");
		writer.println("@ATTRIBUTE هايدي NUMERIC");
		writer.println("@ATTRIBUTE هجرس NUMERIC");
		writer.println("@ATTRIBUTE هذا NUMERIC");
		writer.println("@ATTRIBUTE هذاك NUMERIC");
		writer.println("@ATTRIBUTE هذه NUMERIC");
		writer.println("@ATTRIBUTE هل NUMERIC");
		writer.println("@ATTRIBUTE هم NUMERIC");
		writer.println("@ATTRIBUTE هناك NUMERIC");
		writer.println("@ATTRIBUTE هو NUMERIC");
		writer.println("@ATTRIBUTE هؤلاء NUMERIC");
		writer.println("@ATTRIBUTE هيدي NUMERIC");
		writer.println("@ATTRIBUTE هيك NUMERIC");
		writer.println("@ATTRIBUTE واحد NUMERIC");
		writer.println("@ATTRIBUTE واشنطن NUMERIC");
		writer.println("@ATTRIBUTE واضح NUMERIC");
		writer.println("@ATTRIBUTE واضحة NUMERIC");
		writer.println("@ATTRIBUTE والحكومة NUMERIC");
		writer.println("@ATTRIBUTE والعدالة NUMERIC");
		writer.println("@ATTRIBUTE والعشرين NUMERIC");
		writer.println("@ATTRIBUTE والله NUMERIC");
		writer.println("@ATTRIBUTE واللي NUMERIC");
		writer.println("@ATTRIBUTE وإحنا NUMERIC");
		writer.println("@ATTRIBUTE وإذا NUMERIC");
		writer.println("@ATTRIBUTE وإن NUMERIC");
		writer.println("@ATTRIBUTE وإنما NUMERIC");
		writer.println("@ATTRIBUTE وأن NUMERIC");
		writer.println("@ATTRIBUTE وأنا NUMERIC");
		writer.println("@ATTRIBUTE وأه NUMERIC");
		writer.println("@ATTRIBUTE وأيضا NUMERIC");
		writer.println("@ATTRIBUTE وبالتالي NUMERIC");
		writer.println("@ATTRIBUTE وبعدين NUMERIC");
		writer.println("@ATTRIBUTE وتابع NUMERIC");
		writer.println("@ATTRIBUTE وذلك NUMERIC");
		writer.println("@ATTRIBUTE وزير NUMERIC");
		writer.println("@ATTRIBUTE وزيرة NUMERIC");
		writer.println("@ATTRIBUTE وصلنا NUMERIC");
		writer.println("@ATTRIBUTE وغيرها NUMERIC");
		writer.println("@ATTRIBUTE وفق NUMERIC");
		writer.println("@ATTRIBUTE وفهما NUMERIC");
		writer.println("@ATTRIBUTE وفي NUMERIC");
		writer.println("@ATTRIBUTE وقد NUMERIC");
		writer.println("@ATTRIBUTE ولا NUMERIC");
		writer.println("@ATTRIBUTE ومش NUMERIC");
		writer.println("@ATTRIBUTE ومصر NUMERIC");
		writer.println("@ATTRIBUTE وهذا NUMERIC");
		writer.println("@ATTRIBUTE وي NUMERIC");
		writer.println("@ATTRIBUTE وين NUMERIC");
		writer.println("@ATTRIBUTE يا NUMERIC");
		writer.println("@ATTRIBUTE ياسر NUMERIC");
		writer.println("@ATTRIBUTE ياللي NUMERIC");
		writer.println("@ATTRIBUTE يبقى NUMERIC");
		writer.println("@ATTRIBUTE يتحدث NUMERIC");
		writer.println("@ATTRIBUTE يتم NUMERIC");
		writer.println("@ATTRIBUTE يجي NUMERIC");
		writer.println("@ATTRIBUTE يحبون NUMERIC");
		writer.println("@ATTRIBUTE يحدث NUMERIC");
		writer.println("@ATTRIBUTE يحصل NUMERIC");
		writer.println("@ATTRIBUTE يحكي NUMERIC");
		writer.println("@ATTRIBUTE يخدم NUMERIC");
		writer.println("@ATTRIBUTE يديروا NUMERIC");
		writer.println("@ATTRIBUTE يطلع NUMERIC");
		writer.println("@ATTRIBUTE يعمل NUMERIC");
		writer.println("@ATTRIBUTE يعملوا NUMERIC");
		writer.println("@ATTRIBUTE يعني NUMERIC");
		writer.println("@ATTRIBUTE يفترض NUMERIC");
		writer.println("@ATTRIBUTE يقول NUMERIC");
		writer.println("@ATTRIBUTE يقولوا NUMERIC");
		writer.println("@ATTRIBUTE يكن NUMERIC");
		writer.println("@ATTRIBUTE يكون NUMERIC");
		writer.println("@ATTRIBUTE يمشي NUMERIC");
		writer.println("@ATTRIBUTE يمكن NUMERIC");
		writer.println("@ATTRIBUTE يناير NUMERIC");
		writer.println("@ATTRIBUTE readability NUMERIC");
		writer.println("@ATTRIBUTE pureMSA NUMERIC");
		writer.println("@ATTRIBUTE pureEGY NUMERIC");
		writer.println("@ATTRIBUTE pureGLF NUMERIC");
		writer.println("@ATTRIBUTE pureLAV NUMERIC");
		writer.println("@ATTRIBUTE pureNOR NUMERIC");
		writer.println("@ATTRIBUTE MSAEGY NUMERIC");
		writer.println("@ATTRIBUTE MSAGLF NUMERIC");
		writer.println("@ATTRIBUTE MSALAV NUMERIC");
		writer.println("@ATTRIBUTE MSANOR NUMERIC");
		writer.println("@ATTRIBUTE Adverbials NUMERIC");
		writer.println("@ATTRIBUTE Adverbs NUMERIC");
		writer.println("@ATTRIBUTE ConjSub NUMERIC");
		writer.println("@ATTRIBUTE Conjunctions NUMERIC");
		writer.println("@ATTRIBUTE Demonstratives NUMERIC");
		writer.println("@ATTRIBUTE Modals NUMERIC");
		writer.println("@ATTRIBUTE Negation NUMERIC");
		writer.println("@ATTRIBUTE Particles NUMERIC");
		writer.println("@ATTRIBUTE Prepositionals NUMERIC");
		writer.println("@ATTRIBUTE Prepositions NUMERIC");
		writer.println("@ATTRIBUTE Pronoun NUMERIC");
		writer.println("@ATTRIBUTE Quantitives NUMERIC");
		writer.println("@ATTRIBUTE QuestionWords NUMERIC");
		writer.println("@ATTRIBUTE Relatives NUMERIC");
		writer.println("@ATTRIBUTE StopWords NUMERIC");
		writer.println("@ATTRIBUTE typeTokenRatio NUMERIC");
		writer.print("\n");
		writer.println("@data");
		writer.flush();
	}
	
	
	
	*//**
	 * get names of sub-directories (attributes/classes names). I treat each
	 * sub-directory as a class name (make sure no other sub directories
	 * presented) this makes the code flexible to accept more than 2 classes
	 * todo: isDirectory
	 * 
	 * @param datasetDir
	 * @return
	 *//*
	public static String[] getClassNames(String datasetDir) {
		String[] subdirectories = new File(datasetDir).list();
		return subdirectories;

	}
	
	
	*//**
	 * get text files from classes directories and write contents to arff file
	 * 
	 * @param classDirectory
	 * @param className
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 *//*
	public static String[] readFiles(String[] instances) throws IOException, InterruptedException {

		String[] tagsArray = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/tags.txt");
		String[] keywordsArray = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/keywords.txt");

		String[] pureMSA = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allDIALECTS_minusMSA.txt");
		String[] pureEGY = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allEGY_minusMSA.txt");
		String[] pureGLF = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allGLF_minusMSA.txt");
		String[] pureLAV = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allLAV_minusMSA.txt");
		String[] pureNOR = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allNOR_minusMSA.txt");
		String[] MSAEGY = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allEGY_dialectal_MSA.txt");
		String[] MSAGLF = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allGLF_dialectal_MSA.txt");
		String[] MSALAV = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allLAV_dialectal_MSA.txt");
		String[] MSANOR = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/allNOR_dialectal_MSA.txt");
		String[] Adverbials = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Adverbials.txt");
		String[] Adverbs = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Adverbs.txt");
		String[] ConjSub = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/ConjSub.txt");
		String[] Conjunctions = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Conjunctions.txt");
		String[] Demonstratives = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Demonstratives.txt");
		String[] Modals = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Modals.txt");
		String[] Negation=readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Negation.txt");
		String[] Particles = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Particles.txt");
		String[] Prepositionals = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Prepositionals.txt");
		String[] Prepositions = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Prepositions.txt");
		String[] Pronoun = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Pronouns.txt");
		String[] Quantitives = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Quantitives.txt");
		String[] QuestionWords = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/QuestionWords.txt");
		String[] Relatives = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/Relatives.txt");
		String[] StopWords = readLines("E:/arabicSharedTaskGit/ArabicSharedTask/keywords/StopWords.txt");



		for (int i = 0; i < instances.length; i++) {

				//int wordCount = instances[i].trim().split("\\s+").length;

					int uniqueTags = countUniqueTags(instances[i], tagsArray);
					String tagsCount = countFrequency(instances[i], tagsArray);
					String keywordsCount = countFrequency(instances[i], keywordsArray);
					
					int pureMSACount = countKeywords(instances[i], pureMSA);
					int pureEGYCount = countKeywords(instances[i], pureEGY);
					int pureGLFCount = countKeywords(instances[i], pureGLF);
					int pureLAVCount = countKeywords(instances[i], pureLAV);
					int pureNORCount = countKeywords(instances[i], pureNOR);
					int MSAEGYCount = countKeywords(instances[i], MSAEGY);
					int MSAGLFCount = countKeywords(instances[i], MSAGLF);
					int MSALAVCount = countKeywords(instances[i], MSALAV);
					int MSANORCount = countKeywords(instances[i], MSANOR);

					int Adverbialscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Adverbials);
					int Adverbscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Adverbs);
					int ConjSubcount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), ConjSub);
					int Conjunctionscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Conjunctions);
					int Demonstrativescount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Demonstratives);
					int Modalscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Modals);
					int Negationcount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Negation);
					int Particlescount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Particles);
					int Prepositionalscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Prepositionals);
					int Prepositionscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Prepositions);
					int Pronouncount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Pronoun);
					int Quantitivescount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Quantitives);
					int QuestionWordscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), QuestionWords);
					int Relativescount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), Relatives);
					int StopWordscount = countKeywords(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "), StopWords);

					String[] str = instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " ").split(" ");
					Set<String> set = new HashSet<String>(Arrays.asList(str));
					int uniqueWords = set.size();
					
					int sentCount = osman.countSentences(instances[i].replaceAll("[a-zA-Z]+", "").replace("/", "").trim().replaceAll(" +", " "));
					if(sentCount<1)
						sentCount=1;

					//type token ratio normalised by number of sentences
					double typeTokenRatio = ((double)uniqueWords/(double)str.length)/(double)sentCount;
					//lines[i] = className + "," + "'" + content.replaceAll("\n", "").replaceAll("\r", "").replaceAll(",", " ").replaceAll("'", " ").replace("_", " ").replace("-", " ").replace("&", " ").replace("%", " ").replaceAll(" +", " ").trim() + "'";

					String contentTA = osman.addTashkeel(instances[i]);
					double osmanScore = osman.calculateOsman(contentTA.trim());
					instances[i]="?" + "," + uniqueTags + tagsCount + keywordsCount + "," + osmanScore + "," + pureMSACount + "," + pureEGYCount 
							+ "," + pureGLFCount + "," + pureLAVCount + "," + pureNORCount
							+ "," + MSAEGYCount + "," + MSAGLFCount + "," + MSALAVCount + "," + MSANORCount 
							+ "," + Adverbialscount	+ "," + Adverbscount + "," + ConjSubcount 
							+ "," + Conjunctionscount + "," + Demonstrativescount + "," + Modalscount + "," + Negationcount 
							+ "," + Particlescount + "," + Prepositionalscount + "," + Prepositionscount + "," + Pronouncount 
							+ "," + Quantitivescount + "," + QuestionWordscount + "," + Relativescount + "," + StopWordscount + "," + typeTokenRatio;

					System.out.println(instances[i]);
					

			
		}
		return instances;
	}
	
	public static String[] readFiles(String classDirectory, String className) throws IOException {
		String[] tagsArray = readLines("tags.txt");
		String[] keywordsArray = readLines("keywords.txt");

		File folder = new File(classDirectory + File.separator + className);
		File[] listOfFiles = folder.listFiles();
		String[] lines = new String[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];

			if (file.isFile() && file.getName().endsWith(".txt") ||  file.getName().endsWith(".post")) {
				String content = FileUtils.readFileToString(file);
				int uniqueTags = countUniqueTags(content, tagsArray);
				String tagsCount = countFrequency(content, tagsArray);
				String keywordsCount = countFrequency(content, keywordsArray);
				//lines[i] = className + "," + "'" + content.replaceAll("\n", "").replaceAll("\r", "").replaceAll(",", " ").replaceAll("'", " ").replace("_", " ").replace("-", " ").replace("&", " ").replace("%", " ").replaceAll(" +", " ").trim() + "'";
				
				lines[i]=className + "," + uniqueTags + tagsCount + keywordsCount;
				System.out.println(lines[i]);
			}
		}
		return lines;
	}

	   
*//**
* remove null entries from array		
* @param v
* @return
*//*
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

	// search 2d array (this searches the firms keywords lists for a certain firm's keyword using the firm's name.
	private static String find2DItem(String[][] array, String search) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j].replace("_", " ").trim().equalsIgnoreCase(search.replace("_", " ").trim())) {
					return array[i][j + 1];
				}
			}
		}
		return "No match";
	}
	*/
}
