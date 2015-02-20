package edu.psu.lipx.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.psu.lipx.patent.data.xml.MedXMLParser;
import edu.psu.lipx.patent.data.xml.MedlineCitation;

/**
 * @author Peixuan Li Updated Nov 6, 2014
 */
public class Reader_testing {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String dataPath = "/Users/lanour/Workspaces/dataFile/patent_analysis/medline14n0001.xml";

		try {
			InputStream is = new FileInputStream(dataPath);
			MedXMLParser xmlparser = new MedXMLParser();
			xmlparser.parseXml(is);
			int flag = 1;
			for (MedlineCitation medcitation : xmlparser.getCitation_list()) {
				String[] results = (medcitation.toString()).split("\t");
				if (flag == 1) {
					System.out.println(medcitation.toString());
					System.out.println(results.length);
					for (String element : results) {
						System.out.println(element);
					}
					flag = 2;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	// public static void main(String[] args) throws Exception {
	// InputStream is = new FileInputStream("medline14n0001.xml");
	// XMLParser xmlparser = new XMLParser();
	// xmlparser.parseXml(is);
	// PrintWriter writer = new PrintWriter("medline.tsv");
	// String title = "";
	// for (String s : tagname) {
	// title += s + "\t";
	// }
	// writer.println(title);
	// for (MedlineCitation medcitation : xmlparser.getCitation_list()) {
	// writer.println(medcitation.toString());
	// }
	//
	// writer.close();
	// }

}
