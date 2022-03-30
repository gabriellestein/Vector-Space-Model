/**
* Reads a corpus and builds a positonal index for every term in the corpus.
* Allows a user do a promximity search for two terms and returns all documents found in search. 
* @author - Gabrielle Stein & Andrew Edwards
*/
package assg_03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import opennlp.tools.stemmer.PorterStemmer;

public class VectorSpaceModel {

	public final static String I = ".I";

	public final static String T = ".T";

	public final static String A = ".A";

	public final static String B = ".B";

	public final static String W = ".W";


	public static void main(String[] args) {

		Scanner kbd = new Scanner(System.in);
		System.out.println("Please enter corpus file:");
		
		// Get file path to corpus
		String corpus = kbd.nextLine();
		System.out.println("Input files directory path name is: " + corpus);

		// Collects file names and write them to
		File file = new File(corpus);
		// br for efficiently reading characters from an input stream
		BufferedReader br = null;

		// wordPattern specifies pattern for words using a regular expression
		Pattern wordPattern = Pattern.compile("[a-zA-Z]+");

		// wordMatcher finds words by spotting word word patterns with input
		Matcher wordMatcher;

		// A line read from file
		String line;

		// An extracted word from a line
		String word;

		//Doc id
		int docID;

		String title;

		String authors;
		
		String journal;

		// Stemmer to stem terms
		PorterStemmer stemmer = new PorterStemmer();

		//
		TitleIndex tInd = new TitleIndex();

		AbstractIndex aInd = new AbstractIndex();

		System.out.println("Beginning corpus indexing . . .");
		// Open the input file, read one line at a time, extract words
		// in the line, extract characters in a word, write words and
		// character counts to disk files
		try {
			// Get a BufferedReader object, which encapsulates
			// access to a (disk) file
			br = new BufferedReader(new FileReader(file));

			// As long as we have more lines to process, read a line
			while ((line = br.readLine()) != null) {
				if(line.next().equals(I)){
					docID = next();
				}
				if(line.equals(T)){
					while(line = br.readLine() != A) {
						wordMatcher = wordPattern.matcher(line);
						title += line;
					}
				}

				if(line.equals(A)) {
					while(line = br.readLine != B){
						authors += line;
					}
				}

				if(line.equals(B)){
					while(line = br.readLine != W){
						journal += line;
					}
				}


				String[] titleWords = title.split("\\s+");
				for(int x = 0; x < titleWords.length()){
					if(!tInd.containsKey(titleWords[i]))
					{
						tInd.addTitle(titleWords[i]);

						tInd.get(titleWords[i]).addDocument(new Document(docId, title, authors, journal));
					}
					else { 
						tInd.get(titleWords[i]).addDocument(new Document(docId, title, authors, journal));
					}
				}


				else {

					
					// Process the line by extracting words using the wordPattern
					wordMatcher = wordPattern.matcher(line);

					// Process one word at a time
					while (wordMatcher.find()) {
						// Extract the word
						word = line.substring(wordMatcher.start(), wordMatcher.end());
						// System.out.println(word);

						// Convert the word to lowercase, and write to word file
						word = word.toLowerCase();
						
						// Stems the word - Porter Stemmer
						word = stemmer.stem(word);

						/*// Checks is word has already been added to positional index, if not:
						if (!posInd.containsKey(word)) {
							// Adds term to index
							posInd.addTerm(new Term(word));
							// Adds current document to this terms document list
							posInd.get(word).add(new Document(inputFileNames[index], index + 1));
							// Adds current position to this document's position list
							posInd.get(word)
									.get(posInd.get(word).indexOf(new Document(inputFileNames[index], index + 1)))
									.addPosition(pos);
						// Checks if this document is already in this term's document list; if not:
						} else if (!posInd.get(word).contains(new Document(inputFileNames[index], index + 1))) {
							// Adds this document to this term's document list 
							posInd.get(word).add(new Document(inputFileNames[index], index + 1));
							// Adds this position to this docuement's position list for the current term
							posInd.get(word)
									.get(posInd.get(word).indexOf(new Document(inputFileNames[index], index + 1)))
									.addPosition(pos);
						// If this term is already in positional index, and this document is already in the term's
						// document list, then:
						} else {
							// Adds current position to this document's position list for this term
							posInd.get(word)
									.get(posInd.get(word).indexOf(new Document(inputFileNames[index], index + 1)))
									.addPosition(pos);
						}*/
						// Moves to next position in document for next word
						
					} // while - wordMatcher
				}
			} // while - line
		} // try
		catch (IOException ex) {
			System.err.println("File " + inputFileNames[index] + " not found. Program terminated.\n");
			System.exit(1);
		}

		System.out.println("Finished corpus indexing");
		// close buffered reader
		try {
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// Begin menu
		char choice;
		do {
			System.out.println("Please enter a phrase to search for in corpus ('term a' \\k 'term b')");
			String a = stemmer.stem(kbd.next()).toLowerCase();
			int k = Character.getNumericValue((kbd.next().charAt(1)));
			String b = stemmer.stem(kbd.next()).toLowerCase();
			kbd.nextLine();
			// Checks to see if the first term was found in the corpus
			if (posInd.get(a) == null) {
				System.out.println("First term not found in corpus");
			}
			 
			// Checks to see if the second term was found in the corpus
			else if(posInd.get(b) == null) {
				System.out.println("Second term not found in corpus");
			} 
			
			// Checks to see if the two terms were found in the same document within a certain range
			else if(posInd.positionalIntersect(posInd.get(a), posInd.get(b), k).size() == 0) {
				System.out.println("No documents matching this query");
			}

			// If the terms were found, print out the DocID and the Document name
			else {
				System.out.println("\nDocID\tDocName");
				for (Document d : posInd.positionalIntersect(posInd.get(a), posInd.get(b), k)) {
					System.out.println(d.getDocID() + "\t"+ d.getDocName());
				}
			}
			// Checks to see if the user wants to continue
			System.out.println("Continue? (Y/N)");
			choice = kbd.nextLine().toUpperCase().charAt(0);

		} while (choice == 'Y');

		kbd.close();
	} // main()
}
