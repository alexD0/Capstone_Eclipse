package com.twowire.data;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.twowire.data.jira.XmlRpcDataConnection;

public class TestXmlRpcDataConnection extends TestCase {

	private XmlRpcDataConnection conn;
	
	@Before
	public void setUp() throws Exception {
		conn = new XmlRpcDataConnection();
	}
	
	@Before
	private void setUp(String user, String pass) {
		conn = new XmlRpcDataConnection(user, pass);
	}
	
	@Test
	public void testLogoutInvalidLoginToken() throws Exception {
		boolean result = conn.logout("Logging out now...");
		assertFalse(result);
	}

	//this test will need to use the FakeClient because execute returns
	//a different string each time and we don't want to make a call to 
	//jira for each access
	@Test
	public void testLoginReturnsValidLoginToken() throws Exception {
//		String result = conn.login();
//		assertEquals(result, "6dN8VL2Nle");
	}
	
// this currently requires server access, mock out
	@Test
	public void testLogoutValidToken() throws Exception {
		String loginToken = conn.login();
		boolean result = conn.logout(loginToken);
		assertTrue(result);
	}
	
	@Test
	public void testInvalidLoginReturnsNullToken() throws Exception {
		setUp("nouser", "nopass");
		String loginToken = conn.login();
		assertNull(loginToken);
	}
	
	@Test
	public void testInvalidLogoutReturnsFalse() throws Exception {
		assertFalse(conn.logout("Nonsense"));
	}



}
