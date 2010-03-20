package com.twowire.create;

import java.util.HashMap;
import java.util.Hashtable;
import com.twowire.Observer;
import com.twowire.data.jira.DataService;
import com.twowire.data.twowire.TwowireDataLayer;

public class IssueCreator implements Observer {
	
	private DataService dataService;
	private TwowireDataLayer dataLayer;
	
	public IssueCreator() {
		this.dataLayer = new TwowireDataLayer();
	}
	
	public void update(Ticket ticket) {
		Hashtable issueTable = new Hashtable();
		issueTable.put("type", ticket.getType());
	    issueTable.put("status", "Open");
// Type must be an int from 2-5 (maybe 1 and 6?) we'll have to look into the significance of the numbers
	    issueTable.put("priority", ticket.getPriority());
	    issueTable.put("reporter", ticket.getReporter());
	    issueTable.put("summary", ticket.getSummary());
	    issueTable.put("assignee", "");//This is required
	    issueTable.put("project", "TST");
	    dataService = new DataService();
	    HashMap result = dataService.createIssue(issueTable);
	    dataLayer.addIssue(result, ticket.getOrganization());
	}

}
