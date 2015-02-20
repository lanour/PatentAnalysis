
package edu.psu.lipx.patent.data.xml;
/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
public class MedlineCitation {

	// Read all the information from the XML file to fill the public item object
	private String date_created, date_completed, date_revised;// Define the
																// created,
																// completed and
																// revised date
																// for
																// the article
																// in
																// the XML
	private Article article;// Define the article
	private Medline medline;
	private String PMID ;
	private String otherID;
	private static String default_value = "NOVALUE";
	
	public MedlineCitation() {
		date_created = default_value;
		date_completed = default_value;
		date_revised = default_value;
		PMID = default_value;
		otherID = default_value;
	}

	public String toString() {
		
		String result = PMID + "\t";
		result +=  date_created + "\t"
				+ date_completed + "\t" + date_revised + "\t";
		result += article.toString();
		result += medline.toString();
		result +=  otherID + "\t";
		return result;
	}

	/**
	 * @return the date_created
	 */
	public String getDate_created() {
		return date_created;
	}

	/**
	 * @param date_created the date_created to set
	 */
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	/**
	 * @return the date_completed
	 */
	public String getDate_completed() {
		return date_completed;
	}

	/**
	 * @param date_completed the date_completed to set
	 */
	public void setDate_completed(String date_completed) {
		this.date_completed = date_completed;
	}

	/**
	 * @return the date_revised
	 */
	public String getDate_revised() {
		return date_revised;
	}

	/**
	 * @param date_revised the date_revised to set
	 */
	public void setDate_revised(String date_revised) {
		this.date_revised = date_revised;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * @return the medline
	 */
	public Medline getMedline() {
		return medline;
	}

	/**
	 * @param medline the medline to set
	 */
	public void setMedline(Medline medline) {
		this.medline = medline;
	}

	/**
	 * @return the pMID
	 */
	public String getPMID() {
		return PMID;
	}

	/**
	 * @param pMID the pMID to set
	 */
	public void setPMID(String pMID) {
		PMID = pMID;
	}

	/**
	 * @return the otherID
	 */
	public String getOtherID() {
		return otherID;
	}

	/**
	 * @param otherID the otherID to set
	 */
	public void setOtherID(String otherID) {
		this.otherID = otherID;
	}

	/**
	 * @return the default_value
	 */
	public static String getDefault_value() {
		return default_value;
	}

	/**
	 * @param default_value the default_value to set
	 */
	public static void setDefault_value(String default_value) {
		MedlineCitation.default_value = default_value;
	}
	
	

}
