package com.twowire.data.jira;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;


public class XmlRpcDataConnection implements DataConnection {
	
    private String user_name = "tltjr";
    private String password  = "tltjr";
    private DataConfiguration config;
    
    public XmlRpcDataConnection(DataConfiguration config) {
    	this.config = config;
    }
    
	public XmlRpcDataConnection() {
		this.config = new XmlRpcConfiguration();
	}

	public XmlRpcDataConnection(String user, String pass) {
		this.user_name = user;
		this.password = pass;
		this.config = new XmlRpcConfiguration();
	}

	public String login() {
		Vector<String> loginParams = new Vector<String>(2);
		loginParams.add(user_name);
		loginParams.add(password);
		String loginToken = null;
		try {
			loginToken = (String) config.getClient().execute("jira1.login", loginParams);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return loginToken!=null ? loginToken : null;
	}
	
	public boolean logout(String loginToken) throws XmlRpcException {
		Vector<String> logoutParams = new Vector<String>(1);
		logoutParams.add(loginToken);
		return (Boolean) config.getClient().execute("jira1.logout", logoutParams);
	}

	public DataConfiguration getConfig() {
		return config;
	}


}
