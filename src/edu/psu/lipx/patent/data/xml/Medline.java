package edu.psu.lipx.patent.data.xml;
/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
class Medline {

	// Define the country, medlineTA, NlmUniqueID, ISSNLinking for the
	// MedlineCitation
	private String country;
	private String MedlineTA;
	private String NlmUniqueID;
	private String ISSNLinking;
	private static String default_value = "NOVALUE";
	public Medline() {
		country = default_value;
		MedlineTA = default_value;
		NlmUniqueID = default_value;
		ISSNLinking = default_value;
	}

	public String toString() {
		return country + "\t" + MedlineTA
				+ "\t" + NlmUniqueID + "\t" + ISSNLinking
				+ "\t";
	}

	// Setter and Getter methods for the Medline information

	public String getISSNLinking() {
		return ISSNLinking;
	}

	public void setISSNLinking(String iSSNLinking) {
		ISSNLinking = iSSNLinking;
	}

	public String getNlmUniqueID() {
		return NlmUniqueID;
	}

	public void setNlmUniqueID(String nlmUniqueID) {
		NlmUniqueID = nlmUniqueID;
	}

	public String getMedlineTA() {
		return MedlineTA;
	}

	public void setMedlineTA(String medlineTA) {
		MedlineTA = medlineTA;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
