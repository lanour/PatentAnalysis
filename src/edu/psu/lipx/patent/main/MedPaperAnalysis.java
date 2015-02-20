package edu.psu.lipx.patent.main;

import java.io.File;

import edu.psu.lipx.patent.core.Searcher;
import edu.psu.lipx.patent.core.XMLMedIndexer;


/**
 * @author Peixuan Li Updated Feb 15, 2015 
 */
public class MedPaperAnalysis {
	private String indexPath = "/Users/lanour/Workspaces/dataFile/patent_analysis/medIndexDir";
	private String dataPath = "/Users/lanour/Workspaces/dataFile/patent_analysis/medline14n0001.xml";
	Searcher searcher = null;
//	private int numberOfHits;
	
	public static void main(String[] args) {
		MedPaperAnalysis medAnalysis = new MedPaperAnalysis();
		/** Build the index */
		medAnalysis.buildIndex();

		/** Basic search testing */
//		medAnalysis.search_testing();

		/** Sentence search testing */
//		medAnalysis.searchSentence_testing();

		/** Stemming words testing */
//		medAnalysis.stemming_testing();

	}
	public long buildIndex() {
		long start = System.currentTimeMillis();
		XMLMedIndexer indexer = new XMLMedIndexer(indexPath);
		indexer.indexXMLFile(new File(dataPath));
		indexer.close();
		long end = System.currentTimeMillis();
		System.err.println("Index build time: " + (end - start));
		return end - start;
	}
	

}
