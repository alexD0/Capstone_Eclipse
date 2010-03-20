package com.twowire.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.twowire.data.jira.DataService;

import junit.framework.TestCase;

public class TestIssues extends TestCase {
	
	@Test
	public void testIssuesToString() throws Exception {
		Issues i = new Issues();
		List<HashMap> issues = new ArrayList<HashMap>();
		HashMap one = new HashMap();
		one.put("reporter", "tltjr");
		one.put("key", "TST-654321");
		one.put("summary", "This is a summary for issue one");
		issues.add(one);
		HashMap two = new HashMap();
		two.put("reporter", "phonyReporter");
		two.put("key", "TST-13579");
		two.put("summary", "Issue 2 summary");
		issues.add(two);
		List<String> result = i.issuesToString(issues);
		assertTrue(result.contains("Issue TST-654321:  This is a summary for issue one"));
		assertTrue(result.contains("Issue TST-13579:  Issue 2 summary"));
	}
	
	@Test
	public void testGetIssues() throws Exception {
		Issues i = new Issues();
		List<String> result = i.getIssues("tltjr");
		assertTrue(result.contains("Issue TST-21076:  This summary was typed into our GUI."));
	}
	
	@Test
	public void testGetIssuesNoKeyNoMatchingStatus() throws Exception {
		Issues issues = new Issues();
		List<String> result = issues.getIssues("tltjr", "", 0);
		assertTrue(result.contains("No Matching Issues."));
	}
	
	@Test
	public void testGetIssuesForOpenStatus() throws Exception {
		Issues issues = new Issues();
		List<String> result = issues.getIssues("tltjr", "", 1);
		assertTrue(result.contains("Issue TST-21076:  This summary was typed into our GUI."));
	}
	
	@Test
	public void testGetIssuesWithKeyAndOpenStatus() throws Exception {
		Issues issues = new Issues();
		List<String> result = issues.getIssues("tltjr", "TST-21076", 1);
		assertTrue(result.contains("Issue TST-21076:  This summary was typed into our GUI."));
	}

}
