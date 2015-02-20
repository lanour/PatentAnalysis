package edu.psu.lipx.patent.data.xml;

import java.util.ArrayList;

/**
 * @author Peixuan Li Updated Feb 15, 2014
 */
class Article {
	private Journal journal;// Define which journal the article is on
	private String article_title;
	private String pagination;
	private ArrayList<Author> author_list = new ArrayList<Author>();
	private ArrayList<String> publicationtypes = new ArrayList<String>();
	private String language;
	private static String default_value = "NOVALUE";

	public Article() {
		article_title = default_value;
		pagination = default_value;
		language = default_value;
	}

	public String toString() {
		String result = "";
		result += journal.toString();
		result += article_title + "\t" + pagination + "\t";

		for (int i = 0; i < 3; i++) {
			if (i >= author_list.size()) {
				result += default_value + "\t";
			} else {
				result += author_list.get(i) + "\t";
			}
		}

		result += language + "\t";
		for (String temp_s : publicationtypes) {
			result += temp_s + " ";
		}
		result += "\t";

		return result;
	}

	public void add_author(Author author) {
		this.author_list.add(author);
	}

	public void add_publicationtype(String publicationtype) {
		this.publicationtypes.add(publicationtype);
	}

	// Setter and Getter methods for the Article

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public ArrayList<Author> getAuthor_list() {
		return author_list;
	}

	public void setAuthor_list(ArrayList<Author> author_list) {
		this.author_list = author_list;
	}

	public ArrayList<String> getPublicationtypes() {
		return publicationtypes;
	}

	public void setPublicationtypes(ArrayList<String> publicationtypes) {
		this.publicationtypes = publicationtypes;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
