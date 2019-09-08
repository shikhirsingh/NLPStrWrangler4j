# StrWrangler4j - A String Wrangling Library for Natural Language Processing in Java

This library makes is simple to wrangle and prepare strings in Java for NLP processing


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
  <version>1.0.2</version>
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



**LICENSE**
* Apache 2.0

**Version History**

* 1.0.0 - Initial Release
* 1.0.1 - minor updates to test code
* 1.0.2 - added string cosine and Levenshtein similarity code
* 1.0.3 - bug fixes and shuffle functions


**Roadmap Features**
* Multi-language support