
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
	import weka.classifiers.functions.SMO;
	import java.io.*;

	/**
	 * This class implements a simple text classifier in Java using WEKA.
	 * It loads a file with the text to classify, and the model that has been
	 * learnt with MyFilteredLearner.java.
	 */
	 public class FeaturesClassifier {

			
			/**
			 * This method performs the classification of the instance.
			 * Output is done at the command-line.
			 */
			
			
		public static void predict() throws Exception {

			Classifier smo = new SMO();
			System.out.println("read training arff");

			Instances train = new Instances(new BufferedReader(new FileReader("arff/AllClassesFeaturesTrunc20.arff")));
			train.setClassIndex(0);

			System.out.println("read testing arff");
			Instances unlabeled = new Instances(new BufferedReader(new FileReader("arff/test.arff")));
			unlabeled.setClassIndex(0);

			// training
			System.out.println("training");
			smo.buildClassifier(train);

			Instances labeled = new Instances(unlabeled);

			// label instances
			for (int i = 0; i < unlabeled.numInstances(); i++) {
				double clsLabel = smo.classifyInstance(unlabeled.instance(i));
				labeled.instance(i).setClassValue(clsLabel);
				System.out.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
			}


				
			}
			public static void main (String[] args) throws Exception {
			
				FeaturesFilteredClassifier.predict();

				
			}
	}