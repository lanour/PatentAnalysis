package edu.psu.lipx.patent.data.xml;
/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
class Journal {
	private String ISSN;
	private String Volume;
	private String Issue;
	private String pub_year;
	private String title;
	private String ISOAbbreviation;
	private static String default_value = "NOVALUE";
	public Journal() {
		ISSN = default_value;
		Volume = default_value;
		Issue = default_value;
		pub_year = default_value;
		title = default_value;
		ISOAbbreviation = default_value;
	}

	public String toString() {
		return ISSN + "\t" + Volume + "\t" + Issue
				+ "\t" + pub_year + "\t"  + title
				+ "\t" + ISOAbbreviation + "\t";
	}

	// Setter and Getter methods for the Journal

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public String getVolume() {
		return Volume;
	}

	public void setVolume(String volume) {
		Volume = volume;
	}

	public String getIssue() {
		return Issue;
	}

	public void setIssue(String issue) {
		Issue = issue;
	}

	public String getPub_year() {
		return pub_year;
	}

	public void setPub_year(String pub_year) {
		this.pub_year = pub_year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISOAbbreviation() {
		return ISOAbbreviation;
	}

	public void setISOAbbreviation(String iSOAbbreviation) {
		ISOAbbreviation = iSOAbbreviation;
	}
}
