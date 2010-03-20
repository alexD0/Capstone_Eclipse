package com.twowire.create;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestIssueCreator extends TestCase {

	private IssueCreator issueCreator;

	@Before
	public void setUp() throws Exception {
		issueCreator = new IssueCreator();
	}
	
	@Test
	public void testTicketOne() throws Exception {
		Ticket ticket = new Ticket(1, "Open", 2, "Replacement Reporter", "Test create Issue", "org");
		issueCreator.update(ticket);
	}
	
	// "Major" priority failed
	// 1 = Blocker
	// 2 = Critical
	// 3 = Major
	// 4 = Minor
	// 5 = Trivial
	
	// Replacement Reporter does not work.
	
	/*
	 * Ticket Types
	 Bug — A problem which impairs or prevents the functions of the product.
	 Improvement — An enhancement to an existing feature.
	 New Feature — A new feature of the product.
	 Task — A task that needs to be done.
	 Custom Issue — A custom issue type, as defined by your organisation if required.
	 
	 Status
	  Open — This issue is in the initial 'Open' state, ready for the assignee to start work on it.
 	In Progress — This issue is being actively worked on at the moment by the assignee.
 	Resolved — A Resolution has been identified or implemented, and this issue is awaiting verification by the reporter. From here, issues are either 'Reopened' or are 'Closed'.
 	Reopened — This issue was once 'Resolved' or 'Closed', but is now being re-examined. (For example, an issue with a Resolution of 'Cannot Reproduce' is Reopened when more information becomes available and the issue becomes reproducible). From here, issues are either marked In Progress, Resolved or Closed. 
 	Closed — This issue is complete.
 
	 Priority
	An issue's priority indicates its relative importance. The default priorities are listed below; note that both the priorities and their meanings can be customised by your JIRA administrator to suit your organisation.
	
	 Blocker — Highest priority. Indicates that this issue takes precedence over all others.
	 Critical — Indicates that this issue is causing a problem and requires urgent attention.
	 Major — Indicates that this issue has a significant impact.
	 Minor — Indicates that this issue has a relatively minor impact.
	 Trivial — Lowest priority.
 
 */
 
}
