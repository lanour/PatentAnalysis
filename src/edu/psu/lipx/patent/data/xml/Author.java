package edu.psu.lipx.patent.data.xml;
/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
class Author {

	// Define the name and initial for the author
	private String lastname;
	private String forename;
	private String initial;
	private static String default_value = "NOVALUE";

	public Author() {
		lastname = default_value;
		forename = default_value;
		initial = default_value;
	}

	public String toString() {
		return forename + " " + lastname + " " + initial ;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
}
