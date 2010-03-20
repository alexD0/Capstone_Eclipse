package com.twowire.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestFilter extends TestCase {

	private Filter filter;
	private List<HashMap> input;
	private HashMap issueOne;
	private HashMap issueTwo;
	private HashMap issueThree;
	
	@Before
	public void setUp() throws Exception {
		this.filter = new Filter();
		issueOne = new HashMap();
		issueOne.put("status", 1);
		issueOne.put("reporter", "tltjr");
		issueOne.put("summary", "This is a test ticket");
		issueTwo = new HashMap();
		issueTwo.put("status", 0);
		issueTwo.put("reporter", "Someone");
		issueTwo.put("summary", "This is an issue.");
		issueThree = new HashMap();
		issueThree.put("status", 0);
		issueThree.put("reporter", "Someone");
		issueThree.put("summary", "This is a summary.");
		input = new ArrayList<HashMap>();
		input.add(issueOne);
		input.add(issueTwo);
		input.add(issueThree);
	}

	@Test
	public void testFilterIssuesByStatus() throws Exception {
		List<HashMap> result = filter.filterIssuesByStatus(input, 1);
		assertTrue(result.contains(issueOne));
		assertFalse(result.contains(issueTwo));
	}
	
	@Test
	public void testFilterIssuesByUser() throws Exception {
		List<HashMap> result = filter.filterIssuesByUser(input, "tltjr");
		assertTrue(result.contains(issueOne));
		assertFalse(result.contains(issueTwo));
	}
	
	@Test
	public void testFilterIssuesBySearchString() throws Exception {
		List<HashMap> result = filter.filterIssuesBySearchString(input, "test");
		assertTrue(result.contains(issueOne));
		assertFalse(result.contains(issueTwo));
	}
	
	@Test
	public void testFilterIssuesBySearchStringWithUndesiredMatchingKey() throws Exception {
		List<HashMap> result = filter.filterIssuesBySearchString(input, "summary.");
		assertTrue(result.contains(issueThree));
		assertFalse(result.contains(issueTwo));
		assertFalse(result.contains(issueOne));
	}
	
	
}
