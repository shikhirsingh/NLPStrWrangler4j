package com.shikhir.datascience.StrWrangler4j;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.shikhir.datascience.StrWrangler4j.fileops.FileOpsUtil;
import com.shikhir.datascience.StrWrangler4j.hash.CryptoHash;
import com.shikhir.datascience.StrWrangler4j.hash.MurmurHash;
import com.shikhir.datascience.StrWrangler4j.nlp.CleanText;
import com.shikhir.datascience.StrWrangler4j.nlp.Stopwords;

import junit.framework.TestCase;



/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
	@Test
	public void testStopWords() {		
		String sentence = "Hello my name is Shikhir. This is a test to see if the stopwords function actually remove all the stopwords.";
		String removedStopWords = Stopwords.removeStopWords(sentence);
		
		assertEquals(removedStopWords, "Shikhir. test stopwords function remove stopwords.");
	}
	
	@Test
	public void testCleanText() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("testing_dataset.csv").getFile());

		String [] arrFile = null;
		try {
			arrFile = FileOpsUtil.loadFile(file ,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(arrFile.length>4);
		assertEquals(" NetAngels. 71557 : u71557 : kpWmVAsvLaaE7Mp", CleanText.cleanAll(arrFile[0]));
	}	
	
	@Test
	public void testHashTests() {
		final String testString = "Hello World";
		int murmurTest1 = MurmurHash.hash32(testString);	
		
		assertEquals(-554020671, murmurTest1);
		assertEquals(CryptoHash.sha1_To_hex(testString), "0a4d55a8d778e5022fab701977c5d840bbc486d0");
	}
	
	@Test
	public void testNlpOperations() {
	
	}	

	@Test
	public void testStringConfusable() {
		
	}	

}
