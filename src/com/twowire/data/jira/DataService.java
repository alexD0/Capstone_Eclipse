package com.twowire.data.jira;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;


public class DataService implements IDataService {
    
    private DataConnection conn;
    
    public DataService(DataConnection conn) {
    	this.conn = conn;
    }

	public DataService() {
		this.conn = new XmlRpcDataConnection();
	}

	public HashMap<?, ?> retrieveIssue(String issueKey) throws XmlRpcException {                     /////
		List<String> getIssueParams = new Vector<String>(2);
		String loginToken = conn.login();
		getIssueParams.add(loginToken);
		getIssueParams.add(issueKey);
		DataConfiguration config = conn.getConfig();
		HashMap<?, ?> issue = (HashMap<?, ?>) config.getClient().execute("jira1.getIssue", getIssueParams);
		
	//	for(int i=0;i<issue.size();i++)
		
		return issue;
	}

	public HashMap<?, ?> createIssue(Hashtable<?, ?> issueTable) {
		HashMap<?, ?> result = null;
		Vector<Object> createIssueParams = new Vector<Object>();
		String loginToken = conn.login();
		createIssueParams.add(loginToken);
		createIssueParams.add(issueTable);
		try {
			result = (HashMap<?, ?>) conn.getConfig().getClient().execute("jira1.createIssue", createIssueParams);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return result;
	}

}
