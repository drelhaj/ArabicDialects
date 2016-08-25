
package classifier;

/**
 * A Java class that implements a simple text classifier, based on WEKA.
 * Modified from the original examples for the purpose of UCREL Summer School in Corpus Based NLP
 * at Lancaster University
 * http://ucrel.lancs.ac.uk/summerschool/nlp.php
 * Modified by Mahmoud El-Haj
 */

import weka.core.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

	/**
	 * This class implements a simple text classifier in Java using WEKA.
	 * It loads a file with the text to classify, and the model that has been
	 * learnt with MyFilteredLearner.java.
	 */
	 public class FeaturesClassifier2 {

			
			/**
			 * This method performs the classification of the instance.
			 * Output is done at the command-line.
			 */
			
		public static PrintWriter writer;
		
		public static void printMe(String text){
			writer.println(text);
			writer.flush();
		}
		public static void predict() throws Exception {

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			printMe("Start time:\t " + dateFormat.format(date)); //2014/08/06 15:59:48
			
			
			Classifier smo = new SMO();
			System.out.println("read training arff");

			Instances train = new Instances(new BufferedReader(new FileReader("arff/AllClassesFeaturesTrunc20withReadabilityandFreqListsTrain.arff")));
			train.setClassIndex(0);

			System.out.println("read testing arff");
			Instances unlabeled = new Instances(new BufferedReader(new FileReader("arff/AllClassesFeaturesTrunc20withReadabilityandFreqListsTest.arff")));
			unlabeled.setClassIndex(0);

			// training
			System.out.println("training");
			Evaluation eval = new Evaluation(train);
			eval.crossValidateModel(smo, train, 10, new Random(1));
			String output = eval.toSummaryString();
			System.out.println(output);
			writer.println(output);
			writer.flush();
			String classDetails = eval.toClassDetailsString();
			System.out.println(classDetails);
			writer.println(classDetails);
			writer.flush();
			System.out.println("===== Evaluating on filtered (training) dataset done =====");
			
			smo.buildClassifier(train);

			Instances labeled = new Instances(unlabeled);
            
			// label instances
			for (int i = 0; i < unlabeled.numInstances(); i++) {
				double clsLabel = smo.classifyInstance(unlabeled.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
				System.out.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
				writer.println("=========================================================");
				writer.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
				writer.flush();
			}

	           	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("model/AllClassesFeaturesTrunc20withReadabilityandFreqLists.dat"));
	            out.writeObject(smo);
	            out.close();
	 			System.out.println("===== Saved model: model/AllClassesFeaturesTrunc20withReadabilityandFreqLists.dat =====");

				
			}
			public static void main (String[] args) throws Exception {
				long startTime = System.currentTimeMillis();
				writer = new PrintWriter("E:/arabicSharedTaskGit/ArabicSharedTask/model/featureClassifierOutput.txt");

				FeaturesClassifier2.predict();
				long estimatedTime = System.currentTimeMillis() - startTime;
				
				String time = String.format("%d min, %d sec", 
					    TimeUnit.MILLISECONDS.toMinutes(estimatedTime),
					    TimeUnit.MILLISECONDS.toSeconds(estimatedTime) - 
					    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(estimatedTime))
					);
				
				System.out.println(time);

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				writer.println("Start time:\t " + dateFormat.format(date)); //2014/08/06 15:59:48
				writer.flush();
				
			}
	}