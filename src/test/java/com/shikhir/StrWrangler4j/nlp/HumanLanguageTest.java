package com.shikhir.StrWrangler4j.nlp;

import static org.junit.Assert.*;

import org.junit.Test;

public class HumanLanguageTest {

	@Test
	public void testGet() {
		assertEquals(HumanLanguage.get("en"), HumanLanguage.ENGLISH);
		assertEquals(HumanLanguage.get("Does not exist"), null);
	}

	@Test
	public void testGetISO639_1Code() {
		assertEquals(HumanLanguage.ENGLISH.getISO639_1Code(), "en");
	}

}
