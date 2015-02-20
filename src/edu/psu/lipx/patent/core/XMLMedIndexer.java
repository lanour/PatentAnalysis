/**
 * 
 */
package edu.psu.lipx.patent.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

import edu.psu.lipx.patent.data.xml.MedXMLParser;
import edu.psu.lipx.patent.data.xml.MedlineCitation;

/**
 * @author Peixuan Li updated Feb 15, 2015
 *
 */
public class XMLMedIndexer {
	private IndexWriter indexWriter;
	private String[] fields = { "PMID", "DATECreated", "DATECompleted",// 3
			"DATERevised", "ISSN", "Volume", "Issue", "PubDate", "Title",// 9
			"ISOAbbreviation", "ArticleTitle", "MedlinePgn", "Author1",// 13
			"Author2", "Author3", "Language", "PublicationType", "Country",// 18
			"MedlineTA", "NlmUniqueID", "ISSNLinking", "otherID" };// 22
	/** the type for each field: 0 - text, 1 - string, 2 - int; */
	private int[] types = { 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 1, 0, 1 };

	public XMLMedIndexer(String indexDir) {
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

	public boolean indexXMLFile(File targetFile) {
		System.err.println("Processing: Indexing file:" + targetFile.getPath());
		try {
			InputStream is = new FileInputStream(targetFile);
			MedXMLParser xmlparser = new MedXMLParser();
			xmlparser.parseXml(is);

			for (MedlineCitation medcitation : xmlparser.getCitation_list()) {
				String[] results = (medcitation.toString()).split("\t");
				Document doc = new Document();
				for (int i = 0; i < this.fields.length; i++)
					addFields(doc, types[i], this.fields[i], results[i]);
				indexWriter.addDocument(doc);
			}
			System.err
					.println("Processing: Index build successfully for file: "
							+ targetFile.getPath());
			return true;
		} catch (IOException e) {
			System.err
					.println("Error! The target file can not be add to the index! Target File: "
							+ targetFile.getPath());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.err.println("Error! Parser confguration Exception!");
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Error! SAX exception!");
			e.printStackTrace();
		}
		return false;
	}

	private void addFields(Document doc, int type, String fieldName,
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
