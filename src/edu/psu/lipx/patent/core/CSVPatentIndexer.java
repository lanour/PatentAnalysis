package edu.psu.lipx.patent.core;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import edu.psu.lipx.patent.data.csv.CSVReader;


/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
public class CSVPatentIndexer {

	private IndexWriter indexWriter;
	private String[] fields = { "patentID", "title", "abstract", "claim",
			"description", "ID of patents cited by this patent",
			"current U.S. class", "current CPC class", "field of search",
			"inventor and address", "Assignee", "primary examiner",
			"Assistant Examiner", "Attorney, Agent or Firm", "Date of patent",
			"applNo", "filed date", "inventor's last name",
			"Foreign Patent Documents", "other references",
			"number of citation", "number of foreign patent documents",
			"number of other references" };
	/** the type for each field: 0 - text 1 - string 2 - int */
	private int[] types = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
			0, 0, 0, 2, 2, 2 };


	public CSVPatentIndexer(String indexDir) {
		super();
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory;
		try {
			directory = FSDirectory.open(new File(indexDir));
			indexWriter = new IndexWriter(directory, new IndexWriterConfig(
					Version.LUCENE_4_10_0, analyzer));
			System.err.println("Processing: Index Directory is opened: "
					+ indexDir);
		} catch (IOException e) {
			System.err.println("Error: Can not open the index Directory!");
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			indexWriter.close();
			System.err
					.println("Processing: Index Directory closed. Index building finished");
		} catch (IOException e) {
			System.err.println("Error: Can not close the index Directory!");
			e.printStackTrace();
		}
	}

//	public String[] getFields() {
//		return fields;
//	}

//	public void setFields(String[] fields, int[] types) {
//		this.fields = fields;
//		this.types = types;
//	}

	public boolean indexCSVFile(File targetFile) {
		System.err.println("Processing: Indexing file:" + targetFile.getPath());
		try {
			CSVReader reader = new CSVReader(targetFile);
			if (this.fields != null) {
				String[] row;
				while ((row = reader.nextRow()) != null) {
					Document doc = new Document();
					for (int i = 0; i < this.fields.length; i++)
						addFields(doc, types[i], this.fields[i], row[i]);
					indexWriter.addDocument(doc);
				}
				System.err
						.println("Processing: Index build successfully for file: "
								+ targetFile.getPath());
				return true;
			} else {
				System.err
						.println("Error! There are no field set to extract from file!");
				return false;
			}
		} catch (IOException e) {
			System.err
					.println("Error! The target file can not be add to the index! Target File: "
							+ targetFile.getPath());
			e.printStackTrace();
		}
		return false;
	}

	
	
	private void addFields(Document doc, int type,  String fieldName,
			String value) {
		switch (type) {
		case 1:
			doc.add(new StringField(fieldName, value, Field.Store.YES));
			break;
		case 2:
			doc.add(new IntField(fieldName, Integer.parseInt(value),
					Field.Store.YES));
			break;
		default:
			doc.add(new TextField(fieldName, value, Field.Store.YES));
		}
	}

}
