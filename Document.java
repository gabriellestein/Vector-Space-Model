/**
* Creates a document that contains a list of positions where this term is found
* @author - Gabrielle Stein & Andrew Edwards
*/
package assg_03;

import java.util.*;

public class Document {

	// Document ID (number in corpus)
	private int docID;
	// Document name
	private String title;
	// Array of all authors
	private String authors;
	// Journal document is in
	private String journal;
	
	//private HashMap<Term, >
	
	/**
	* Two paramter constructor
	* @param docName - document name
	* @param docID - document ID
	* @param journal - document journal
	* @param authors - list of authors
	 */
	public Document(int docID, String title, String authors, String journal) {
		this.docID = docID;
		this.title = title;
		this.authors = authors;
		this.journal = journal;
	}
	
	/**
	* Returns document name
	* @return docName
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	* Returns authors
	* @return authors
	*/
	public String getAuthors() {
		return authors;
	}
	
	/**
	* Returns journal
	* @return jounral
	*/
	public String getJournal() {
		return journal;
	}
	
	/**
	* Returns document ID
	* @return docID
	*/
	public int getDocID() {
		return docID;
	}
	
	/**
	* Checks if two documents are equal
	* @param obj - The document
	* @return boolean stating whether two documents are the same
	*/
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(obj instanceof Document) {
			Document temp = (Document)obj;
			return this.docID == temp.docID;
		}
		else
			return false;
	}
}
