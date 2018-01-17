package arabic;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates Word Frequency lists (quick and works for large files)
 * 
 * @author Dr Mahmoud El-Haj @ Lancaster University
 *
 */
public class QuickFreqCount {

	static Map<String, Integer> map;
	static PrintWriter writer;

	public static void main(String args[]) throws IOException {

		map = new HashMap<>();
		String[] dialects = { "EGY", "LAV", "NOR", "GLF", "MSA" };// classes
																	// containing
																	// text
																	// files.

		for (int w = 0; w < dialects.length; w++) {
			map.clear();

			// create a file channel and buffer.
			FileChannel fileChannel = FileChannel.open(Paths.get("Training\\TrainingPlain\\" + dialects[w] + ".txt"));
			ByteBuffer buffer = ByteBuffer.allocate(1000);

			int noOfBytesRead = fileChannel.read(buffer);

			while (noOfBytesRead != -1) {

				// flip buffer between reading and writing.
				buffer.flip();

				// loop through buffer contents
				while (buffer.hasRemaining()) {

					// extract text from the buffer
					CharBuffer line = Charset.defaultCharset().decode(buffer);
					String text = line.toString();

					text = text.replaceAll("[\\p{P}\\p{Digit}]", " ").replaceAll("[^\\p{InArabic}]+", " ")
							.replaceAll(" +", " ").trim();//keeps only Arabic characters
					
					// using BreakIterator to get words
					List<String> wordsList = getWords(text.toLowerCase());

					countWords((String[]) wordsList.toArray(new String[0]));

				}
				buffer.clear();
				noOfBytesRead = fileChannel.read(buffer);
			}
			// close channel when no more text (bytes) remaining in the buffer.
			fileChannel.close();

			// print map output to file
			String txtFile = "Training\\TrainingPlain\\" + dialects[w] + "_FreqList.txt";//creates a frequency list for each class (dialect).
			writer = new PrintWriter(txtFile, "UTF-8");

			for (String key : map.keySet()) {
				writer.println(key);
				writer.flush();
			}

			writer.close();
		}
	}

	public static void countWords(String[] words) {

		for (String w : words) {
			if (w.length() > 1) {
				Integer n = map.get(w.trim());
				n = (n == null) ? 1 : ++n;
				map.put(w.trim(), n);
			}
		}
	}

	// iterates over text to extract words through detecting boundaries in text.
	public static List<String> getWords(String text) {
		List<String> words = new ArrayList<String>();
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(text);
		int lastIndex = breakIterator.first();
		while (BreakIterator.DONE != lastIndex) {
			int firstIndex = lastIndex;
			lastIndex = breakIterator.next();
			if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
				words.add(text.substring(firstIndex, lastIndex));
			}
		}

		return words;
	}

}
