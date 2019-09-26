# StrWrangler4j - A String Wrangling Library for Natural Language Processing in Java

This library makes is simple to wrangle and prepare Strings in Java for NLP processing


**Author**

* Shikhir Singh

**Dependencies**

* Java 8+ 

**How to Install**

Maven - be sure to check for latest version in Maven:

```
<dependency>
  <groupId>com.shikhir</groupId>
  <artifactId>StrWrangler4j</artifactId>
  <version>1.2.3</version>
</dependency>
```

**Just get me started ASAP!**

* Load a small file quickly into a string array. Will not load from classpath
```
String [] arrFile = null;
try{
	arrFile = FileOpsUtil.loadFile(file ,"UTF-8");
}
catch(Exception e){
	e.printStackTrace();
}
```

* To prepare a string for NLP, you will first want to clean the data
```
String contaminatedText = "您在马蜂窝预订的长沙往返哈尔滨 Hello World 全!程";
CleanText.cleanAll(contaminatedText);
```

* To detect a language
```
try {
	LangDetector.getInstance(); // loads language detection models
	System.out.println(LanguageDetector.detect("Hello World"));  // english 
																// See HumanLanguage.java for full language list
	System.out.println(LanguageDetector.detect("您在马蜂窝预订的长沙往返哈尔滨"));  // chinese
	LanguageDetector.close(); // closes language detection model to free up memory - close if no more predictions are needed
} catch (Exception e) {
	e.printStackTrace();
}
```

* To Murmur hash a string (very fast hashing algorithm)
```
final String testString = "Hello World";
int murmurTest1 = MurmurHash.hash32(testString);	
```

* To remove stop words
```
String sentence = "Hello my name is Shikhir. This is a test to see if the stopwords function actually remove all the stopwords.";
String removedStopWords = Stopwords.removeStopWords(sentence);
```

* To remove confusables strings (Unicode characters that look english - used by bad actors to bypass spam filtering)
```
String confusable = "ᔕE᙭Y ᔕᑌᑎ ᗪᖇEᔕᔕ ᗩᗷEᖇᑕᖇOᗰᗷIE & ᖴITᑕᕼ ᑎᗯOT ᗪIᔕTᖇEᔕᔕEᗪ ᖴᒪIᖇTY ᔕᑌᑎ ᗪᖇEᔕᔕ ᗯITᕼ ᗯᕼITE IᑎᔕIᗪE ᒪIᑎIᑎG ᗩᑎᗪ 2 ᗪEEᑭ ᔕIᗪE ᑭOᑕKETᔕ.";
String unconfuse = StringConfusable.unconfuse(confusable);
```

* To shuffle a csv file
```
ClassLoader classLoader = getClass().getClassLoader();
File fileInput = new File("testing_dataset.csv").getFile());
File fileOutput = new File("outputFile.csv");		
try {
	FileOpsUtil.shuffleFile(fileInput, fileOutput, "UTF-8");
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
```

* To find Similarity of two string
```
double levenshtein_similarity = NlpOperations.levenshteinSimilarity("Its Hello world!", "Its Jello world!") * 100.0;
double cosine_similarity = NlpOperations.cosineSimilarity("Its Hello world!", "Its Jello world!") * 100.0;

```

* To stem a string 
```
String stemmed = NlpOperations.stem("His government is seeking to renegotiate the withdrawal deal agreed by his predecessor, Theresa May.");
```

**RUNNING EXAMPLES**

* You can find working examples in the test code located at /src/test/java/

**LICENSE**
* Apache 2.0

**Version History**

* 1.0.0 - Initial Release
* 1.0.1 - minor updates to test code
* 1.0.2 - added string cosine and Levenshtein similarity 
* 1.0.3 - bug fixes and shuffle functions
* 1.0.4 - New ClassLoaderUtilz
* 1.0.5 - small bug fixes
* 1.1.0 - added language detection
* 1.1.7 - fixed language detection bugs
* 1.1.9 - finally fixed lang detection classpath issues
* 1.2.1 - removed unneeded libraries
* 1.2.2 - added close method for lang detection to save memory
* 1.2.3 - added HumanLanguage class - yet to be integrated for prediction response


**Roadmap Features**
* use HumanLanguage class for prediction