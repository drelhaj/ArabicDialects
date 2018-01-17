package arabic;

import java.io.IOException;
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
 * Class to count words in text (this works for very large text files and it's quick)
 * @author Dr Mahmoud El-Haj @ Lancaster University
 *
 */
public class CountingWords {

	static Map<String, Integer> map;
	
	//String textFile: text file you want to count words for
	public static int countWords(String textFile) throws IOException {

		map = new HashMap<>();

		int wordCount = 0;
		// create a file channel and buffer.
		FileChannel fileChannel = FileChannel.open(Paths.get(textFile));
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
				//text2 = text2 + text;

				text = text.replaceAll("[\\p{P}\\p{Digit}]"," ").replaceAll("[^\\p{InArabic}]+"," ").replaceAll(" +", " ").trim();
				// using BreakIterator to get words
				List<String> wordsList = getWords(text.toLowerCase());

wordCount += wordsList.size();
			}
			buffer.clear();
			noOfBytesRead = fileChannel.read(buffer);
		}
		// close channel when no more text (bytes) remaining in the buffer.
		fileChannel.close();

		
		return wordCount;
		
		
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
