package com.twowire.data;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;

import junit.framework.TestCase;
import org.junit.Test;
import com.twowire.data.jira.DataService;

public class TestDataService extends TestCase {
	
	private DataService dataService;
	
	@Test
	public void testRetrieveIssueLive() throws Exception {
		dataService = new DataService();
		HashMap<?, ?> issue = dataService.retrieveIssue("TST-21076");
		System.out.println(issue);
		assertEquals(issue.get("summary"), "This summary was typed into our GUI.");
	}
	
	@Test
	public void testRetrieveIssuesForSearchString() throws Exception {
		dataService = new DataService();
	//	List<HashMap> issues = dataService.getIssuesFromTextSearch("test");
	//	for(HashMap issue : issues) {
	//		System.out.println(issue);
	//	}
	}
	
	@Test
	public void testAddAttachment() throws Exception {
		File file = new File("test_attachment.txt");
	}

}
