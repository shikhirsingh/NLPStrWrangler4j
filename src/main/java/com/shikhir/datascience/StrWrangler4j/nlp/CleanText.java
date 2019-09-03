package com.shikhir.datascience.StrWrangler4j.nlp;

public class CleanText {

	/** Removes non-ascii characters from string
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */
	
	public static String stripNonAsciiChar(String text) {
		return text.replaceAll("[^\\p{ASCII}]","");
	}

	/** Removes whitespace characters from string and replace with one whitespace characters
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */

	public static String stripContinuousWhitespace(String text) {
		return text.replaceAll("\\s+", " "); 
	}
	
	/** Removes ASCII control characters from string
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */

	public static String stripAsciiControlChar(String text) {
		return text.replaceAll("\\p{Cntrl}", ""); 
	}
	
	/** Removes ASCII non-printable characters from String
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */

	public static String stripAsciiNonPrintableChar(String text) {
		return text.replaceAll("[^\\p{Print}]", "");
	}	

	/** Removes Unicode non-printable characters from String
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */

	public static String stripUnicodeNonPrintableChar(String text) {
		return text.replaceAll("\\p{C}", "");
	}	

	/** Removes all characters from String that are not useful in NLP operations in English
	 * 
	 * @param text the body from which the characters need to be removed
	 * @return the text after characters have been stripped
	 */

	public static String cleanAll(String text){
		return stripUnicodeNonPrintableChar(
					stripAsciiNonPrintableChar(
							stripAsciiControlChar(
									stripContinuousWhitespace(stripNonAsciiChar(text))
							)
					)
				).trim();
	}

}
