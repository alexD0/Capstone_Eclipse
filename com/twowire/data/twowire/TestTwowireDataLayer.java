package com.twowire.data.twowire;

import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.twowire.data.twowire.BCrypt;
import com.twowire.data.twowire.TwowireDataLayer;

public class TestTwowireDataLayer extends TestCase {

	private TwowireDataLayer dataLayer;
	
	@Before
	public void setUp() throws Exception {
		dataLayer = new TwowireDataLayer();
	}

	@Test
	public void testValidateUser() throws Exception {
		boolean result = dataLayer.validateUser("tltjr", "tltjr");
		assertTrue(result);
	}
	
	@Test
	public void testEncryptedPWordMatches() throws Exception {
		String hashed = BCrypt.hashpw("tltjr", BCrypt.gensalt());
		assertTrue(BCrypt.checkpw("tltjr", BCrypt.hashpw("tltjr", BCrypt.gensalt())));
	}
	
	@Test
	public void testInsertIssue() throws Exception {
		HashMap issue = new HashMap();
		issue.put("key", "TST-12345");
		issue.put("reporter", "tltjr");
		dataLayer.addIssue(issue, "org");
		List<String> keys = dataLayer.getIssueKeysForUser("tltjr"); 
		assertTrue(keys.contains("TST-12345"));
	}
	
// This is not actually a test. It simply inserts users into the table w/ a hashed pword.
	@Test
	public void testInsertUser() throws Exception {	
//		loginManager.insertUser("tltjr", "tltjr");
	}
}
