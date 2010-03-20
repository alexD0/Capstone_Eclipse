package com.twowire.create;

public class Ticket {
	
	private int type;
	private String status;
	private int priority;
	private String reporter;
	private String summary;
	private String organization;

	public Ticket(int type, String status, int priority, String reporter,
			String summary, String organization) {
		this.type = type;
		this.status = status;
		this.priority = priority;
		this.reporter = reporter;
		this.summary = summary;
		this.organization = organization;
	}
	
	public int getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public int getPriority() {
		return priority;
	}

	public String getReporter() {
		return reporter;
	}

	public String getSummary() {
		return summary;
	}

	public String getOrganization() {
		return organization;
	}

}
