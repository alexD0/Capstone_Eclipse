package com.twowire.data;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.twowire.data.jira.XmlRpcConfiguration;

public class TestXmlRpcConfiguration extends TestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidUrlConfiguresCorrectly() throws Exception {
		XmlRpcConfiguration config = new XmlRpcConfiguration();
		boolean result = config.configure();
		assertTrue(result);
	}
	
// this may be trying to make an actual connection so if the tests are slow, skip this
	@Test
	public void testInvalidUrlDoesNotConfigure() throws Exception {
		XmlRpcConfiguration config = new XmlRpcConfiguration("@$Nonsensical/url", "##bad&rpc");
		boolean result = config.configure();
		assertFalse(result);
	}
}
