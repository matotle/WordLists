package wordCount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * @author Michael Console Version of WordSort Initially prints the default text
 *         then lets user input their own
 */
public class WordSort {

	public static void main(String[] args) {

		System.out.println("default list");
		String words = "22 $$ history, one two#two or, three*three*three four!four!four!four story";
		WordSort w = new WordSort();
		w.printLists(words);
		System.out.println("Enter custom data");
		Scanner keyboard = new Scanner(System.in);
		words = keyboard.nextLine();
		w.printLists(words);

		keyboard.close();
	}

	/**
	 * 
	 * @param words
	 * @return ArrayList of Strings 'words'
	 */
	public ArrayList<String> parseText(String words) {
		String[] invalid = { "$", "#", "!", ",", "*", "&" };

		// replace invalid any invalid Characters
		for (int i = 0; invalid.length > i; i++) {
			words = words.replace(invalid[i], " ");
		}
		// split on the empty characters
		String[] preWordArray = words.split(" ");

		ArrayList<String> wordArray = new ArrayList<String>();
		// create an ArrayList adding all the non-spaces
		for (int i = 0; preWordArray.length > i; i++) {
			if (!preWordArray[i].isEmpty()) {
				wordArray.add(preWordArray[i]);
			}
		}
		return wordArray;
	}

	/**
	 * 
	 * @param word
	 * @param wordArray
	 * @return count of that word in an array
	 */
	private int countInstancesOfWord(String word, ArrayList<String> wordArray) {
		// Start at zero incase checking an arraylist where we don't want it to be found
		int wordCount = 0;

		for (int i = 0; wordArray.size() > i; i++) {
			if (word.equalsIgnoreCase(wordArray.get(i))) {
				wordCount++;
			}
		}

		return wordCount;
	}

	/**
	 * 
	 * @param wordArray
	 * @return a Word object for sorting
	 */
	private ArrayList<Word> getWordsWithCount(ArrayList<String> wordArray) {
		ArrayList<String> distinctWords = new ArrayList<String>();
		ArrayList<Word> wordsWithCount = new ArrayList<Word>();

		// Add to the array of Distinct words if not found in distinct words
		for (int i = 0; wordArray.size() > i; i++) {

			if (countInstancesOfWord(wordArray.get(i), distinctWords) < 1) {
				distinctWords.add(wordArray.get(i));
				wordsWithCount.add(new Word(wordArray.get(i), countInstancesOfWord(wordArray.get(i), wordArray)));
			}
		}
		return wordsWithCount;

	}

	/**
	 * 
	 * @param words
	 *            Simply prints words to console
	 */
	public void printLists(String words) {

		ArrayList<String> wordArray = parseText(words);
		ArrayList<Word> distinctWordArray = getWordsWithCount(wordArray);
		Collections.sort(distinctWordArray);
		ArrayList<String> wordsByAlpha = new ArrayList<String>();
		System.out.println("Words by Quanity");
		for (int i = 0; distinctWordArray.size() > i; i++) {
			System.out.println(distinctWordArray.get(i).getWordWithQuanity());
			wordsByAlpha.add(distinctWordArray.get(i).getWordWithQuanity());
		}
		Collections.sort(wordsByAlpha);
		System.out.println("Words by Alphabetical");
		for (int i = 0; wordsByAlpha.size() > i; i++) {
			System.out.println(wordsByAlpha.get(i));

		}

	}

	/**
	 * 
	 * @author Michael
	 *
	 */
	class Word implements Comparable<Word> {
		String word;
		int quanity;

		Word(String word, int quanity) {
			this.word = word;
			this.quanity = quanity;
		}

		private String getWordWithQuanity() {

			return word + " (" + Integer.toString(quanity) + ")";
		}

		@Override
		public int compareTo(Word other) {
			return Integer.compare(other.quanity, this.quanity);
		}
	}

}
