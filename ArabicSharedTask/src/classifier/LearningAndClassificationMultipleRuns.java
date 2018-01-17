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
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;

/**
 * This class implements the learning and classifier in Java using WEKA.
 * It takes training and testing arff files and accepts multiple runs with different features
 */
	 public class LearningAndClassificationMultipleRuns {

			
			
			/**
			 * This method performs the training (learning) and classification of the instance.
			 * It uses the ARFF files created using ARFFCreator class and supports multiple runs
			 * Output is done at the command-line.
			 */
			
		static PrintWriter writer;
		static int counter = 0;
		static String[] goldStandards;
		static int correctInstances = 0;
		
		
		
		
		public static void main(String[] args) throws Exception {
			long startTime = System.currentTimeMillis();
			
			//the following are the name of features for the ARFF files found in arff/FeaturesSelection, I created multiple arff file with each focusing on different
			//feature and also combining all features together as in AllFeatures.
			String[] features = {"AllFeatures", "Stylistic_Bivalency", "Grammatical_Stylistic", "Grammatical_Bivalency", "Bivalency", "Stylistic", "Grammatical"};
			goldStandards = readLines("arff/GoldStandards.txt");//these are the real predictions of the testing files, used here to calculate accuracy.

			for(int i=0; i<features.length; i++){
			LearningAndClassificationMultipleRuns.predict("arff/FeaturesSelection/LREC2018_"+features[i]+"_Train.arff","arff/FeaturesSelection/LREC2018_"+features[i]+"_Test.arff",features[i]);
			
			long estimatedTime = System.currentTimeMillis() - startTime;
			
			String time = String.format("%d min, %d sec", 
				    TimeUnit.MILLISECONDS.toMinutes(estimatedTime),
				    TimeUnit.MILLISECONDS.toSeconds(estimatedTime) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(estimatedTime))
				);
			
			System.out.println(time);

			}
			
		}
		
		
		public static void predict(String trainingFile, String testingFile, String feature) throws Exception {
			
			Classifier classifier = null;


			System.out.println("==========================================================================");

			System.out.println("read training arff");

			Instances train = new Instances(new BufferedReader(new FileReader(trainingFile)));
			train.setClassIndex(0);

			System.out.println("read testing arff");
			Instances unlabeled = new Instances(new BufferedReader(new FileReader(testingFile)));
			unlabeled.setClassIndex(0);

			// training
			String[] algorithms = {"j48","nb","knn", "smo"};

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
			System.out.println("Classifier\t" + algorithms[w] + "\tFeatures:\t" + feature);
			//writer.println("==========================================================================");
			//writer.println("Classifier\t" + algorithms[w] + "\tFeatures:\t" + feature);
			//writer.println("==========================================================================");

			Evaluation eval = new Evaluation(train);
			eval.crossValidateModel(classifier, train, 10, new Random(1));//>>>>>>>>>>>>>>>>>>>>>>>>>>here
			String output = eval.toSummaryString();
			double correct = eval.correct();
			double incorrect = eval.incorrect();
			double accuracy = correct*100/(correct+incorrect);
			double recall = eval.weightedRecall();
			double precission = eval.weightedPrecision();
			
			System.out.println(output);
			String classDetails = eval.toClassDetailsString();
			String confusionMatrix = eval.toMatrixString();
			System.out.println("===== Evaluating on filtered (training) dataset done =====");
			classifier.buildClassifier(train);//>>>>>>>>>>>>>>>>>>>>>>>>>>and here			
			Instances labeled = new Instances(unlabeled);
 
			correctInstances = 0;
			counter=0;

			for (int i = 0; i < unlabeled.numInstances(); i++) {
				double clsLabel = classifier.classifyInstance(unlabeled.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
				String prediction = unlabeled.classAttribute().value((int) clsLabel);
				if(goldStandards[i].equals(prediction))
					++correctInstances;
			}	
			double testingAccuracy = (double)(correctInstances*100)/(double)goldStandards.length;
			//print results on a text file
			writer.println(algorithms[w].toUpperCase() + "\t"+ feature + "\t" + accuracy +"\t"+ testingAccuracy + "\t" +recall + "\t" + precission);
			//uncomment the following line if you want the confusionMatrix to be printed as well.
			//writer.println(confusionMatrix);
			writer.flush();
			}}
	
						
			//read text file contents into string array
			public static String[] readLines(String filename) throws IOException {
				
				writer = new PrintWriter("arff/FeaturesSelection/FeaturesResults/featuresResultsAll.txt");
				writer.println("Classifier" + "\t" + "Features" + "\t" + "Training Accuracy"  + "\t" + "Testing Accuracy" + "\t" + "Training Recall" + "\t" + "Training Precision");
				
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