package edu.psu.lipx.patent.data.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
public class MedXMLParser extends DefaultHandler {

//	private static String[] tagname = { "PMID", "DATECreated", "DATECompleted",//3
//			"DATERevised", "ISSN", "Volume", "Issue", "PubDate", "Title",//9
//			"ISOAbbreviation", "ArticleTitle", "MedlinePgn", "Author1",//13
//			"Author2", "Author3", "Language", "PublicationType", "Country",//18
//			"MedlineTA", "NlmUniqueID", "ISSNLinking", "otherID" };//22
	private ArrayList<MedlineCitation> citation_list;
	private MedlineCitation current_citation;
	private Article current_article;
	private Journal current_journal;
	private Author current_author;
	private Medline current_medline;
	private String temp_date = "";
	private String tagName = null;
	private String Date_type = "default";
	private boolean pubdate = false;

	public void parseXml(InputStream xmlStream) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlStream, this);

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tagName != null) {
			String data = new String(ch, start, length);
			if (tagName.equals("PMID")) {
				this.current_citation.setPMID(data);
			} else if (!Date_type.equals("default")) {
				insertDate(data);
			} else if (tagName.equals("ISSN")) {
				current_journal.setISSN(data);
			} else if (tagName.equals("Volume")) {
				current_journal.setVolume(data);
			} else if (tagName.equals("Issue")) {
				current_journal.setIssue(data);
			} else if (pubdate) {
				if (tagName.equals("Year")) {
					current_journal.setPub_year(data);
				}
				pubdate = false;
			} else if (tagName.equals("Title")) {
				current_journal.setTitle(data);
			} else if (tagName.equals("ISOAbbreviation")) {
				current_journal.setISOAbbreviation(data);
			} else if (tagName.equals("ArticleTitle")) {
				current_article.setArticle_title(data);
			} else if (tagName.equals("MedlinePgn")) {
				current_article.setPagination(data);
			} else if (tagName.equals("LastName")) {
				current_author.setLastname(data);
			} else if (tagName.equals("ForeName")) {
				current_author.setForename(data);
			} else if (tagName.equals("Initials")) {
				current_author.setInitial(data);
			} else if (tagName.equals("Language")) {
				current_article.setLanguage(data);
			} else if (tagName.equals("PublicationType")) {
				current_article.add_publicationtype(data);
			} else if (tagName.equals("Country")) {
				current_medline.setCountry(data);
			} else if (tagName.equals("MedlineTA")) {
				current_medline.setMedlineTA(data);
			} else if (tagName.equals("NlmUniqueID")) {
				current_medline.setNlmUniqueID(data);
			} else if (tagName.equals("ISSNLinking")) {
				current_medline.setISSNLinking(data);
			} else if (tagName.equals("OtherID")) {
				current_citation.setOtherID(data);
			}

		}

	}

	// Parse the date info
	private void insertDate(String data) {
		if (tagName.equals("Year")) {
			temp_date += Integer.parseInt(data) + "-";
		} else if (tagName.equals("Month")) {
			temp_date += Integer.parseInt(data) + "-";
		} else if (tagName.equals("Day")) {
			temp_date += Integer.parseInt(data);
			if (Date_type.equals("Created")) {
				this.current_citation.setDate_created(temp_date);
			} else if (Date_type.equals("Completed")) {
				this.current_citation.setDate_completed(temp_date);
			} else if (Date_type.equals("Revised")) {
				this.current_citation.setDate_revised(temp_date);
			}
			Date_type = "default";
			temp_date = "";
		}

	}

	@Override
	public void endDocument() throws SAXException {

	}

	// When read to the end of each element do this
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equals("Journal")) {
			current_article.setJournal(current_journal);
		} else if (qName.equals("Author")) {
			current_article.add_author(current_author);
		} else if (qName.equals("Article")) {
			current_citation.setArticle(current_article);
		} else if (qName.equals("MedlineCitation")) {
			this.citation_list.add(current_citation);
		} else if (qName.equals("MedlineJournalInfo")) {
			current_citation.setMedline(current_medline);
		}
		this.tagName = null;

	}

	// Run the below code when start parsing
	@Override
	public void startDocument() throws SAXException {

	}

	/**
	 * Each element, object or larger tag in the xml document need to initialize
	 * some objects to fill with the information
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("MedlineCitationSet")) {
			citation_list = new ArrayList<MedlineCitation>();
		} else if (qName.equals("MedlineCitation")) {
			current_citation = new MedlineCitation();
		} else if (qName.equals("DateCreated")) {
			Date_type = "Created";
		} else if (qName.equals("DateCompleted")) {
			Date_type = "Completed";
		} else if (qName.equals("DateRevised")) {
			Date_type = "Revised";
		} else if (qName.equals("Article")) {
			current_article = new Article();
		} else if (qName.equals("Journal")) {
			current_journal = new Journal();
		} else if (qName.equals("PubDate")) {
			pubdate = true;
		} else if (qName.equals("Author")) {
			current_author = new Author();
		} else if (qName.equals("MedlineJournalInfo")) {
			this.current_medline = new Medline();
		}

		this.tagName = qName;

	}

	public ArrayList<MedlineCitation> getCitation_list() {
		return citation_list;
	}

	public void setCitation_list(ArrayList<MedlineCitation> citation_list) {
		this.citation_list = citation_list;
	}

}
