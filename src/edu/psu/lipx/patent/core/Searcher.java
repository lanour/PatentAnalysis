package edu.psu.lipx.patent.core;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.sandbox.queries.regex.RegexQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author Peixuan Li Updated Nov 6, 2014
 */
public class Searcher {

	private IndexSearcher indexSearcher;
	private Analyzer analyzer;
	private ScoreDoc[] results;
	private long executeTime;
	private QueryParser queryParser;
	private Query query;
	private int numberForResult;

	public Searcher(String indexDir) {
		super();
		analyzer = new StandardAnalyzer();
		try {
			Directory directory = FSDirectory.open(new File(indexDir));
			DirectoryReader indexReader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			System.err.println("Error: Can not open index Directory: ");
			e.printStackTrace();
		}
	}

	public Document[] getResults() {
		try {
			Document[] documents = new Document[this.results.length];
			for (int i = 0; i < this.results.length; i++)
				documents[i] = indexSearcher.doc(results[i].doc);
			return documents;
		} catch (IOException e) {
			System.err.println("Error: IOException: Cannot get the results!");
			e.printStackTrace();
		}
		return null;
	}

	public long getExecuteTime() {
		return executeTime;
	}

	public int Search(String searchField, String keyword, int numberOfResults) {
		QueryParser queryParser = new QueryParser(searchField, analyzer);
		try {
			this.query = queryParser.parse(keyword);
			this.numberForResult = numberOfResults;
			return this.performSearch();
		} catch (ParseException e1) {
			System.err
					.println("Error: Parse Exception for keyword: " + keyword);
			e1.printStackTrace();
		}
		return 0;
	}

	public int termSearch(String searchField, String term_text,
			int numberOfResult) {
		this.query = new TermQuery(new Term(searchField, term_text));
		this.numberForResult = numberOfResult;
		System.err.println("Processing: Search Type: Term Search");
		return this.performSearch();
	}

	public int wildcardSearch(String field, String query_text,
			int numberOfResult) {
		this.query = new WildcardQuery(new Term(field, query_text));
		this.numberForResult = numberOfResult;
		System.err.println("Processing: Search Type: Wildcard Search");
		return this.performSearch();
	}

	public int prefixSearch(String field, String prefix, int numberOfResult) {
		this.query = new PrefixQuery(new Term(field, prefix));
		this.numberForResult = numberOfResult;
		System.err.println("Processing: Searching Type: Prefix Search");
		return this.performSearch();
	}

	public int fuzzySearch(String field, String query_text, int max_edit,
			int numberOfResult) {
		this.query = new FuzzyQuery(new Term(field, query_text), max_edit);
		this.numberForResult = numberOfResult;
		System.err
				.println("Processing: Searching Type: Fuzzy Search within max_edit");
		return this.performSearch();
	}

	public int fuzzySearch(String field, String query_text, int max_edit,
			int prefix_length, int numberOfResult) {
		this.query = new FuzzyQuery(new Term(field, query_text), max_edit,
				prefix_length);
		this.numberForResult = numberOfResult;
		System.err
				.println("Processing: Searching Type: Fuzzy Search within max_edit and prefix matching");
		return this.performSearch();
	}

	public int rangeSearch(String field, int range_start, int range_end,
			boolean isStartIncluded, boolean isEndIncluded, int numberOfResult) {
		this.query = NumericRangeQuery.newIntRange(field, range_start,
				range_end, isStartIncluded, isEndIncluded);
		this.numberForResult = numberOfResult;
		System.err.println("Processing: Searching Type: Range Search");
		return this.performSearch();
	}

	public int regularExpressionSearch(String field,
			String query_regExpression, int numberOfResult) {
		this.query = new RegexQuery(new Term(field, query_regExpression));
		this.numberForResult = numberOfResult;
		System.err
				.println("Processing: Search Type: Regular Expression Search");
		return this.performSearch();
	}

	public int queryParserSearch(String field, String query_text,
			int numberOfResults) {
		this.queryParser = new QueryParser(field, analyzer);
		try {
			this.query = queryParser.parse(query_text);
			System.err.println("Processing: Search Type: Query Parser");
			this.numberForResult = numberOfResults;
			return this.performSearch();
		} catch (ParseException e1) {
			System.err.println("Error: Parse Exception for keyword: "
					+ query_text);
			e1.printStackTrace();
		}
		return 0;
	}

	private int performSearch() {
		try {
			System.err.println("Processing: Search for Query:\t"
					+ query.toString());
			long start = System.currentTimeMillis();
			this.results = indexSearcher.search(query, this.numberForResult).scoreDocs;
			long end = System.currentTimeMillis();
			this.executeTime = end - start;
			System.err
					.println("Processing: Search finished, Exection time for Searching: "
							+ this.executeTime);
			System.err.println("Processing: Search finished, result hits: "
					+ this.results.length);
			return this.results.length;
		} catch (IOException e) {
			System.err.println("Error: IOException for Term Searching");
			e.printStackTrace();
		}
		return 0;
	}

}
