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
  <version>1.1.9</version>
</dependency>
```

**Just get me started ASAP!**

* Load a small file quickly
```
String [] arrFile = 	null
try{
	arrFile = FileOpsUtil.loadFile(file ,"UTF-8");
}
catch(Exception e){

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
		System.out.println(LanguageDetector.detect("Hello World"));  // english
		System.out.println(LanguageDetector.detect("您在马蜂窝预订的长沙往返哈尔滨"));  // chinese
	} catch (Exception e) {
		e.printStackTrace();
	}
```

* To Murmur hash a string
```
	final String testString = "Hello World";
	int murmurTest1 = MurmurHash.hash32(testString);	
```

* To remove stop words
```
	String sentence = "Hello my name is Shikhir. This is a test to see if the stopwords function actually remove all the stopwords.";
	String removedStopWords = Stopwords.removeStopWords(sentence);
```

* To remove confusables strings (Unicode characters that look english)
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
double levenshtein_similarity = NlpOperations.levenshteinSimilarity("Hello!", "Jello!");
double cosine_similarity = NlpOperations.cosineSimilarity("Hello world!", "Jello world!");

```

* To stem a string 
```
String stemmed = NlpOperations.stem("His government is seeking to renegotiate the withdrawal deal agreed by his predecessor, Theresa May.");
```


**LICENSE**
* Apache 2.0

**Version History**

* 1.0.0 - Initial Release
* 1.0.1 - minor updates to test code
* 1.0.2 - added string cosine and Levenshtein similarity code
* 1.0.3 - bug fixes and shuffle functions
* 1.0.4 - New ClassLoaderUtilz
* 1.0.5 - small bug fixes
* 1.1.0 - added language detection
* 1.1.7 - fixed language detection bugs
* 1.1.9 - finally fixed lang detection classpath issues


**Roadmap Features**
* Multi-language support