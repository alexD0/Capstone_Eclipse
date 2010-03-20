package com.twowire.web.client.service.twowire;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TwoWireService")
public interface TwowireService extends RemoteService {
	
	boolean checkLogin(String user, String pass);

	List<String> retrieveIssues(String user);                                  ////
	
	
	List<String> retrieveIssues2(String user);                             //        //    

	void submitIssue(int type, String status, int priority, String reporter,
			String summary, boolean notify, String organization);

	List<String> retrieveIssues(String user, String key, int status);

	List<String> retrieveIssues(String user, String key, int status,
			String searchString);
	
	

}
