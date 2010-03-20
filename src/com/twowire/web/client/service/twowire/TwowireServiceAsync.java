package com.twowire.web.client.service.twowire;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TwowireServiceAsync {

	void checkLogin(String user, String pass, AsyncCallback<Boolean> callback);

	void submitIssue(int type, String status, int priority, String reporter,
			String summary, boolean notify, String organization,
			AsyncCallback<Void> callback);

	void retrieveIssues(String user, String key, int status,
			AsyncCallback<List<String>> asyncCallback);

	void retrieveIssues(String user, AsyncCallback<List<String>> callback);             ////
	
	void retrieveIssues2(String user, AsyncCallback<List<String>> callback);           //          //

	void retrieveIssues(String user, String key, int status,
			String searchString, AsyncCallback<List<String>> callback);
}
