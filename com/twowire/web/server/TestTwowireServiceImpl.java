package com.twowire.web.server;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.twowire.create.TicketFactory;
import com.twowire.model.Issues;

public class TestTwowireServiceImpl extends TestCase {

	private TwowireServiceImpl twowireService;
	private TicketFactory factory;
	private Issues issues;
	
	@Before
	public void setUp() throws Exception {
		factory = mock(TicketFactory.class);
		issues = mock(Issues.class);
		twowireService = new TwowireServiceImpl(factory, issues); 
	}

	@Test
	public void testSubmitIssue() throws Exception {
		twowireService.submitIssue(1, "two", 3, "four", "five", false, "org");
		verify(factory).createTicket(1, "two", 3, "four", "five", false, "org");
	}
	
	@Test
	public void testRetrieveIssues() throws Exception {
		twowireService.retrieveIssues("user");
		verify(issues).getIssues("user");
	}
	
	@Test
	public void testDefaultConstructor() throws Exception {
		twowireService = new TwowireServiceImpl();
		assertTrue(twowireService.getTicketFactory().getClass().equals(TicketFactory.class));
		assertTrue(twowireService.getPendingTickets().getClass().equals(Issues.class));
	}
}

