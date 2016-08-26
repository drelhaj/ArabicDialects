package testing;

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
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;

	/**
	 * This class implements a simple text classifier in Java using WEKA.
	 * It loads a file with the text to classify, and the model that has been
	 * learnt with MyFilteredLearner.java.
	 */
	 public class CompetetionClassifier {

			
			/**
			 * This method performs the classification of the instance.
			 * Output is done at the command-line.
			 */
			
		static PrintWriter writer;
		static int counter = 0;
		public static void predict(String trainingFile, String testingFile) throws Exception {
			writer = new PrintWriter("arff/competetion/"+trainingFile.replace("arff/", "").replace(".arff", "")+".txt");
			
			Classifier classifier = null;


			System.out.println("==========================================================================");

			System.out.println("read training arff");

			Instances train = new Instances(new BufferedReader(new FileReader(trainingFile)));
			train.setClassIndex(0);

			System.out.println("read testing arff");
			Instances unlabeled = new Instances(new BufferedReader(new FileReader(testingFile)));
			unlabeled.setClassIndex(0);

			// training
			String[] algorithms = {"j48"};//,"nb","knn"};

			for(int w=0; w<algorithms.length;w++){
				
				if(algorithms[w].equals("nb"))
				classifier = new NaiveBayes();
				if(algorithms[w].equals("smo"))
				classifier = new SMO();
				if(algorithms[w].equals("knn"))
				classifier = new IBk();
				if(algorithms[w].equals("j48"))
				classifier = new J48();
			System.out.println("==========================================================================");
			System.out.println("training using " + algorithms[w] + " classifier");
			writer.println("==========================================================================");
			writer.println("training using " + algorithms[w] + " classifier");
			Evaluation eval = new Evaluation(train);
			eval.crossValidateModel(classifier, train, 10, new Random(1));//>>>>>>>>>>>>>>>>>>>>>>>>>>here
			String output = eval.toSummaryString();
			System.out.println(output);
			writer.println(output);
			writer.flush();
			String classDetails = eval.toClassDetailsString();
			System.out.println(classDetails);
			writer.println(classDetails);
			writer.flush();
			//System.out.println("=================================================== Confusion Matrix ===================================================");
			//System.out.println(eval.toMatrixString());
			//writer.println("=================================================== Confusion Matrix ===================================================");
			//writer.println(eval.toMatrixString());
			//writer.flush();
			System.out.println("===== Evaluating on filtered (training) dataset done =====");
			
			classifier.buildClassifier(train);//>>>>>>>>>>>>>>>>>>>>>>>>>>and here
					
			Instances labeled = new Instances(unlabeled);
            
			// label instances
			writer.println();
			writer.println("=========================================================");
			writer.println("		Testing Results			");
			writer.println("=========================================================");

			for (int i = 0; i < unlabeled.numInstances(); i++) {
				double clsLabel = classifier.classifyInstance(unlabeled.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
				System.out.println(++counter + " -> " + unlabeled.classAttribute().value((int) clsLabel));
				writer.println(counter + " -> " + unlabeled.classAttribute().value((int) clsLabel));
				writer.flush();
			}	
			writer.println("=========================================================");

			
			}
			


	           /*	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("model/Trunc20withReadabilityandFreqListsReducedTrain.dat"));
	            out.writeObject(classifier);
	            out.close();
	 			System.out.println("===== Saved model: model/Trunc20withReadabilityandFreqListsReducedTrain.dat =====");
*/
				
			}
			public static void mainClass() throws Exception {
				long startTime = System.currentTimeMillis();
				CompetetionClassifier.predict("arff/AllCL.arff","arff/ArffCompetetionCL.arff");
				
				long estimatedTime = System.currentTimeMillis() - startTime;
				
				String time = String.format("%d min, %d sec", 
					    TimeUnit.MILLISECONDS.toMinutes(estimatedTime),
					    TimeUnit.MILLISECONDS.toSeconds(estimatedTime) - 
					    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(estimatedTime))
					);
				
				System.out.println(time);


				
			}
			
			
	}