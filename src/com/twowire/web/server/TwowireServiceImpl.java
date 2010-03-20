package com.twowire.web.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.twowire.create.TicketFactory;
import com.twowire.data.twowire.TwowireDataLayer;
import com.twowire.model.Issues;
import com.twowire.web.client.service.twowire.TwowireService;

@SuppressWarnings("serial")
public class TwowireServiceImpl extends RemoteServiceServlet implements
		TwowireService {
	
	private TicketFactory factory;
	private Issues issues;
	
	public TwowireServiceImpl() {
		factory = new TicketFactory();
		issues = new Issues();
	}
	
	public TwowireServiceImpl(TicketFactory factory, Issues issues) {
		this.factory = factory;
		this.issues = issues;
	}
	
	@Override
	public boolean checkLogin(String user, String pass){
		TwowireDataLayer manager = new TwowireDataLayer();
		return manager.validateUser(user, pass);
	}
	
	@Override
	public void submitIssue(int type, String status,
			int priority, String reporter, String summary, boolean notify, String organization) {
		factory.createTicket(type, status, priority, reporter, summary, notify, organization);
	}

	@Override
	public List<String> retrieveIssues(String user) {
		return issues.getIssues(user);
	}
	
	@Override
	public List<String> retrieveIssues2(String user) {                  //     // 
		return issues.getIssues2(user);
	}																	//     //
	
	
	

	@Override
	public List<String> retrieveIssues(String user, String key, int status) {
		return issues.getIssues(user, key, status);
	}
	
	@Override
	public List<String> retrieveIssues(String user, String key, int status, String searchString) {
		return issues.getIssuesFromTextSearch(user, key, status, searchString);
	}
	
	public TicketFactory getTicketFactory() {
		return factory;
	}

	public Issues getPendingTickets() {
		return issues;
	}



}
