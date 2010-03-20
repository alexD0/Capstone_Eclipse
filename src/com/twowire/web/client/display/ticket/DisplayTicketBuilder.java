package com.twowire.web.client.display.ticket;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class DisplayTicketBuilder extends Widget {
	private VerticalPanel displayTicketForm;

	public DisplayTicketBuilder(int i, List<String>result,String user) {
		displayTicketForm = new VerticalPanel();
		FlexTable flexTable = constructFlexTable(i,result);
		displayTicketForm.add(flexTable);
	}
	
	
	
	private FlexTable constructFlexTable(int i,List<String>result){
		// On all the places I put test, thats where the info will be put for each ticket
		// For some parts of the info we still don't have just leave blank until backend is further implemented
		FlexTable flexTable = new FlexTable();
		flexTable.setHTML(0, 0, "<b><l1>Ticket Summary</l1></b>");
		flexTable.setHTML(1, 0, result.get(i));
		/*
		flexTable.setHTML(1, 1, "test");
		flexTable.setHTML(2, 0, "Email Notification (Contact Info):");
		flexTable.setHTML(2, 1, "test");
		flexTable.setHTML(3, 0, "Status:");
		flexTable.setHTML(3, 1, "test");
		flexTable.setHTML(4, 0, "Date Created:");
		flexTable.setHTML(4, 1, "test");
		flexTable.setHTML(5, 0, "Last Updated:");
		flexTable.setHTML(5, 1, "test");
		flexTable.setHTML(6, 0, "<b><l1>Ticket Description</l1></b>");
		flexTable.setHTML(7, 0, "Severity:");
		flexTable.setHTML(7, 1, "test");
		flexTable.setHTML(8, 0, "Product:");
		flexTable.setHTML(8, 1, "test");
		flexTable.setHTML(9, 0, "Ticket Type:");
		flexTable.setHTML(9, 1, "test");
		flexTable.setHTML(10, 0, "Category:");
		flexTable.setHTML(10, 1, "test");
		flexTable.setHTML(10, 0, "Subject:");
		flexTable.setHTML(10, 1, "test");
		flexTable.setHTML(10, 0, "Details");
		flexTable.setHTML(10, 1, "test");
		*/
		return flexTable;
	}
	
	public VerticalPanel getPanel() {
		return displayTicketForm;
	}
}