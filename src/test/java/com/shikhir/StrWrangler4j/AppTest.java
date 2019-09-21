package com.shikhir.StrWrangler4j;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.cybozu.labs.langdetect.LangDetectException;
import com.shikhir.StrWrangler4j.fileops.FileOpsUtil;
import com.shikhir.StrWrangler4j.hash.CryptoHash;
import com.shikhir.StrWrangler4j.hash.MurmurHash;
import com.shikhir.StrWrangler4j.nlp.CleanText;
import com.shikhir.StrWrangler4j.nlp.Stopwords;
import com.shikhir.StrWrangler4j.nlp.LangDetector;
import com.shikhir.StrWrangler4j.nlp.NlpOperations;
import com.shikhir.StrWrangler4j.suspect.StringConfusable;
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
    public void testConfusable()
    {
    	String confusable = "ᔕE᙭Y ᔕᑌᑎ ᗪᖇEᔕᔕ ᗩᗷEᖇᑕᖇOᗰᗷIE & ᖴITᑕᕼ ᑎᗯOT ᗪIᔕTᖇEᔕᔕEᗪ ᖴᒪIᖇTY ᔕᑌᑎ ᗪᖇEᔕᔕ ᗯITᕼ ᗯᕼITE IᑎᔕIᗪE ᒪIᑎIᑎG ᗩᑎᗪ 2 ᗪEEᑭ ᔕIᗪE ᑭOᑕKETᔕ.";
    	String unconfuse = StringConfusable.unconfuse(confusable).toLowerCase();
    	assertEquals("sexy sun dress abercrombie & fitch nwot distressed flirty sun dress with white inside lining and 2 deep side pockets.", unconfuse);
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
		assertEquals("NetAngels. 71557 : u71557 : kpWmVAsvLaaE7Mp", CleanText.cleanAll(arrFile[0]));
	}	
	@Test
	public void testSimilarity() {
		int levenshtein_similarity = (int) (NlpOperations.levenshteinSimilarity("Its Hello world!", "Its Jello world!") * 100);
		int cosine_similarity = (int) (NlpOperations.cosineSimilarity("Its Hello world!", "Its Jello world!") * 100);
	
		assertEquals(levenshtein_similarity, 93);
		assertEquals(cosine_similarity, 96);
	}
	
	@Test
	public void testHashTests() {
		final String testString = "Hello World";
		int murmurTest1 = MurmurHash.hash32(testString);	
		
		assertEquals(-554020671, murmurTest1);
		assertEquals(CryptoHash.sha1_To_hex(testString), "0a4d55a8d778e5022fab701977c5d840bbc486d0");
	}
	
	private int countCharacters(File file) throws IOException {
	      FileInputStream fileStream = new FileInputStream(file);
	      InputStreamReader input = new InputStreamReader(fileStream);
	      BufferedReader reader = new BufferedReader(input);
	      int charCount = 0;
	      String data;        
	      while((data = reader.readLine()) != null) {
	         charCount += data.length();                        
	      }            
	      return charCount;
	}
	
	@Test
	public void testShuffle() {
		ClassLoader classLoader = getClass().getClassLoader();
		File fileInput = new File(classLoader.getResource("testing_dataset.csv").getFile());
		File fileOutput = new File("./src/test/resources/outputFool.csv");
		
		
		try {
			FileOpsUtil.shuffleFile(fileInput, fileOutput, "UTF-8");
			int inputFileCharCount = countCharacters(fileInput);
			int outputFileCharCount = countCharacters(fileOutput);
			//System.out.println(inputFileCharCount+" == "+outputFileCharCount);
			assertEquals(inputFileCharCount, outputFileCharCount);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Test
	public void testStemming() {
		String news= "His government is seeking to renegotiate the withdrawal deal agreed by his predecessor, Theresa May.";
		String expected = "Hi govern is seek to renegoti the withdraw deal agre by hi predecessor, Theresa May."; // note His is now Hi! - yikes, not good! 
		String stemmed = NlpOperations.stem(news);
		assertEquals(stemmed, expected);
	}
	
	@Test
	public void testLanguage() {
		try {
			assertEquals(LangDetector.detect("Hello World"), "en"); 
			assertEquals(LangDetector.detect("您在马蜂窝预订的长沙往返哈尔滨"), "zh-cn");
			
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
