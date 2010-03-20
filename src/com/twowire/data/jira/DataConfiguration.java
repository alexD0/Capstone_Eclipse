package com.twowire.data.jira;

import org.apache.xmlrpc.client.XmlRpcClient;

public interface DataConfiguration {
	
	boolean configure();
	
	XmlRpcClient getClient();

}
