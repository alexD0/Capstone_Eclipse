package com.twowire.data.jira;

import java.util.HashMap;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;

public interface IDataService {

	HashMap<?, ?> retrieveIssue(String key) throws XmlRpcException;

}

