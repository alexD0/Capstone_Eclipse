package com.twowire;

import com.twowire.create.TestIssueCreator;
import com.twowire.create.TestTicketFactory;
import com.twowire.data.TestDataService;
import com.twowire.data.TestXmlRpcConfiguration;
import com.twowire.data.TestXmlRpcDataConnection;
import com.twowire.data.twowire.TestTwowireDataLayer;
import com.twowire.model.TestFilter;
import com.twowire.model.TestIssues;
import com.twowire.notify.TestEmailNotification;
import com.twowire.notify.TestGeneralNotification;
import com.twowire.notify.TestSMSNotification;
import com.twowire.web.server.TestTwowireServiceImpl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.twowire.web");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestIssueCreator.class);
		suite.addTestSuite(TestDataService.class);
		suite.addTestSuite(TestXmlRpcConfiguration.class);
		suite.addTestSuite(TestXmlRpcDataConnection.class);
		suite.addTestSuite(TestEmailNotification.class);
		suite.addTestSuite(TestGeneralNotification.class);
		suite.addTestSuite(TestSMSNotification.class);
		suite.addTestSuite(TestFilter.class);
		suite.addTestSuite(TestIssues.class);
		suite.addTestSuite(TestTicketFactory.class);
		suite.addTestSuite(TestTwowireServiceImpl.class);
		suite.addTestSuite(TestTwowireDataLayer.class);
		//$JUnit-END$
		return suite;
	}

}
