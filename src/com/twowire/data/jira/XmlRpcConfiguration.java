package com.twowire.data.jira;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;


public class XmlRpcConfiguration implements DataConfiguration {
	
	private String jira_uri  = "http://jira.atlassian.com";
    private String rpc_path  = "/rpc/xmlrpc";
    
    private XmlRpcClient rpcClient;
    private XmlRpcClientConfigImpl config;
    
    public XmlRpcConfiguration() {
    	this.jira_uri = "http://jira.atlassian.com";
    	this.rpc_path = "/rpc/xmlrpc";
    	configureRpcClient();
    }
    
    public XmlRpcConfiguration(String uri, String rpcPath) {
    	this.jira_uri = uri;
    	this.rpc_path = rpcPath;
    	configureRpcClient();
    }
    
    public boolean configure() {
    	return configureRpcClient();
    }
    
	private boolean configureRpcClient() {
		config = new XmlRpcClientConfigImpl();
		try {
			config.setServerURL(new URL(jira_uri + rpc_path));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		rpcClient = new XmlRpcClient();
		rpcClient.setConfig(config);
		
		return true;
	}
	
	public XmlRpcClient getClient() {
		return rpcClient;
	}
}
