package com.twowire.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.twowire.data.jira.DataService;
import com.twowire.data.jira.IDataService;
import com.twowire.data.twowire.TwowireDataLayer;

public class Issues {
	
	private IDataService jiraData;
	private TwowireDataLayer twowireData;
	private Filter filter;
	
	public Issues() {
		this.jiraData = new DataService();
		this.twowireData = new TwowireDataLayer();
		this.filter = new Filter();
	}
	
	public Issues(IDataService dataService) {
		this.jiraData = dataService;
		this.twowireData = new TwowireDataLayer();
		this.filter = new Filter();
	}

	public List<String> getIssues(String user) {
		return issuesToString(retrieveIssues(user));
	}
	
	public List<String> getIssues2(String user)                //       // 
	{
		return issuesToString2(retrieveIssues(user));
	}                                                          //       //
	
	private List<HashMap> retrieveIssues(String user) {                         ////
		List<HashMap> result = new ArrayList<HashMap>();
		List<String> keys = twowireData.getIssueKeysForUser(user);
		for(String key : keys) {
			result.add(getIssueForKey(key));
		}
		return result;
	}

	public List<String> getIssues(String user, String key, int status) {
		return issuesToString(retrieveIssues(user, key, status));
	}
	
	private List<HashMap> retrieveIssues(String user, String key, int status) {
		List<HashMap> issues = new ArrayList<HashMap>();
		if(!key.equals("")) {
			HashMap issue = getIssueForKey(key);
			issue = checkUserIsIssueOwner(issue, user);
			issues.add(issue);
		}
		else {
			issues = getHashMapIssues(user);
			issues = filter.filterIssuesByStatus(issues, status);
		}
		return issues;
	}
	
	private HashMap checkUserIsIssueOwner(HashMap issue, String user) {
		if(!issue.get("reporter").equals(user)) {
			return null;
		}
		return issue;
	}

	private List<HashMap> getHashMapIssues(String user) {
		List<HashMap> result = new ArrayList<HashMap>();
		List<String> keys = twowireData.getIssueKeysForUser(user);
		for(String key : keys) {
			result.add(getIssueForKey(key));
		}
		return result;
	}
	
	private HashMap getIssueForKey(String key) {                                     ////
		try {
			return jiraData.retrieveIssue(key);
		} catch (XmlRpcException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> issuesToString(List<HashMap> result) {                       ///
		List<String> strings = new ArrayList<String>();
		if(result.size()>0) {
			for(HashMap map : result) {
				strings.add("Issue " + map.get("key") + ":  " + map.get("summary"));
			}
		}
		else {
			strings.add("No Matching Issues.");
		}
		return strings;
	}
	
	public List<String> issuesToString2(List<HashMap> result) {                       ///                       ///
		List<String> strings = new ArrayList<String>();
		if(result.size()>0) {
			for(HashMap map : result) {
				strings.add("Issue " + map.get("key") + ":" + map.get("summary")+" Created: " + map.get("created") + " Updated: "+ map.get("updated")+ "  Status " + map.get("status"));
				
			}
		}
		else {
			strings.add("No Matching Issues.");
		}
		return strings;
	}                                                                                 ///                       ///
	
	
	

	public List<String> getIssuesFromTextSearch(String user, String key,
			int status, String searchString) {
		List<HashMap> result = new ArrayList<HashMap>();
		if(!key.equals("")) {
			result.add(checkUserIsIssueOwner(getIssueForKey(key), user));
		}
		else {
			result = retrieveIssues(user, key, status);
			if(result.size()==0) {
				return null;
			}
			else {
				result = filter.filterIssuesBySearchString(result, searchString);
			}
		}
		return issuesToString(result);
	}


}
