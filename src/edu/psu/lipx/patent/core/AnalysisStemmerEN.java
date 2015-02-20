package edu.psu.lipx.patent.core;

import org.tartarus.snowball.ext.EnglishStemmer;

/**
 * @author Peixuan Li Updated Nov 6, 2014
 */
public class AnalysisStemmerEN {

	private EnglishStemmer enStemmer;

	public AnalysisStemmerEN() {
		super();
		this.enStemmer = new EnglishStemmer();
	}

	/**
	 * to find the stem of the word
	 * 
	 * @param word
	 *            , the word to find the stem
	 * @return the stem word, if can not find the stem of the word, it will
	 *         return the original word.
	 */
	public String stemWord(String word) {
		this.enStemmer.setCurrent(word);
		if (this.enStemmer.stem())
			return this.enStemmer.getCurrent();
		else
			return word;
	}

}
