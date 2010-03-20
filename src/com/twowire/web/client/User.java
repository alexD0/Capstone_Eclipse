package com.twowire.web.client;

public class User {
	
	private static String username;
	
	public User(String username) {
		this.username = username;
	}
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String user) {
		username = user;
	}
}
