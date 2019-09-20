package com.shikhir.StrWrangler4j.nlp;

import java.net.URL;
import java.util.ArrayList;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import com.shikhir.StrWrangler4j.fileops.ClassLoaderUtilz;


public class LanguageDetector {
	
	private static LanguageDetector INSTANCE = null;
	
	private LanguageDetector() throws LangDetectException {
		URL profileResourceUrl = ClassLoaderUtilz.getResource("profiles/", LanguageDetector.class);
		String profile = profileResourceUrl.getPath();
		DetectorFactory.loadProfile(profile);
	}
	
	public static LanguageDetector getInstance() throws LangDetectException {
		if(INSTANCE==null) {
			INSTANCE = new LanguageDetector();
		}
		return INSTANCE;
	}
	/**
	 * This method is used to test if the string contains characters that are from
	 * the CJKV languages (Chinese, Japanese, Korean, or Vietnamese ).
	 * The CJKV languages are unique because they don't use space a separator 
	 * for words. This is often important because the words need to be tokenized
	 * differently.
	 *   
	 * @param strTest The string that needs tested for CJKV
	 * @return the count of CJKV characters in string
	 * @since 1.0.0
	 */
	public static int countCJKCharecters(String strTest) {
		final int length = strTest.length();
		int counter=0;
		for (int offset = 0; offset < length; ) {
		    final int codepoint = Character.codePointAt(strTest, offset);
			if(Character.isIdeographic(codepoint)){
				counter++;
			};
			
		    offset += Character.charCount(codepoint);
		}
		return counter;
	}
	
  	public static String detect(String text) throws LangDetectException {
		getInstance();
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.detect();
	}

	public static ArrayList<Language> detectLangs(String text) throws LangDetectException {
		getInstance();
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.getProbabilities();
	}

}
