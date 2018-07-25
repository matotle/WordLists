package model;

import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * @author Michael 
 * web Version of WordLists
 */
public class WordListsModel {


	/**
	 * 
	 * @param words
	 * @return ArrayList<String> of proper words
	 */
	public ArrayList<String> parseText(String words) {
		String[] invalid = { "$", "#", "!", ",", "*" };

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
	 * @param words
	 * @return ArrayList of Strings 'words'
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
	 * @param word
	 * @param wordArray
	 * @return count of that word in an array
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
	 * @return a string that represents a list to be sent to web side
	 */
	public String getPrintLists(String words) {
		String appended = "";
		ArrayList<String> wordArray = parseText(words);
		ArrayList<Word> distinctWordArray = getWordsWithCount(wordArray);
		Collections.sort(distinctWordArray);
		ArrayList<String> wordsByAlpha = new ArrayList<String>();
		appended += "Words by Quanity: \n";
		for (int i = 0; distinctWordArray.size() > i; i++) {
			appended += distinctWordArray.get(i).getWordWithQuanity() + " \n";
			wordsByAlpha.add(distinctWordArray.get(i).getWordWithQuanity());
		}
		Collections.sort(wordsByAlpha);
		appended += "Words by Alphabetical: \n";
		for (int i = 0; wordsByAlpha.size() > i; i++) {
			appended += wordsByAlpha.get(i) + " \n";
		}
		return appended;
	}

	private class Word implements Comparable<Word> {
		String word;
		int quanity;

		private Word(String word, int quanity) {
			this.word = word;
			this.quanity = quanity;
		}

		private String getWordWithQuanity() {

			return word + " (" + Integer.toString(quanity) + ")";
		}
		// to use Collections.sort for the numbers, compare the quantity

		public int compareTo(Word other) {
			return Integer.compare(other.quanity, this.quanity);
		}
	}

}
