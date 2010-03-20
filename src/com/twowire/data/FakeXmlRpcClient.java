package com.twowire.data;

import java.util.HashMap;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;

public class FakeXmlRpcClient extends XmlRpcClient {
	
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(String throwaway, List discard) {
		HashMap<String, String> issue = new HashMap<String, String>();
		issue.put("priority", "High");
		issue.put("summary", "This is a fake issue for testing.");
		issue.put("assigned to", "Thomas Thornton");
		return issue;
		
	}
}
